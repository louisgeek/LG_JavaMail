package com.louisgeek.javamail;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.louisgeek.javamail.email.EmailMessage;
import com.louisgeek.javamail.email.interfaces.IEmailFactory;
import com.louisgeek.javamail.email.netease.NeteaseEmailFactory;
import com.louisgeek.javamail.email.sina.SinaEmailFactory;
import com.louisgeek.javamail.email.tencent.TencentEmailFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class MainActivity extends AppCompatActivity {
    private String toEmail = "louisgeek@126.com";
    private String ccEmail = "louisgeek@163.com";
    private String bccEmail = "louisgeek@qq.com";

    private static final String USER_NAME = "xxx@qq.com";
    //报 535 错误 更改QQ密码以及独立密码会触发授权码过期，需要重新获取新的授权码登录。
    private static final String AUTH_CODE = "xxx";//qq邮箱 授权码
    //发送方的邮箱
    private static final String FROM_EMAIL = "xxx@qq.com";
    //发送方姓名
    private static final String FROM_NAME = "xxx";

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        findViewById(R.id.id_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                executorService.execute(new Runnable() {
                    @Override
                    public void run() {

                        //
                        //腾讯 QQ 邮箱发 email
                        IEmailFactory tencentEmailFactory = new TencentEmailFactory();
                        try {
                            EmailMessage emailMessage = EmailMessage.newBuilder()
                                    .setTitle("test_qq_email")
                                    .setText("test_qq_email text")
                                    .setContent("test_qq_email 内容")
                                    .setCCAddresses(new Address[]{new InternetAddress(ccEmail)})
                                    .setBCCAddresses(new Address[]{new InternetAddress(bccEmail)})
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .build();

                            tencentEmailFactory.getProtocolSmtp().send(emailMessage);


                            EmailMessage emailMessage2 = EmailMessage.newBuilder()
                                    .setTitle("test_qq_email")
                                    .setText("test_qq_email text 回执")
                                    .setContent("test_qq_email 内容 回执")
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .setReadReceipt(true)
                                    .build();

                            tencentEmailFactory.getProtocolSmtp().send(emailMessage2);
                        } catch (AddressException e) {
                            e.printStackTrace();
                        }
                        //网易 163 邮箱发 email
                        File imagePath = new File(Environment.getExternalStorageDirectory() + File.separator + "temp" + File.separator + "homepage-newlogo.png");
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
                            IEmailFactory neteaseEmailFactory = new NeteaseEmailFactory();
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


                            EmailMessage emailMessageWithImage = EmailMessage.newBuilder()
                                    .setTitle("test_163_email")
                                    .setText("test_163_email text")
                                    .setContent("test_163_email 图文 <img src='cid:" + imagePath.getName() + "'/>")
                                    .setImageFiles(new File[]{imagePath})
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .build();

                            // 图文
                            neteaseEmailFactory.getProtocolSmtp().send(emailMessageWithImage);

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


                            //
                            IEmailFactory sinaEmailFactory = new SinaEmailFactory();

                            EmailMessage emailMessageS = EmailMessage.newBuilder()
                                    .setTitle("test_sina_email")
                                    .setText("test_sina_email text")
                                    .setContent("test_sina_email 图文 带附件")
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .build();

                            sinaEmailFactory.getProtocolSmtp().send(emailMessageS);


                            EmailMessage emailMessageSWithImage = EmailMessage.newBuilder()
                                    .setTitle("test_sss_email")
                                    .setText("test_sss_email text")
                                    .setContent("test_sss_email 图文 <img src='cid:" + imagePath.getName() + "'/>")
                                    .setImageFiles(new File[]{imagePath})
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .build();
                            sinaEmailFactory.getProtocolSmtp().send(emailMessageSWithImage);

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (AddressException e) {
                            e.printStackTrace();
                        }


                    }
                });


                //
                Toast.makeText(MainActivity.this, "你已经点击！", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
