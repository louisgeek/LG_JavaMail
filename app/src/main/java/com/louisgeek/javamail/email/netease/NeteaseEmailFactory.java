package com.louisgeek.javamail.email.netease;


import com.louisgeek.javamail.email.JavaEmailInfoConfig;
import com.louisgeek.javamail.email.abstracts.AbstractProtocolSmtp;
import com.louisgeek.javamail.email.interfaces.IEmailFactory;

/**
 * Created by classichu on 2018/3/14.
 */

public class NeteaseEmailFactory implements IEmailFactory {
    private static final String USER_NAME = "xxx@163.com";
    private static final String AUTH_CODE = "xxx";//163 的授权码
    //发送方的邮箱
    private static final String FROM_EMAIL = "xxx@163.com";
    //发送方姓名
    private static final String FROM_NAME = "xx";


    @Override
    public AbstractProtocolSmtp getProtocolSmtp() {
        return new NeteaseProtocolSmtp(new JavaEmailInfoConfig(USER_NAME, AUTH_CODE, FROM_EMAIL, FROM_NAME));
    }

}
