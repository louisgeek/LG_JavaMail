package com.louisgeek.javamail.email.abstracts;

import android.support.annotation.NonNull;
import android.util.Log;

import com.louisgeek.javamail.email.JavaEmailHelper;
import com.louisgeek.javamail.email.JavaEmailHostConfig;
import com.louisgeek.javamail.email.JavaEmailInfoConfig;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * Created by classichu on 2018/3/14.
 * <p>
 * Smtp 协议抽象类
 */

public abstract class AbstractProtocolSmtp {

    private static final String TAG = "AbstractProtocolSmtp";
    private static final String SUBTYPE_MIXED = "mixed";//纯文本,超文本二选一  内嵌资源 附件
    private static final String SUBTYPE_RELATED = "related";//纯文本&纯文本,超文本二选一  内嵌资源
    private static final String SUBTYPE_ALTERNATIVE = "alternative";//纯文本,超文本二选一

    private JavaEmailInfoConfig mJavaEmailInfoConfig;

    public AbstractProtocolSmtp(JavaEmailInfoConfig javaEmailInfoConfig) {
        mJavaEmailInfoConfig = javaEmailInfoConfig;
    }

    protected abstract JavaEmailHostConfig setupHostConfig();

    public void sendHtml(String title, String content, Address[] toAddresses) {
        sendHtml(title, content, toAddresses, null, null);
    }

    public void sendHtml(String title, String content, Address[] toAddresses, Address[] ccAddresses, Address[] bccAddresses) {
        if (setupHostConfig() == null) {
            Log.e(TAG, "send: setupHostConfig()==null");
            return;
        }
        Address addressAndName = JavaEmailHelper.createAddressAndName(mJavaEmailInfoConfig.getFromEmail(), mJavaEmailInfoConfig.getFromName());
        if (addressAndName == null) {
            Log.e(TAG, "send: addressAndName==null");
            return;
        }
        Properties props = getProperties(addressAndName);
        Session session = getSession(props);
        MimeMessage message = getMimeMessage(title, content, null, null, toAddresses, ccAddresses, bccAddresses, addressAndName, session);
        transportMessage(session, message);
    }

    private void transportMessage(Session session, MimeMessage message) {
        if (message == null) {
            Log.e(TAG, "transportMessage:message==null ");
            return;
        }
        try {
            //Transport 邮件发送器
//   PasswordAuthentication 同样也是设置 USERNAME 和 PASSWORD         Transport.send(msg, USERNAME, PASSWORD);
            //###  Transport.send(msg, USERNAME, AUTH_CODE);
            //###   Transport.send(msg);
            // Transport.send 如果有问题 554 错误  用这个

            Transport transport = session.getTransport();
            //            transport.connect(MAIL_HOST, USERNAME, AUTH_CODE);
            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (NoSuchProviderException e) {
            Log.e(TAG, "transportMessage:" + e.getMessage());
            Log.e(TAG, "transportMessage:" + e.getNextException());
        } catch (MessagingException e) {
            Log.e(TAG, "transportMessage:" + e.getMessage());
            Log.e(TAG, "transportMessage:" + e.getNextException());
        }

    }

    private MimeBodyPart createContentBodyPart(String htmlText, File[] imgFiles) {
        try {
            MimeBodyPart contentBodyPart = new MimeBodyPart();
            //
            //html文本
            MimeBodyPart htmlBodyPart = new MimeBodyPart();
            //二选一 alternative 纯正文 和 超文本
            MimeMultipart htmlMultipart = new MimeMultipart(SUBTYPE_ALTERNATIVE);
            MimeBodyPart htmlBodyPartTemp = new MimeBodyPart();
            htmlBodyPartTemp.setContent(htmlText, "text/html;charset=utf-8");
            htmlMultipart.addBodyPart(htmlBodyPartTemp);
            htmlBodyPart.setContent(htmlMultipart);
            //图片
            MimeBodyPart imgBodyPart = null;
            if (imgFiles != null && imgFiles.length > 0) {
                imgBodyPart = new MimeBodyPart();
                //=== 内嵌 related  把多张图打包起来
                MimeMultipart imgMultipart = new MimeMultipart(SUBTYPE_RELATED);
                for (File imgFile : imgFiles) {
                    MimeBodyPart imgBodyPartTemp = new MimeBodyPart();
                    FileDataSource fileDataSource = new FileDataSource(imgFile);
                    imgBodyPartTemp.setDataHandler(new DataHandler(fileDataSource));
                    //cid
                    imgBodyPartTemp.setContentID(imgFile.getName());
                    imgMultipart.addBodyPart(imgBodyPartTemp);
                }
                imgBodyPart.setContent(imgMultipart);
            }
            //内嵌 related  把图文打包起来
            MimeMultipart contentMultipart = new MimeMultipart(SUBTYPE_RELATED);
            contentMultipart.addBodyPart(htmlBodyPart);
            if (imgBodyPart != null) {
                contentMultipart.addBodyPart(imgBodyPart);
            }
            //
            contentBodyPart.setContent(contentMultipart);
            return contentBodyPart;
        } catch (MessagingException e) {
            Log.e(TAG, "createContentBodyPart:" + e.getMessage());
            Log.e(TAG, "createContentBodyPart:" + e.getNextException());
        }
        return null;
    }

    private MimeBodyPart createFileBodyPart(File[] files) {
        if (files == null || files.length <= 0) {
            return null;
        }
        try {
            MimeBodyPart fileBodyPart = new MimeBodyPart();
            //===内嵌 related  把多文件打包起来
            MimeMultipart fileMultipart = new MimeMultipart(SUBTYPE_RELATED);
            for (File file : files) {
                MimeBodyPart fileBodyPartTemp = new MimeBodyPart();
                FileDataSource fileDataSource = new FileDataSource(file);
                fileBodyPartTemp.setDataHandler(new DataHandler(fileDataSource));
                //fileBodyPartTemp.setFileName(file.getName());
                fileBodyPartTemp.setFileName(MimeUtility.encodeText(file.getName()));
                fileMultipart.addBodyPart(fileBodyPartTemp);
            }
            fileBodyPart.setContent(fileMultipart);
            return fileBodyPart;
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "createFileBodyPart:" + e.getMessage());
        } catch (MessagingException e) {
            Log.e(TAG, "createFileBodyPart:" + e.getMessage());
            Log.e(TAG, "createFileBodyPart:" + e.getNextException());
        }
        return null;
    }

    private MimeMessage getMimeMessage(String title, String htmlText, File[] imageFiless, File[] files, Address[] toAddresses, Address[] ccAddresses, Address[] bccAddresses, Address addressAndName, Session session) {
        try {
            // MimeMessage 邮件类
            MimeMessage msg = new MimeMessage(session);
            // ！！！设置发件人地址，针对邮件来说的，是邮件类的属性，收件人邮箱里可以看到的，知道邮件是由谁发的
//            msg.setFrom(new InternetAddress("创业软件" + "<" + fromAddress + ">"));
            msg.setFrom(addressAndName);
            //多个收信人地址 逗号隔开 可以用 Address InternetAddress 封装
            // msg.addRecipients(Message.RecipientType.TO, TO_EMAIL);
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            //抄送 (抄送一份给发件人，降低 163之类的 报 554 错误（垃圾邮件，屏蔽问题） 的概率)
            msg.addRecipient(Message.RecipientType.CC, addressAndName);
            if (ccAddresses != null) {
                msg.addRecipients(Message.RecipientType.CC, ccAddresses);
            }
            if (bccAddresses != null) {
                //密送
                msg.addRecipients(Message.RecipientType.BCC, bccAddresses);
            }
             msg.setSubject(title, "UTF-8");
            // msg.setSubject(title);//设置主题
            msg.setSentDate(new Date());//设置时间

            //纯文本 msg.setText(content);//设置内容 可以直接使用 setContent 替代，兼容 html代码
            //html  msg.setContent(htmlText, "text/html;charset=UTF-8");
            // msg.setContent("<span style='color:red;'>html邮件测试...</span>","text/html;charset=gbk");
         /*   msg.setContent(
                    "<body><div style='width: 1000px;height: 300px;margin: 0px auto;margin-bottom:20px;border:1px solid #92B0DD;background-color: #FFFFFf;'><h3>这是系统自动发送的邮件，请勿回复!</h3><br/>"+
                            content+"</div></body>",
                    "text/html;charset=UTF-8");*/
            //mixed 混合
            MimeMultipart multipart = new MimeMultipart(SUBTYPE_MIXED);
            // 附件部分
            BodyPart fileBodyPart = createFileBodyPart(files);
            // 创建图文部分
            BodyPart contentBodyPart = createContentBodyPart(htmlText, imageFiless);

            //
            if (fileBodyPart != null) {
                multipart.addBodyPart(fileBodyPart);
            }

            if (contentBodyPart != null) {
                multipart.addBodyPart(contentBodyPart);
            }
            msg.setContent(multipart);
            // 设置回复人 ( 收件人回复此邮件时,不设置就是默认收件人 )
            // msg.setReplyTo();
            // 设置优先级(1:紧急   3:普通    5:低)
            // msg.setHeader("X-Priority", "1");
            // 要求阅读回执(收件人阅读邮件时会提示回复发件人,表明邮件已收到,并已阅读)
            //msg.setHeader("Disposition-Notification-To", fromAddress.toString());
            msg.saveChanges();
            return msg;
        } catch (MessagingException mex) {
            Log.e(TAG, "getMimeMessage:" + mex.getMessage());
            Log.e(TAG, "getMimeMessage:" + mex.getNextException());
        }

        return null;
    }

    @NonNull
    private Session getSession(Properties props) {
        //getDefaultInstance 会复用Properties
        //Session session = Session.getDefaultInstance(props, null);
        //Session session = Session.getInstance(props, null);
        //和 props.put("mail.debug", true); 功能一致
        // session.setDebug(true);
        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mJavaEmailInfoConfig.getUserName(), mJavaEmailInfoConfig.getAuthCode());
            }
        });
    }

    @NonNull
    private Properties getProperties(Address addressAndName) {
        Properties props = new Properties();
        //邮件的协议 pop3 smtp imap
        props.put("mail.transport.protocol", "smtp");
        //== The default host name of the mail server for both Stores and Transports. Used if the mail.xxx.host property isn't set.
        //如果 mail.xxx.host 未设置的时候取它的值
        //props.put("mail.host", MAIL_HOST);
        //== The default user name to use when connecting to the mail server. Used if the mail.protocol.user property isn't set.
        //如果 mail.xxx.user 未设置的时候取它的值
        //props.put("mail.user", FROM_USER);
        props.put("mail.smtp.host", setupHostConfig().getEmailHost());
        props.put("mail.smtp.port", setupHostConfig().getEmailHostPort());
        props.put("mail.smtp.user", addressAndName);//登录邮件服务器的用户名
        //props.put("mail.smtp.class", "mail.smtp.class");
        //qq 邮箱必须要有这个 否则报 530 错误
        props.put("mail.smtp.starttls.enable", "true");
        // 开启debug调试
        props.put("mail.debug", true);
        // 发件人地址，针对服务器来说的  mail.from / mail.smtp.from 邮件被退回（bounced）等时，被退到的邮箱地址 可以设置成 fromAddress
        //服务使用的地址，用来设置邮件的 return 地址。缺省是Message.getFrom()或InternetAddress.getLocalAddress()。mail.user / mail.smtp.user 会优先使用
        props.put("mail.from", addressAndName);
        // props.put("mail.mime.address.strict", true);//严格
        // props.put("mail.store.protocol", "mail.store.protocol");
        // 发送服务器需要身份验证
        props.put("mail.smtp.auth", true);

        //使用SSL安全连接  java 1.8 有问题
       /* props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.socketFactory.port", setupHostConfig().getEmailHostPortSsl());*/
        return props;
    }

    public void sendHtmlWithImage(String title, String htmlText, File[] imageFiles, Address[] toAddresses) {
        sendHtmlWithImage(title, htmlText, imageFiles, toAddresses, null, null);
    }

    public void sendHtmlWithImage(String title, String htmlText, File[] imageFiles, Address[] toAddresses, Address[] ccAddresses,
                                  Address[] bccAddresses) {
        if (setupHostConfig() == null) {
            Log.e(TAG, "sendWithImage: setupHostConfig()==null");
            return;
        }
        Address addressAndName = JavaEmailHelper.createAddressAndName(mJavaEmailInfoConfig.getFromEmail(), mJavaEmailInfoConfig.getFromName());
        if (addressAndName == null) {
            Log.e(TAG, "sendWithImage: addressAndName==null");
            return;
        }
        Properties props = getProperties(addressAndName);
        Session session = getSession(props);
        MimeMessage message = getMimeMessage(title, htmlText, imageFiles, null,
                toAddresses, ccAddresses, bccAddresses, addressAndName, session);
        transportMessage(session, message);
    }

    public void sendHtmlWithFile(String title, String htmlText, File[] files, Address[] toAddresses) {
        sendHtmlWithFile(title, htmlText, files, toAddresses, null, null);
    }

    public void sendHtmlWithFile(String title, String htmlText, File[] files, Address[] toAddresses, Address[] ccAddresses, Address[] bccAddresses) {
        if (setupHostConfig() == null) {
            Log.e(TAG, "sendWithFile: setupHostConfig()==null");
            return;
        }
        Address addressAndName = JavaEmailHelper.createAddressAndName(mJavaEmailInfoConfig.getFromEmail(), mJavaEmailInfoConfig.getFromName());
        if (addressAndName == null) {
            Log.e(TAG, "sendWithFile: addressAndName==null");
            return;
        }
        Properties props = getProperties(addressAndName);
        Session session = getSession(props);
        MimeMessage message = getMimeMessage(title, htmlText, null, files,
                toAddresses, null, null, addressAndName, session);
        transportMessage(session, message);
    }

    public void sendHtmlWithImageAndFile(String title, String htmlText, File[] imageFiles, File[] files, Address[] toAddresses) {
        sendHtmlWithImageAndFile(title, htmlText, imageFiles, files, toAddresses, null, null);
    }

    public void sendHtmlWithImageAndFile(String title, String htmlText, File[] imageFiles, File[] files, Address[] toAddresses, Address[] ccAddresses, Address[] bccAddresses) {
        if (setupHostConfig() == null) {
            Log.e(TAG, "sendWithImageAndFile: setupHostConfig()==null");
            return;
        }
        Address addressAndName = JavaEmailHelper.createAddressAndName(mJavaEmailInfoConfig.getFromEmail(), mJavaEmailInfoConfig.getFromName());
        if (addressAndName == null) {
            Log.e(TAG, "sendWithImageAndFile: addressAndName==null");
            return;
        }
        Properties props = getProperties(addressAndName);
        Session session = getSession(props);
        MimeMessage message = getMimeMessage(title, htmlText, imageFiles, files,
                toAddresses, null, null, addressAndName, session);
        transportMessage(session, message);
    }
}
