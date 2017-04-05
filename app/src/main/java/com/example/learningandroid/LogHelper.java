package com.example.learningandroid;

import android.app.Activity;
import android.util.Log;

/**
 * Created by ### on 09-03-2017.
 */

public class LogHelper {
    public final static String LogTag="LogTag";

    public static void LogCallback(Activity activity, String callback){
        String logMsg = String.format("Activity:%s | Callback:%s", activity.getClass().getSimpleName(), callback);
        Log.d(LogTag, logMsg);
    }

    public static void logThreadId(String message) {
        long processId = android.os.Process.myPid();
        long threadId = Thread.currentThread().getId();
        Log.d(LogTag, String.format("[ Process: %d | Thread: %d] %s", processId, threadId, message));
    }
}
