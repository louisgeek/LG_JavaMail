package com.louisgeek.javamail.email.tencent;


import com.louisgeek.javamail.email.JavaEmailInfoConfig;
import com.louisgeek.javamail.email.abstracts.AbstractProtocolSmtp;
import com.louisgeek.javamail.email.interfaces.IEmailFactory;

/**
 * Created by classichu on 2018/3/14.
 */

public class TencentEmailFactory implements IEmailFactory {
    private static final String USER_NAME = "xxx@qq.com";
    //报 535 错误 更改QQ密码以及独立密码会触发授权码过期，需要重新获取新的授权码登录。
    private static final String AUTH_CODE = "xxx";//qq邮箱 授权码
    //发送方的邮箱
    private static final String FROM_EMAIL = "xxx@qq.com";
    //发送方姓名
    private static final String FROM_NAME = "xxx";

    @Override
    public AbstractProtocolSmtp getProtocolSmtp() {
        return new TencentProtocolSmtp(new JavaEmailInfoConfig(USER_NAME, AUTH_CODE, FROM_EMAIL, FROM_NAME));
    }
}