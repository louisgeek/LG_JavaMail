package com.louisgeek.javamail.netease;


import com.louisgeek.javamail.EmailService;
import com.louisgeek.javamail.abstracts.AbstractProtocolSmtp;
import com.louisgeek.javamail.interfaces.IEmailFactory;

/**
 * Created by classichu on 2018/3/14.
 */

public class NeteaseEmailFactory implements IEmailFactory {
    private static final String USER_NAME = "bsoft_app@163.com";
    private static final String AUTH_CODE = "xxx";//163 的授权码
    //发送方的邮箱
    private static final String FROM_EMAIL = "bsoft_app@163.com";
    //发送方姓名
    private static final String FROM_NAME = "louisgeek_netease";

    @Override
    public AbstractProtocolSmtp getProtocolSmtp() {
        return new NeteaseProtocolSmtp(EmailService.create(USER_NAME, AUTH_CODE, FROM_EMAIL, FROM_NAME));
    }
}
