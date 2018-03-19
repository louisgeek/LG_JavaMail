package com.louisgeek.javamail;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by classichu on 2018/3/14.
 */

public class JavaEmailHelper {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    public static Address[] createAddresses(String address) {
        return new Address[]{createAddress(address)};
    }

    public static Address createAddress(String address) {
        try {
            return new InternetAddress(address);
        } catch (AddressException e) {
            MyLog.e(e.getMessage());
        }
        return null;
    }

    public static Address createAddressAndName(String address, String name) {

        try {
            return new InternetAddress(address, name, "utf-8");
        } catch (UnsupportedEncodingException e) {
            MyLog.e(e.getMessage());
        }

        return null;
    }

    public boolean verifyEmailAddress(String emailAddr) {
        if (TextUtils.isEmpty(emailAddr)) {
            return false;
        }
        Matcher m = EMAIL_PATTERN.matcher(emailAddr);
        return m.matches();
    }
}
