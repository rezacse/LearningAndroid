package com.example.learningandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class BatteryActivity extends AppCompatActivity {

    BatteryLogReceiver mBatteryLogReceiver = null;
    BatteryDisplayReceiver mBatteryDisplayReceiver=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_battery, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBatteryDisplayReceiver = new BatteryDisplayReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBatteryDisplayReceiver, intentFilter);

        LogHelper.logThreadId("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBatteryDisplayReceiver);

        LogHelper.logThreadId("onPause");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = true;
        switch (item.getItemId()) {
            case R.id.action_start_battery_log:
                handleActionStartBatteryLog(item);
                break;
            case R.id.action_stop_battery_log:
                handleActionStopBatteryLog(item);
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }

        return handled;
    }

    private void handleActionStartBatteryLog(MenuItem item) {
        if(mBatteryLogReceiver==null){
            LogHelper.logThreadId("handleActionStartBatteryLog");
            mBatteryLogReceiver = new BatteryLogReceiver();
            IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            registerReceiver(mBatteryLogReceiver, intentFilter);
        }
    }

    private void handleActionStopBatteryLog(MenuItem item) {
        LogHelper.logThreadId("handleActionStopBatteryLog");
        if(mBatteryLogReceiver!=null){
            unregisterReceiver(mBatteryLogReceiver);
            mBatteryLogReceiver = null;
        }
    }

    private void setPowerDisplay(boolean onAC, int powerLevel) {
        int displayResource;
        if(powerLevel > 90) {
            displayResource = onAC ? R.drawable.battery_charging_100 :
                    R.drawable.battery_discharging_100;
        } else if(powerLevel > 65) {
            displayResource = onAC ? R.drawable.battery_charging_075 :
                    R.drawable.battery_discharging_075;
        } else if (powerLevel > 40) {
            displayResource = onAC ? R.drawable.battery_charging_050 :
                    R.drawable.battery_discharging_050;
        } else {
            displayResource = onAC ? R.drawable.battery_charging_025 :
                    R.drawable.battery_discharging_025;
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(displayResource);
    }

    private class BatteryDisplayReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int batteryPlugged = intent.getIntExtra("plugged", -1);
            boolean onAc = batteryPlugged== BatteryManager.BATTERY_PLUGGED_AC;

            int level = intent.getIntExtra("level", -1);
            int maxValue = intent.getIntExtra("scale", -1);
            int chargedPercentage = (level * 100)/maxValue;

            setPowerDisplay(onAc, chargedPercentage);
        }
    }
}
