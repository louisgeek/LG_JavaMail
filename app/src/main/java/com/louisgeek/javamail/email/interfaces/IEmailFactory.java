package com.louisgeek.javamail.email.interfaces;


import com.louisgeek.javamail.email.abstracts.AbstractProtocolSmtp;

/**
 * Created by classichu on 2018/3/14.
 */

public interface IEmailFactory {
     AbstractProtocolSmtp getProtocolSmtp();
}
