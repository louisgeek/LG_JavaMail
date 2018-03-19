package com.louisgeek.javamail;

import android.util.Log;

/**
 * Created by louisgeek on 2018/3/19.
 */

public class MyLog {
    private static final boolean isLog = true;
    private static final String TAG = "MyLog";

    public static void e(String msg) {
        if (isLog) {
            Log.e(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isLog) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (isLog) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (isLog) {
            Log.w(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (isLog) {
            Log.v(TAG, msg);
        }
    }


    public static void e(String tag, String msg) {
        if (isLog) {
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isLog) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isLog) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isLog) {
            Log.w(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isLog) {
            Log.v(tag, msg);
        }
    }
}
