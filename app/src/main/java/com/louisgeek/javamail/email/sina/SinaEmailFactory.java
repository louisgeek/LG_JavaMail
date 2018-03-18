package com.louisgeek.javamail.email.sina;

import com.louisgeek.javamail.email.EmailService;
import com.louisgeek.javamail.email.abstracts.AbstractProtocolSmtp;
import com.louisgeek.javamail.email.interfaces.IEmailFactory;

/**
 * Created by louisgeek on 2018/3/19.
 */

public class SinaEmailFactory implements IEmailFactory {
    private static final String USER_NAME = "louisgeek@sina.com";
    //报 535 错误 更改QQ密码以及独立密码会触发授权码过期，需要重新获取新的授权码登录。
    private static final String AUTH_CODE = "zfq69@wb";//qq邮箱 授权码
    //发送方的邮箱
    private static final String FROM_EMAIL = "louisgeek@sina.com";
    //发送方姓名
    private static final String FROM_NAME = "louisgeek_sina";

    @Override
    public AbstractProtocolSmtp getProtocolSmtp() {
        return new SinaProtocolSmtp(EmailService.create(USER_NAME, AUTH_CODE, FROM_EMAIL, FROM_NAME));
    }
}
