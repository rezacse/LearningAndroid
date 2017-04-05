package com.example.learningandroid;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by ### on 21-03-2017.
 */

public class MyHandler extends Handler {

    public MyHandler(Looper looper){
        super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
        String messageText = (String) msg.obj;
        long threadId = Thread.currentThread().getId();
        Log.d(MainActivity.LogTag, String.format("MyHandler[running on thread %d] - received %s", threadId, messageText));
    }
}
