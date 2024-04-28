package org.example.common.enums;

public enum RCode {

    UNKNOWN(0, "Please try again later"),
    SUCC(1000, "request success"),
    REQUEST_PARAM_ERR(1001, "wrong request param"),
    REQUEST_PRODUCT_TYPE_ERR(1002, "wrong product type"),

    ;



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
