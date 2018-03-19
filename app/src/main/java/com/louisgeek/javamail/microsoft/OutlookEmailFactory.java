package com.louisgeek.javamail.microsoft;

import com.louisgeek.javamail.EmailService;
import com.louisgeek.javamail.abstracts.AbstractProtocolSmtp;
import com.louisgeek.javamail.interfaces.IEmailFactory;

/**
 * Created by classichu on 2018/3/19.
 */

public class OutlookEmailFactory implements IEmailFactory {
    private static final String USER_NAME = "louisgeek@live.com";
    private static final String AUTH_CODE = "xxx";//密码
    //发送方的邮箱
    private static final String FROM_EMAIL = "louisgeek@live.com";
    //发送方姓名
    private static final String FROM_NAME = "louisgeek_outlook";

    @Override
    public AbstractProtocolSmtp getProtocolSmtp() {
        return new OutlookProtocol(EmailService.create(USER_NAME, AUTH_CODE, FROM_EMAIL, FROM_NAME));
    }
}
