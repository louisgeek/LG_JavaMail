使用方法：

```groovy
  implementation 'com.sun.mail:android-mail:1.6.0'
    implementation 'com.sun.mail:android-activation:1.6.0'
```



```java
  //腾讯 QQ 邮箱发 email
                        IEmailFactory tencentEmailFactory = new TencentEmailFactory();
                        try {
                            tencentEmailFactory.getProtocolSmtp().sendHtml("test_qq_email", "test_qq_email 内容", new Address[]{new InternetAddress(toEmail)});

                        } catch (AddressException e) {
                            e.printStackTrace();
                        }
                    //网易 163 邮箱发 email
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
          //带附件                       
                            neteaseEmailFactory.getProtocolSmtp().sendHtmlWithFile("test_163_email", "test_163_email 内容", new File[]{file}, new Address[]{new InternetAddress(toEmail)});
       // 图文 带附件                    //neteaseEmailFactory.getProtocolSmtp().sendHtmlWithImageAndFile("test_163_email", "test_163_email 内容",new File[]{imageFile}, new File[]{file}, new Address[]{new InternetAddress(toEmail)});
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (AddressException e) {
                            e.printStackTrace();
                        }


                    }
                });

```







详见博客：

http://blog.csdn.net/RichieZhu/article/details/79578483

