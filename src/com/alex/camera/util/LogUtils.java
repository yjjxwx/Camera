package com.alex.camera.util;

import android.util.Log;

/**
 * Created by alex on 14-11-8.
 */
public class LogUtils {
    public static boolean DEBUG = true;

    public static void i(String tag, String message){
        if(DEBUG) {
            Log.i(tag, message);
        }
    }
    public static void d(String tag, String message){
        if(DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void e(String tag, String message){
        if(DEBUG) {
            Log.e(tag, message);
        }
    }

    public static void e(String tag, String message, Throwable tr){
        if(DEBUG){
            Log.e(tag,message,tr);
        }
    }
}
