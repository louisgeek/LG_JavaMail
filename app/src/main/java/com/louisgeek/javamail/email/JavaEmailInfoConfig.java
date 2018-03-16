package com.louisgeek.javamail.email;

/**
 * Created by classichu on 2018/3/14.
 */

public class JavaEmailInfoConfig {
    private String userName;
    private String authCode;
    private  String fromEmail;
    private  String fromName;
    public JavaEmailInfoConfig(String userName, String authCode, String fromEmail, String fromName) {
        this.userName = userName;
        this.authCode = authCode;
        this.fromEmail = fromEmail;
        this.fromName = fromName;
    }

    public String getUserName() {
        return userName;
    }

    public String getAuthCode() {
        return authCode;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public String getFromName() {
        return fromName;
    }
}
