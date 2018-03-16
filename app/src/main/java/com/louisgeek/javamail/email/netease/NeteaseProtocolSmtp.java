package com.louisgeek.javamail.email.netease;


import com.louisgeek.javamail.email.JavaEmailHostConfig;
import com.louisgeek.javamail.email.JavaEmailInfoConfig;
import com.louisgeek.javamail.email.abstracts.AbstractProtocolSmtp;

/**
 * Created by classichu on 2018/3/14.
 */

public class NeteaseProtocolSmtp extends AbstractProtocolSmtp {
    private static final String TAG = "NeteaseProtocolSmtp";

    private static final String MAIL_HOST = "smtp.163.com";
    private static final int MAIL_HOST_PORT = 25;
    private static final int MAIL_HOST_PORT_SSL = 465;// 465 / 994

    public NeteaseProtocolSmtp(JavaEmailInfoConfig javaEmailInfoConfig) {
        super(javaEmailInfoConfig);
    }


    @Override
    protected JavaEmailHostConfig setupHostConfig() {
        return new JavaEmailHostConfig(MAIL_HOST,MAIL_HOST_PORT,MAIL_HOST_PORT_SSL);
    }
}
