package com.example.learningandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.io.FileOutputStream;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        LogHelper.logThreadId("ServiceActivity Thread");

        setupButtons();
    }

    private void setupButtons() {
        findViewById(R.id.btnDoLongRunningWork).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDoLongRunningWorkOnClick((Button) v);
            }
        });
        findViewById(R.id.btnStartMonitoring).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStartMonitoringOnClick((Button) v);
            }
        });
        findViewById(R.id.btnStopMonitoring).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStopMonitoringOnClick((Button) v);
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    private void btnDoLongRunningWorkOnClick(Button button) {
        LogHelper.logThreadId("btnDoLongRunningWorkOnClick");
        String messageText = "This is the message from the Activity";

        Intent intent = new Intent(this, MySimpleService.class);
//        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("MessageText", messageText);
        startService(intent);
    }

    private void btnStartMonitoringOnClick(Button button) {
        LogHelper.logThreadId("btnStartMonitoringOnClick");

        Intent intent = new Intent(this, MonitoringService.class);
//        Intent intent = new Intent();
        intent.setAction(MonitoringService.StartAction);
        startService(intent);
    }

    private void btnStopMonitoringOnClick(Button button) {
        LogHelper.logThreadId("btnStopMonitoringOnClick");

        Intent intent = new Intent(this, MonitoringService.class);
        //Intent intent = new Intent();
        intent.setAction(MonitoringService.StopAction);
        startService(intent);
    }
}
