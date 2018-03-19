package com.louisgeek.javamail.interfaces;


import com.louisgeek.javamail.abstracts.AbstractProtocolSmtp;

/**
 * Created by classichu on 2018/3/14.
 */

public interface IEmailFactory {
     AbstractProtocolSmtp getProtocolSmtp();
}
