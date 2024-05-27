package com.example.front.controller;

import com.example.front.service.SmsService;
import com.example.front.view.RespResult;
import io.swagger.annotations.Api;
import org.example.common.constants.RedisKey;
import org.example.common.enums.RCode;
import org.example.common.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "message service")
@RestController
@RequestMapping("/v1/sms")
public class SmsController extends BaseController{
    @Resource(name = "smsCodeRegisterImpl")
    private SmsService smsService;

    @Resource(name = "smsCodeLoginImpl")
    private SmsService loginSmsService;
    @GetMapping("/code/register")
    public RespResult sendCodeRegister(@RequestParam String phone) throws Exception {
        RespResult result = RespResult.fail();
        if (CommonUtil.checkPhone(phone)){
            // check redis exists phone number or not
            String key = RedisKey.KEY_SMS_CODE_REG + phone;
            if (stringRedisTemplate.hasKey(key)) {
                result = RespResult.ok();
                result.setCode(RCode.SMS_CODE_CAN_USE);
            }else {
                boolean isSuccess =smsService.sendSms(phone);
                if (isSuccess){
                    result = RespResult.ok();
                }
            }
        } else {
            result.setRCode(RCode.PHONE_FORMAT_ERR);
        }

        return result;
    }

    @GetMapping("/code/login")
    public RespResult sendCodeLogin(@RequestParam String phone) throws Exception {
        RespResult result = RespResult.fail();
        if (CommonUtil.checkPhone(phone)){
            // check redis exists phone number or not
            String key = RedisKey.KEY_SMS_CODE_REG + phone;
            if (stringRedisTemplate.hasKey(key)) {
                result = RespResult.ok();
                result.setCode(RCode.SMS_CODE_CAN_USE);
            }else {
                boolean isSuccess =loginSmsService.sendSms(phone);
                if (isSuccess){
                    result = RespResult.ok();
                }
            }
        } else {
            result.setRCode(RCode.PHONE_FORMAT_ERR);
        }

        return result;
    }
}
