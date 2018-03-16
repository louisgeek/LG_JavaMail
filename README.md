## 1 开始使用

- 这里使用的 JavaMail for Android 1.6.0 版本（我是 jdk 1.8）

  https://javaee.github.io/javamail/Android

- java 版 jar 见官方地址（请使用 jdk 1.7 测试）

  https://javaee.github.io/javamail/

```groovy
  //javamail(android) 1.6.1 有问题
    compile 'com.sun.mail:android-mail:1.6.0'
    compile 'com.sun.mail:android-activation:1.6.0'
```


## 2  pop3 smtp imap 协议

- SMTP：简单邮件传输协议，用于发送电子邮件的传输协议；
- POP3：用于接收电子邮件的标准协议；
- IMAP：互联网消息协议，是 POP3 的替代协议。

**POP3**

```html
POP3 是Post Office Protocol 3的简称，即邮局协议的第3个版本,它规定怎样将个人计算机连接到Internet的邮件服务器和下载电子邮件的电子协议。它是因特网电子邮件的第一个离线协议标准,POP3允许用户从服务器上把邮件存储到本地主机（即自己的计算机）上,同时删除保存在邮件服务器上的邮件，而POP3服务器则是遵循POP3协议的接收邮件服务器，用来接收电子邮件的。
```



**SMTP**

```
SMTP 的全称是“Simple Mail Transfer Protocol”，即简单邮件传输协议。它是一组用于从源地址到目的地址传输邮件的规范，通过它来控制邮件的中转方式。SMTP 协议属于 TCP/IP 协议簇，它帮助每台计算机在发送或中转信件时找到下一个目的地。SMTP 服务器就是遵循 SMTP 协议的发送邮件服务器。 
　　SMTP 认证，简单地说就是要求必须在提供了账户名和密码之后才可以登录 SMTP 服务器，这就使得那些垃圾邮件的散播者无可乘之机。 
　　增加 SMTP 认证的目的是为了使用户避免受到垃圾邮件的侵扰。
```



**IMAP**

```
IMAP 全称是Internet Mail Access Protocol，即交互式邮件存取协议，它是跟POP3类似邮件访问标准协议之一。不同的是，开启了IMAP后，您在电子邮件客户端收取的邮件仍然保留在服务器上，同时在客户端上的操作都会反馈到服务器上，如：删除邮件，标记已读等，服务器上的邮件也会做相应的动作。所以无论从浏览器登录邮箱或者客户端软件登录邮箱，看到的邮件以及状态都是一致的。
```





##  IMAP和POP3有什么区别

```
POP3 协议允许电子邮件客户端下载服务器上的邮件，但是在客户端的操作（如移动邮件、标记已读等），不会反馈到服务器上，比如通过客户端收取了邮箱中的3封邮件并移动到其他文件夹，邮箱服务器上的这些邮件是没有同时被移动的 。
而 IMAP  提供webmail 与电子邮件客户端之间的双向通信，客户端的操作都会反馈到服务器上，对邮件进行的操作，服务器上的邮件也会做相应的动作。
同时， IMAP 像 POP3 那样提供了方便的邮件下载服务，让用户能进行离线阅读。IMAP 提供的摘要浏览功能可以让你在阅读完所有的邮件到达时间、主题、发件人、大小等信息后才作出是否下载的决定。此外， IMAP 更好地支持了从多个不同设备中随时访问新邮件。总之， IMAP  整体上为用户带来更为便捷和可靠的体验。 POP3  更易丢失邮件或多次下载相同的邮件，但 IMAP 通过邮件客户端与webmail 之间的双向同步功能很好地避免了这些问题。
 注：若在web邮箱中设置了“保存到已发送”，使用客户端POP服务发信时，已发邮件也会自动同步到网页端“已发送”文件夹内。
```

![https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/imap_pop3_info.png](https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/imap_pop3_info.png)

## 3 常用邮箱服务商协议地址和端口

###  网易

163 邮箱



![https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/netease_protocol_port.png](https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/netease_protocol_port.png)



126 邮箱

![https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/netease_protocol_port_126.png](https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/netease_protocol_port_126.png)

### 腾讯QQ邮箱

![https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/tencent_protocol_port.png](https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/tencent_protocol_port.png)



## 4 启用 pop3 / smtp / imap服务

- 按需开启对应服务



网易：会让你设置授权码

![https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/netease_protocol_config.jpg](https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/netease_protocol_config.jpg)



腾讯：会生成授权码

![https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/tencent_protocol_config.png](https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/tencent_protocol_config.png)

**注意**：修改QQ密码或者QQ独立密码，会导致授权码失效，继续使用会报 535 错误


![https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/tencent_auth_code_tips.png](https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/tencent_auth_code_tips.png)



## 5 具体发送邮箱步骤


// 1 定义 Properties 进行配置

```java
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
```



// 2 创建 Session ，获取会话环境

```java
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
```



// 3 创建 MimeMessage 实例对象   ，封装邮件信息

 ```java
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
        
        } catch (MessagingException mex) {
            Log.e(TAG, "getMimeMessage:" + mex.getMessage());
            Log.e(TAG, "getMimeMessage:" + mex.getNextException());
        }
 ```

- subtype 关系图

![https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/subtype.png](https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/subtype.png)



// 4 获得 Transport 实例对象   ，发送邮件

```java
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
```



## 6 测试

![https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/163.png](https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/163.png)

![https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/qq.png](https://gitee.com/louisgeek/ResourceStore/raw/master/javamail/qq.png)

 ## ## 7 常见报错



一、 报 554 异常

1 使用的是授权码，不是密码

2 如果使用 Transport.send 静态方法，改成  session.getTransport().sendMessage(),具体详见代码



二、 报 530 异常

1 qq邮箱需要加入下面的代码

```java
 props.put("mail.smtp.starttls.enable", "true");`
```



