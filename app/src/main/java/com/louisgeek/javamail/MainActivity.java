package com.louisgeek.javamail;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.louisgeek.javamail.interfaces.IEmailFactory;
import com.louisgeek.javamail.microsoft.OutlookEmailFactory;
import com.louisgeek.javamail.netease.NeteaseEmailFactory;
import com.louisgeek.javamail.sina.SinaEmailFactory;

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


                        IEmailFactory neteaseEmailFactory = new NeteaseEmailFactory();
                        try {
                            EmailMessage emailMessage = EmailMessage.newBuilder()
                                    .setTitle("杭船业软件有限公司")
                                    .setText("杭船业软件有限公司1")
                                    .setContent("杭船业软件有限公司2")
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .build();

                            neteaseEmailFactory.getProtocolSmtp().send(emailMessage);


                            EmailMessage emailMessage2 = EmailMessage.newBuilder()
                                    .setTitle("杭船业软件有限公司回执")
                                    .setText("杭船业软件有限公司1 回执")
                                    .setContent("杭船业软件有限公司2 回执")
                                    .setCCAddresses(new Address[]{new InternetAddress(ccEmail)})
                                    .setBCCAddresses(new Address[]{new InternetAddress(bccEmail)})
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .setReadReceipt(true)
                                    .build();

                            neteaseEmailFactory.getProtocolSmtp().send(emailMessage2);
                        } catch (AddressException e) {
                            MyLog.e(e.getMessage());
                        }
                        //网易 163 邮箱发 email
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
                            //neteaseEmailFactory.getProtocolSmtp().send(emailMessageWithFile);


                            EmailMessage emailMessageWithImage = EmailMessage.newBuilder()
                                    .setTitle("test_163_email")
                                    .setText("test_163_email text")
                                    .setContent("test_163_email 图文 <img src='cid:" + imagePath.getName() + "'/>")
                                    .setImageFiles(new File[]{imagePath})
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .build();

                            // 图文
                            // neteaseEmailFactory.getProtocolSmtp().send(emailMessageWithImage);

                            EmailMessage emailMessageWithImageAndFile = EmailMessage.newBuilder()
                                    .setTitle("test_163_email")
                                    .setText("test_163_email text")
                                    .setContent("test_163_email 图文 带附件")
                                    .setImageFiles(new File[]{imagePath})
                                    .setFiles(new File[]{file})
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .build();

                            // 图文 带附件
                            //neteaseEmailFactory.getProtocolSmtp().send(emailMessageWithImageAndFile);


                            //
                            IEmailFactory sinaEmailFactory = new SinaEmailFactory();

                            EmailMessage emailMessageS = EmailMessage.newBuilder()
                                    .setTitle("test_sina_email")
                                    .setText("test_sina_email text")
                                    .setContent("test_sina_email 图文 带附件")
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .build();

                            // sinaEmailFactory.getProtocolSmtp().send(emailMessageS);


                            EmailMessage emailMessageSWithImage = EmailMessage.newBuilder()
                                    .setTitle("test_sss_email")
                                    .setText("test_sss_email text")
                                    .setContent("test_sss_email 图文 <img src='cid:" + imagePath.getName() + "'/>")
                                    .setImageFiles(new File[]{imagePath})
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .build();
                            //sinaEmailFactory.getProtocolSmtp().send(emailMessageSWithImage);


                            IEmailFactory outlookEmailFactory = new OutlookEmailFactory();
                            EmailMessage emailMessageSWithImage55 = EmailMessage.newBuilder()
                                    .setTitle("test_out_email")
                                    .setText("test_out_email text")
                                    .setContent("test_out_email 图文 <img src='cid:" + imagePath.getName() + "'/>")
                                    .setImageFiles(new File[]{imagePath})
                                    .setTOAddresses(new Address[]{new InternetAddress(toEmail)})
                                    .build();
                            //outlookEmailFactory.getProtocolSmtp().send(emailMessageSWithImage55);


                        } catch (IOException e) {
                            MyLog.e(e.getMessage());
                        } catch (AddressException e) {
                            MyLog.e(e.getMessage());
                        }


                    }
                });


                //
                Toast.makeText(MainActivity.this, "点击成功！", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
