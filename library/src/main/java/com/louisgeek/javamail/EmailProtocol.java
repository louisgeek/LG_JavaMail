package com.louisgeek.javamail;

/**
 * Created by louisgeek on 2018/3/18.
 */

public class EmailProtocol {
    private String emailHost;
    private int emailHostPort;
    private int emailHostPortSSL;
    private boolean debug;

    public static EmailProtocol create(String emailHost) {
        return new EmailProtocol(emailHost, 0, 0);
    }

    public static EmailProtocol create(String emailHost, int emailHostPort) {
        return new EmailProtocol(emailHost, emailHostPort, 0);
    }

    public static EmailProtocol create(String emailHost, int emailHostPort, int emailHostPortSSL) {
        return new EmailProtocol(emailHost, emailHostPort, emailHostPortSSL);
    }

    private EmailProtocol(String emailHost, int emailHostPort, int emailHostPortSSL) {
        this.emailHost = emailHost;
        this.emailHostPort = emailHostPort;
        this.emailHostPortSSL = emailHostPortSSL;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public int getEmailHostPort() {
        return emailHostPort;
    }

    public int getEmailHostPortSSL() {
        return emailHostPortSSL;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isDebug() {
        return debug;
    }
}
