

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groovy
 allprojects {
 repositories {
    jcenter()
    maven { url "https://jitpack.io" }
 }
}
```



Step 2. Add the dependency [![img](https://jitpack.io/v/louisgeek/LouisJavaMail.svg)](https://jitpack.io/#louisgeek/LouisJavaMail)

```
dependencies {
	        compile 'com.github.louisgeek:LouisJavaMail:x.x.x'
	}
```



使用方法：

1 继承 AbstractProtocolSmtp 配置邮箱Smtp服务信息

```java
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
```

2 实现 IEmailFactory 工厂 配置账户信息
```java
public class NeteaseEmailFactory implements IEmailFactory {
    private static final String USER_NAME = "bsoft_app@163.com";
    private static final String AUTH_CODE = "xxx";//163 的授权码
    //发送方的邮箱
    private static final String FROM_EMAIL = "bsoft_app@163.com";
    //发送方姓名
    private static final String FROM_NAME = "louisgeek_netease";
    
    @Override
    public AbstractProtocolSmtp getProtocolSmtp() {
        return new NeteaseProtocolSmtp(EmailService.create(USER_NAME, AUTH_CODE, FROM_EMAIL, FROM_NAME));
    }
}
```


3 发送邮件
```java
// 普通
 IEmailFactory neteaseEmailFactory = new NeteaseEmailFactory();
                        try {
                            EmailMessage emailMessage = EmailMessage.newBuilder()
                                    .setTitle("杭船业软件有限公司")
                                    .setText("杭船业软件有限公司1")
                                    .setContent("杭船业软件有限公司2")
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .build();

                            neteaseEmailFactory.getProtocolSmtp().send(emailMessage);

// 
             File imagePath = new File(Environment.getExternalStorageDirectory() + File.separator + "temp" + File.separator + "zfq.jpg");
                        //
                        File filePath = new File(getFilesDir() + File.separator + "temp" + File.separator);
                        if (!filePath.exists()) {
                            filePath.mkdirs();
                        }
                        File file = new File(filePath, "test_email.txt");
                        try {
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            fileOutputStream.write("test_email content 中文".getBytes("utf-8"));
                            fileOutputStream.close();
                            //
                            EmailMessage emailMessageWithFile = EmailMessage.newBuilder()
                                    .setTitle("test_163_email")
                                    .setText("test_163_email text")
                                    // .setContent("test_163_email 带附件")
                                    //  .setFiles(new File[]{file})
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .build();

                            //带附件
                            neteaseEmailFactory.getProtocolSmtp().send(emailMessageWithFile);

//

                            EmailMessage emailMessageWithImage = EmailMessage.newBuilder()
                                    .setTitle("test_163_email")
                                    .setText("test_163_email text")
                                    .setContent("test_163_email 图文 <img src='cid:" + imagePath.getName() + "'/>")
                                    .setImageFiles(new File[]{imagePath})
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .build();

                            // 图文
                            neteaseEmailFactory.getProtocolSmtp().send(emailMessageWithImage);
//
                            EmailMessage emailMessageWithImageAndFile = EmailMessage.newBuilder()
                                    .setTitle("test_163_email")
                                    .setText("test_163_email text")
                                    .setContent("test_163_email 图文 带附件")
                                    .setImageFiles(new File[]{imagePath})
                                    .setFiles(new File[]{file})
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .build();

                            // 图文 带附件
                            neteaseEmailFactory.getProtocolSmtp().send(emailMessageWithImageAndFile);

```







详见博客：

http://blog.csdn.net/RichieZhu/article/details/79578483

