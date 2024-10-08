package com.example.front.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jdwx.sms")
public class JdwxSmsConfig {
    private String url;
    private String appkey;
    private String content;
    private String loginText;

    public String getLoginText() {
        return loginText;
    }

    public void setLoginText(String loginText) {
        this.loginText = loginText;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
