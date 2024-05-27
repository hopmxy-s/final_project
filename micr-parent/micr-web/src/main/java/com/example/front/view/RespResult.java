package com.example.front.view;

import org.apache.dubbo.common.utils.Page;
import org.example.common.enums.RCode;
import java.util.List;

import java.sql.ResultSet;

// uniform response class
public class RespResult {
    // response code
    private int code;
//    text description of code
    private String msg;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    //    single data
    private Object data;

    private String accessToken;
//    list data
    private List list;
    //    paging
    private PageInfo page;

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }

    public void setRCode(RCode rcode){
        this.code = rcode.getCode();
        this.msg = rcode.getText();
    }

//    Represents the successful responseResult object
    public static RespResult ok() {
        RespResult result = new RespResult();
        result.setCode(RCode.SUCC);

        return result;
    }
//    Represents the fail responseResult object

    public void setCode(int code) {
        this.code = code;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public static RespResult fail() {
        RespResult result = new RespResult();
        result.setCode(RCode.UNKNOWN);

        return result;
    }



    public int getCode() {
        return code;
    }

    public void setCode(RCode rcode) {
        this.code = rcode.getCode();
        this.msg = rcode.getText();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
