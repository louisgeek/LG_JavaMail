package com.louisgeek.javamail.tencent;


import com.louisgeek.javamail.EmailProtocol;
import com.louisgeek.javamail.EmailService;
import com.louisgeek.javamail.abstracts.AbstractProtocolSmtp;

/**
 * Created by classichu on 2018/3/14.
 */

public class TencentProtocolSmtp extends AbstractProtocolSmtp {
    private static final String MAIL_HOST = "smtp.qq.com";
    private static final int MAIL_HOST_PORT = 25;
    private static final int MAIL_HOST_PORT_SSL = 465;// 465 / 587

    public TencentProtocolSmtp(EmailService emailService) {
        super(emailService);
    }

    @Override
    public EmailProtocol setupEmailProtocol() {
        return EmailProtocol.create(MAIL_HOST, MAIL_HOST_PORT, MAIL_HOST_PORT_SSL);
    }
}
