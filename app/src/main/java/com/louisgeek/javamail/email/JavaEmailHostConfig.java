package com.louisgeek.javamail.email;

/**
 * Created by classichu on 2018/3/14.
 */

public class JavaEmailHostConfig {
    private  String emailHost;
    private  int emailHostPort;
    private  int emailHostPortSsl;



    public JavaEmailHostConfig(String emailHost, int emailHostPort, int emailHostPortSsl) {
        this.emailHost = emailHost;
        this.emailHostPort = emailHostPort;
        this.emailHostPortSsl = emailHostPortSsl;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public int getEmailHostPort() {
        return emailHostPort;
    }

    public int getEmailHostPortSsl() {
        return emailHostPortSsl;
    }

}
