package com.louisgeek.javamail.email.netease;


import com.louisgeek.javamail.email.EmailProtocol;
import com.louisgeek.javamail.email.EmailService;
import com.louisgeek.javamail.email.abstracts.AbstractProtocolSmtp;

/**
 * Created by classichu on 2018/3/14.
 */

public class NeteaseProtocolSmtp extends AbstractProtocolSmtp {
    private static final String MAIL_HOST = "smtp.163.com";
    private static final int MAIL_HOST_PORT = 25;
    private static final int MAIL_HOST_PORT_SSL = 465;// 465 / 994

    public NeteaseProtocolSmtp(EmailService emailService) {
        super(emailService);
    }

    @Override
    public EmailProtocol setupEmailProtocol() {
        return EmailProtocol.create(MAIL_HOST, MAIL_HOST_PORT, MAIL_HOST_PORT_SSL);
    }
}
