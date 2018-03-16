package com.louisgeek.javamail.email.tencent;


import com.louisgeek.javamail.email.JavaEmailHostConfig;
import com.louisgeek.javamail.email.JavaEmailInfoConfig;
import com.louisgeek.javamail.email.abstracts.AbstractProtocolSmtp;

/**
 * Created by classichu on 2018/3/14.
 */

public class TencentProtocolSmtp extends AbstractProtocolSmtp {
    private static final String TAG = "TencentProtocolSmtp";
    //
    private static final String MAIL_HOST = "smtp.qq.com";
    private static final int MAIL_HOST_PORT = 25;
    private static final int MAIL_HOST_PORT_SSL = 465;// 465 / 587

    public TencentProtocolSmtp(JavaEmailInfoConfig javaEmailInfoConfig) {
        super(javaEmailInfoConfig);
    }


    @Override
    protected JavaEmailHostConfig setupHostConfig() {
        return new JavaEmailHostConfig(MAIL_HOST,MAIL_HOST_PORT,MAIL_HOST_PORT_SSL);
    }
}
