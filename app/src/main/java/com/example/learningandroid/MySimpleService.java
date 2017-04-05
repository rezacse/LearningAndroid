package com.example.learningandroid;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;

import java.io.FileOutputStream;

public class MySimpleService extends Service {

    HandlerThread mHandlerThread;
    Handler mHandler;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {

        mHandlerThread = new HandlerThread("MySimpleService");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                Intent intent= (Intent) msg.obj;
                int startId = msg.arg1;
                doWork(intent);
                stopSelfResult(startId);
            }
        };
    }

    @Override
    public void onDestroy() {
        mHandlerThread.quit();
        mHandlerThread= null;
        mHandler = null;

        LogHelper.logThreadId("onDestroy Service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        LogHelper.logThreadId("onStartCommand");

        Message msg = mHandler.obtainMessage();
        msg.obj = intent;
        msg.arg1 = startId;
        msg.sendToTarget();

        return START_NOT_STICKY;
    }

    private void doWork(Intent intent) {

        LogHelper.logThreadId("start doWork");

        String messageText = intent.getStringExtra("MessageText");
        FileOutputStream outStream = FileHelper.openOutStream(this, "servicedata.txt");
        for (int i = 0; i < 5; i++) {
            FileHelper.slowWrite(outStream, messageText);
        }
        FileHelper.closeOutStream(outStream);

        LogHelper.logThreadId("end doWork");

    }
}
