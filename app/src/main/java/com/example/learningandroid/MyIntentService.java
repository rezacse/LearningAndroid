package com.example.learningandroid;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import java.io.FileOutputStream;


public class MyIntentService extends IntentService {
   private static final String EXTRA_PARAM2 = "com.example.learningandroid.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            LogHelper.logThreadId("start onHandleIntent");

            String messageText = intent.getStringExtra("MessageText");
            FileOutputStream outStream = FileHelper.openOutStream(this, "servicedata.txt");
            for (int i = 0; i < 5; i++) {
                FileHelper.slowWrite(outStream, messageText);
            }
            FileHelper.closeOutStream(outStream);

            LogHelper.logThreadId("end onHandleIntent");

        }
    }


}
