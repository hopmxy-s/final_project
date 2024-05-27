package org.example.common.enums;

public enum RCode {

    UNKNOWN(0, "Please try again later"),
    SUCC(1000, "request success"),
    REQUEST_PARAM_ERR(1001, "wrong request param"),
    REQUEST_PRODUCT_TYPE_ERR(1002, "wrong product type"),
    PRODUCT_OFFLINE(1003, "product offline"),
    PHONE_FORMAT_ERR(1004, "phone number format is wrong"),
    PHONE_EXISTS(1005, "phone number already exists"),
    SMS_CODE_CAN_USE(1006, "verification code can continue to use"),
    SMS_CODE_INVALID(1007, "verification code is invalid"),
    PHONE_LOGIN_PASSWORD_INVALID(1008, "phone number or password is invalid"),

    TOKEN_INVALID(1009, "token is invalid");



    // response code
//    0: default
//    1000-2000 is a logical problem with incorrect request parameters
//    2000-3000 is a server request error
//    3000-4000 is the response result for accessing dubbo
    private int code;
    private String text;

    RCode(int c, String t) {
        this.code = c;
        this.text = t;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
