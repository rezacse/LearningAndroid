package com.example.learningandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BatteryLogReceiver extends BroadcastReceiver {
    public BatteryLogReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        BatteryStatus batteryStatus = new BatteryStatus(intent);
        LogHelper.logThreadId(batteryStatus.toString());
    }
}
