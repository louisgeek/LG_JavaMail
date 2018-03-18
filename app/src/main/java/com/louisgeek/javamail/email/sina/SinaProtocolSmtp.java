package com.louisgeek.javamail.email.sina;

import com.louisgeek.javamail.email.EmailProtocol;
import com.louisgeek.javamail.email.EmailService;
import com.louisgeek.javamail.email.abstracts.AbstractProtocolSmtp;

/**
 * Created by louisgeek on 2018/3/19.
 */

public class SinaProtocolSmtp extends AbstractProtocolSmtp {
    private static final String MAIL_HOST = "smtp.sina.com";
    private static final int MAIL_HOST_PORT = 25;
    private static final int MAIL_HOST_PORT_SSL = 465;// 465 / 587

    public SinaProtocolSmtp(EmailService mEmailService) {
        super(mEmailService);
    }

    @Override
    public EmailProtocol setupEmailProtocol() {
        return EmailProtocol.create(MAIL_HOST, MAIL_HOST_PORT, MAIL_HOST_PORT_SSL);
    }
}
