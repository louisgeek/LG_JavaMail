package com.louisgeek.javamail.microsoft;

import com.louisgeek.javamail.EmailProtocol;
import com.louisgeek.javamail.EmailService;
import com.louisgeek.javamail.abstracts.AbstractProtocolSmtp;

/**
 * Created by classichu on 2018/3/19.
 */

public class OutlookProtocol extends AbstractProtocolSmtp {
    private static final String MAIL_HOST = "smtp-mail.outlook.com";
    private static final int MAIL_HOST_PORT = 25;
    private static final int MAIL_HOST_PORT_SSL = 587;

    public OutlookProtocol(EmailService mEmailService) {
        super(mEmailService);
    }

    @Override
    protected EmailProtocol setupEmailProtocol() {
        return EmailProtocol.create(MAIL_HOST, MAIL_HOST_PORT, MAIL_HOST_PORT_SSL);
    }
}
