package com.example.learningandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ManageLocationListenerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        LogHelper.logThreadId("ManageLocationListenerReceiver.onReceive");

        String action = intent.getAction();

        if(Intent.ACTION_AIRPLANE_MODE_CHANGED.equalsIgnoreCase(action)){
            boolean isOn = intent.getBooleanExtra("state", false);

            String serviceAction = isOn? MonitoringService.StopAction : MonitoringService.StartAction;
            LogHelper.logThreadId("serviceIntent action = "+ serviceAction);
            //Intent serviceIntent = new Intent(serviceAction);
            Intent serviceIntent = new Intent(context, MonitoringService.class);
            serviceIntent.setAction(serviceAction);
            context.startService(serviceIntent);
        }
    }
}
