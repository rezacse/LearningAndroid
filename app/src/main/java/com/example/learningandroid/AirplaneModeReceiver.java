package com.example.learningandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ### on 21-03-2017.
 */

public class AirplaneModeReceiver extends BroadcastReceiver {

    private String mLabel = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean state = intent.getBooleanExtra("state", false);

        Log.d(MainActivity.LogTag, String.format("Airplane Mode Changed, Label=%s state=%b", mLabel, state));
    }

    public  void setLabel(String label){
        mLabel = label;
    }
}
