package com.example.learningandroid;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.ActivityCompat;

public class MonitoringService extends Service {
    public static String StartAction = "com.example.learningandroid.action.START_MONITORING";
    public static String StopAction = "com.example.learningandroid.action.STOP_MONITORING";
    HandlerThread mHandlerThread;
    LocationListener mLocationListener = null;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        LogHelper.logThreadId("onCreate Service");

        mHandlerThread = new HandlerThread("MySimpleService");
        mHandlerThread.start();
    }

    @Override
    public void onDestroy() {
        LogHelper.logThreadId("onDestroy Service");

        mHandlerThread.quit();
        mHandlerThread = null;

        MyNotificationHelper.removeNotification(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if(action.equalsIgnoreCase(StartAction)){
            startMonitoring();
        }else if(action.equalsIgnoreCase(StopAction)){
            stopMonitoring();
            stopSelf();
        }

        return START_REDELIVER_INTENT;
    }

    private void startMonitoring() {
        if (mLocationListener == null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;

            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
            mLocationListener = new MyLocationListener(this);
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, mLocationListener, mHandlerThread.getLooper());

            LogHelper.logThreadId("startMonitoring");
        }
    }

    private void stopMonitoring(){
        if(mLocationListener!=null){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                return;

            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
            lm.removeUpdates(mLocationListener);
            mLocationListener = null;

            LogHelper.logThreadId("stopMonitoring");
        }
    }
}
