package com.runtai.mvpproject.comment.util;

import android.util.Log;

public class Logger {

    /**
     * 上线前改为false，则所有log都会消失
     */
    public static boolean isShowLog = true;

    public static void v(String tag, String msg) {
        if (isShowLog) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isShowLog) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isShowLog) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isShowLog) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isShowLog) {
            Log.e(tag, msg);
        }
    }
}
