package com.louisgeek.javamail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.louisgeek.javamail.email.interfaces.IEmailFactory;
import com.louisgeek.javamail.email.netease.NeteaseEmailFactory;
import com.louisgeek.javamail.email.tencent.TencentEmailFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executors;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class MainActivity extends AppCompatActivity {
    private String toEmail = "louisgeek@126.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.id_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Executors.newFixedThreadPool(2).execute(new Runnable() {
                    @Override
                    public void run() {

                        //
                        IEmailFactory tencentEmailFactory = new TencentEmailFactory();
                        try {
                            tencentEmailFactory.getProtocolSmtp().sendHtml("test_163_email", "test_163_email 内容", new Address[]{new InternetAddress(toEmail)});

                        } catch (AddressException e) {
                            e.printStackTrace();
                        }
                        //
                        File filePath = new File(getFilesDir() + "temp" + File.separator);
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
                            neteaseEmailFactory.getProtocolSmtp().sendHtmlWithFile("test_qq_email", "test_qq_email 内容", new File[]{file}, new Address[]{new InternetAddress(toEmail)});
                            //## neteaseEmailFactory.getProtocolSmtp().sendHtmlWithImageAndFile("test_qq_email", "test_qq_email 内容",new File[]{imageFile}, new File[]{file}, new Address[]{new InternetAddress(toEmail)});
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
