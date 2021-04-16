package com.junxin.freepaykotlin.utils;

/**
 * ：Created by z on 2019-11-15
 * Log 公开
 */

import android.util.Log;


import com.youth.banner.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Lig {

    public static Boolean DEBUG_MODE = BuildConfig.DEBUG? true:false;

    public static String TAG = "zeropay";

    public static void v(String verbose) {
        if (DEBUG_MODE) {
            Log.v(TAG, verbose);
        }

    }

    public static void d(String debug) {
        if (DEBUG_MODE) {
            Log.d(TAG, debug);
        }

    }

    public static void i(String info) {
        if (DEBUG_MODE) {
            Log.i(TAG, info);
        }

    }

    public static void w(String warn) {
        if (DEBUG_MODE) {
            Log.w(TAG, warn);
        }

    }

    public static void e(String error) {

        if (DEBUG_MODE) {
            Log.e(TAG, error);
        }
    }

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.e(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.e(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    public static void printJson(String jsonStr) {
        String tag = TAG;
        String message;
        String headString = "Network 结果:";

        try {
            if (jsonStr.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(jsonStr);
                message = jsonObject.toString(4);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
            } else if (jsonStr.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(jsonStr);
                message = jsonArray.toString(4);
            } else {
                message = jsonStr;
            }
        } catch (JSONException e) {
            message = jsonStr;
        }

        printLine(tag, true);
        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.e(tag, "║ " + line);
        }
        printLine(tag, false);
    }

}
