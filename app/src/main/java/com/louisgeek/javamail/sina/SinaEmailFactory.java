package com.louisgeek.javamail.sina;

import com.louisgeek.javamail.EmailService;
import com.louisgeek.javamail.abstracts.AbstractProtocolSmtp;
import com.louisgeek.javamail.interfaces.IEmailFactory;

/**
 * Created by louisgeek on 2018/3/19.
 */

public class SinaEmailFactory implements IEmailFactory {
    private static final String USER_NAME = "louisgeek@sina.com";
    private static final String AUTH_CODE = "xxx";//密码
    //发送方的邮箱
    private static final String FROM_EMAIL = "louisgeek@sina.com";
    //发送方姓名
    private static final String FROM_NAME = "louisgeek_sina";

    @Override
    public AbstractProtocolSmtp getProtocolSmtp() {
        return new SinaProtocolSmtp(EmailService.create(USER_NAME, AUTH_CODE, FROM_EMAIL, FROM_NAME));
    }
}
