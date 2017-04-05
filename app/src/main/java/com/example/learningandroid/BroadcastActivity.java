package com.example.learningandroid;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class BroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        setUiEvents();
    }

    void setUiEvents(){

        findViewById(R.id.btnMonitorAirplaneModeActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnMonitorAirplaneModeActivityOnClick((Button)view);
            }
        });

        Button monitorAirplaneModeAppButton = (Button) findViewById(R.id.btnMonitorAirplaneModeApp);
        monitorAirplaneModeAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnMonitorAirplaneModeAppOnClick((Button)view);
            }
        });

        Button stopMonitorAirplaneModeAppButton = (Button) findViewById(R.id.btnStopMonitorAirplaneModeApp);
        stopMonitorAirplaneModeAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(MainActivity.LogTag, "Register to monitor airplane Mode - App");
            }
        });
    }

    private void btnMonitorAirplaneModeActivityOnClick(Button button){
        AirplaneModeReceiver receiver = new AirplaneModeReceiver();
        receiver.setLabel("Activity");

        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(receiver, filter);
        Log.d(MainActivity.LogTag, "Register to monitor airplane Mode - Activity");
    }

    private void btnMonitorAirplaneModeAppOnClick(Button button) {
        AirplaneModeReceiver receiver = new AirplaneModeReceiver();
        receiver.setLabel("App");

        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        Context appContext = getApplicationContext();
        appContext.registerReceiver(receiver, filter);

        Log.d(MainActivity.LogTag, "Register to monitor airplane Mode - App");
    }
}
