package com.example.front.controller;

import com.example.front.service.SmsService;
import com.example.front.view.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.time.DateFormatUtils;
import org.example.api.model.User;
import org.example.api.pojo.UserAccountInfo;
import org.example.common.enums.RCode;
import org.example.common.util.CommonUtil;
import org.example.common.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "User service")
@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController{
    @Resource(name = "smsCodeRegisterImpl")
    private SmsService smsService;

    @Resource(name = "smsCodeLoginImpl")
    private SmsService loginSmsService;

    @Resource
    private JwtUtil jwtUtil;

    @ApiOperation(value = "register account")
    @PostMapping("/register")
    public RespResult userRegister(@RequestParam String phone,
                                   @RequestParam String pword,
                                   @RequestParam String scode){
        RespResult result = RespResult.fail();
        if (CommonUtil.checkPhone(phone)){
            if (pword != null && pword.length() == 32) {

                if (smsService.checkSmsCode(phone, scode)) {
                    //register

                    int registerResult = userService.userRegister(phone, pword);
                    if (registerResult == 1) {
                        result = RespResult.ok();
                    } else if (registerResult == 2){
                        result.setRCode(RCode.PHONE_EXISTS);
                    } else {
                        result.setRCode(RCode.REQUEST_PARAM_ERR);
                    }
                } else {
                    result.setRCode(RCode.SMS_CODE_INVALID);
                }

            } else {
                result.setRCode(RCode.REQUEST_PARAM_ERR);
            }
        } else {
            result.setRCode(RCode.PHONE_FORMAT_ERR);
        }
        return result;
    }


    @ApiOperation(value = "whether phone number exists")
    @GetMapping("/phone/exists")
    public RespResult phoneExists(@RequestParam("phone") String phone) {
        RespResult result = new RespResult();
        result.setRCode(RCode.PHONE_EXISTS);
        // check requirement
        if (CommonUtil.checkPhone(phone)) {
            User user = userService.queryByPhone(phone);
            if (user == null) {
                result = RespResult.ok();
            }
        }else {
            result.setRCode(RCode.PHONE_FORMAT_ERR);
        }

        return result;
    }
    @ApiOperation(value = "user login to obtain access token")
    @PostMapping("/login")
    public RespResult userLogin(@RequestParam String phone,
                                @RequestParam String pword,
                                @RequestParam String scode) throws Exception{
        RespResult result = RespResult.fail();
        if (CommonUtil.checkPhone(phone) && (pword != null && pword.length() == 32)){
            if (loginSmsService.checkSmsCode(phone, scode)){
                User user = userService.userLogin(phone, pword);
                if (user != null) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("uid", user.getId());
                    String jwtToken = jwtUtil.createJwt(data, 120);

                    result = RespResult.ok();
                    result.setAccessToken(jwtToken);

                    Map<String, Object> userInfo = new HashMap<>();
                    userInfo.put("uid", user.getId());
                    userInfo.put("phone", user.getPhone());
                    userInfo.put("name", user.getName());
                    result.setData(userInfo);

                } else {
                    result.setRCode(RCode.PHONE_LOGIN_PASSWORD_INVALID);
                }

            } else {
                result.setRCode(RCode.SMS_CODE_INVALID);
            }
        } else {
            result.setRCode(RCode.REQUEST_PARAM_ERR);
        }


        return result;
    }


    @ApiOperation(value = "user center")
    @GetMapping("/usercenter")
    public RespResult userCenter(@RequestHeader(value = "uid", required = false) Integer uid){
        RespResult result = RespResult.fail();
        if (uid != null && uid > 0) {
            UserAccountInfo userAccountInfo = userService.queryUserAllInfo(uid);
            if (userAccountInfo != null) {
                result =  RespResult.ok();

                Map<String, Object> data = new HashMap<>();
                data.put("name", userAccountInfo.getName());
                data.put("phone", userAccountInfo.getPhone());
                data.put("headerUrl", userAccountInfo.getHeaderImage());
                data.put("money", userAccountInfo.getAvailableMoney());
                if (userAccountInfo.getLastLoginTime() != null) {
                    data.put("loginTime", DateFormatUtils.format(
                            userAccountInfo.getLastLoginTime(), "yyyy-MM-dd HH:mm:ss"
                    ));

                } else {
                    data.put("loginTime", "-");
                }

                result.setData(data);

            }
        }


        return result;
    }

}
