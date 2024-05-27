package com.example.front.service;

public interface SmsService {
    /**
     * @param phone phone number
     * @return true: success
     */
    // send message
    boolean sendSms(String phone) throws Exception;

    boolean checkSmsCode(String phone, String code);
}
