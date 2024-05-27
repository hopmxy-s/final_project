package com.example.front.interceptor;

import com.alibaba.fastjson.JSONObject;

import com.example.front.view.RespResult;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.example.common.enums.RCode;
import org.example.common.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


public class TokenInterceptor implements HandlerInterceptor {

    private String secret = "";

    public TokenInterceptor(String secret) {
        this.secret = secret;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        //1.如果是OPTIONS，放行
        if( "OPTIONS".equalsIgnoreCase(request.getMethod())){
            return true;
        }
        boolean  requestSend = false;
        try{
            //2.获取token的，进行验证
            String headerUid = request.getHeader("uid");
            String headerToken = request.getHeader("Authorization");
            if(StringUtils.isNotBlank(headerToken)){
                //Bearer eyxxxxx
                String jwt = headerToken.substring(7);
                //读jwt
                JwtUtil jwtUtil = new JwtUtil(secret);
                Claims claims = jwtUtil.readJwt(jwt);

                //获取jwt中的数据，uid
                Integer jwtUid = claims.get("uid",Integer.class);
                if( headerUid.equals( String.valueOf(jwtUid))){
                    //token和发起请求用户是同一个。 请求可以被处理
                    requestSend = true;
                }
            }
        }catch (Exception e){
            requestSend = false;
            e.printStackTrace();
        }

        //token没有验证通过，需要给vue错误提示
        if( requestSend == false ){
            //返回json数据给前端
            RespResult result = RespResult.fail();
            result.setRCode(RCode.TOKEN_INVALID);

            //使用HttpServletResponse输出 json
            String respJson = JSONObject.toJSONString(result);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print(respJson);
            out.flush();
            out.close();
        }

        return  requestSend;
    }

}