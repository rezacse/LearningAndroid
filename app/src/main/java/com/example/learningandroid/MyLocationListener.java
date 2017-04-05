package com.example.learningandroid;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by ### on 21-03-2017.
 */


public class MyLocationListener implements LocationListener {
    private Context mContext;

    public MyLocationListener(Context context){
        mContext = context;
    }

    @Override
    public void onLocationChanged(Location location) {
        long threadId = Thread.currentThread().getId();
        Log.d(MainActivity.LogTag, String.format("Provider:%s [running on thread %d] - Location:%.6f/%.6f",
                location.getProvider(), threadId, location.getLatitude(), location.getLongitude()));

        MyNotificationHelper.displayNotification(mContext,location);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle bundle) {
        String statusMessage = "unknown";
        switch (status) {
            case LocationProvider.AVAILABLE:
                statusMessage = "Available";
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                statusMessage = "Temporarily Unavailable";
                break;
            case LocationProvider.OUT_OF_SERVICE:
                statusMessage = "Out of Service";
                break;
        }

        long threadId = Thread.currentThread().getId();
        Log.d(MainActivity.LogTag, String.format("Provider:%s Status changed [running on thread %d] - new status:%s", provider, threadId, statusMessage));
    }

    @Override
    public void onProviderEnabled(String provider) {
        long threadId = Thread.currentThread().getId();
        Log.d(MainActivity.LogTag, String.format("Provider:%s [running on thread %d] ENABLED", provider, threadId));
    }

    @Override
    public void onProviderDisabled(String provider) {
        long threadId = Thread.currentThread().getId();
        Log.d(MainActivity.LogTag, String.format("Provider:%s [running on thread %d] DISABLED", provider, threadId));
    }
}
