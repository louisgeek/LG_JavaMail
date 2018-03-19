package com.louisgeek.javamail;


/**
 * Created by louisgeek on 2018/3/18.
 */

public class EmailService {
    private String account;
    private String authCode;
    private String fromEmail;
    private String fromName;

    public static EmailService create(String account, String authCode, String fromEmail) {
        return new EmailService(account, authCode, fromEmail, null);
    }

    public static EmailService create(String account, String authCode, String fromEmail, String fromName) {
        return new EmailService(account, authCode, fromEmail, fromName);
    }

    private EmailService(String account, String authCode, String fromEmail, String fromName) {
        this.account = account;
        this.authCode = authCode;
        this.fromEmail = fromEmail;
        this.fromName = fromName;
    }

    public String getAccount() {
        return account;
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
