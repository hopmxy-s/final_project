package com.example.front.service.impl;

import com.example.front.service.SmsService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.example.common.constants.RedisKey;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service(value = "smsCodeRegisterImpl")
public class SmsCodeRegisterImpl implements SmsService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    //public boolean sendSms(String phone) {
    public boolean sendSms(String phone) {
        String random = RandomStringUtils.randomNumeric(4);
        System.out.println("Random number of register is:" + random);
        String key = RedisKey.KEY_SMS_CODE_REG + phone;
        stringRedisTemplate.boundValueOps(key).set(random, 3, TimeUnit.MINUTES);
/*        String host = "https://cxkjsms.market.alicloudapi.com";
        String path = "/chuangxinsms/dxjk";
        String method = "POST";
        String appcode = "536a894bc28a42bf8975682e5f0440e2";//开通服务后 买家中心-查看AppCode
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("content", "【创信】你的验证码是：5873，3分钟内有效！");
        querys.put("mobile", "13233585180");
        Map<String, String> bodys = new HashMap<String, String>();*/

        /*        try {
         *//*HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());*//*
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }*/

        return true;

    }

    @Override
    public boolean checkSmsCode(String phone, String code) {
        String key = RedisKey.KEY_SMS_CODE_REG + phone;
        if (stringRedisTemplate.hasKey(key)){
            String querySmsCode = stringRedisTemplate.boundValueOps(key).get();
            if (code.equals(querySmsCode)) {
                return true;
            }
        }
        return false;
    }

}
