package com.example.learningandroid;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class ThreadIssuesActivity extends AppCompatActivity {

    private static final int MAX_WRITES = 5;

    HandlerThread mHandlerThread;
    Handler mHandler;
    LocationListener mLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_issues);

        setupButtons();

        StrictMode.enableDefaults();

    }

    private void setupButtons() {
        findViewById(R.id.btnWriteToFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnWriteToFileOnClick((Button) view);
            }
        });
        findViewById(R.id.btnWriteToFileAsyncTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnWriteToFileAsyncTaskOnClick((Button) view);
            }
        });
        findViewById(R.id.btnSendMessageToHandler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSendMessageToHandlerOnClick((Button) view);
            }
        });
        findViewById(R.id.btnCallRunnableOnHandler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCallRunnableOnHandlerOnClick((Button) view);
            }
        });
        findViewById(R.id.btnStartLocationMonitoring).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStartLocationMonitoringOnClick((Button) view);
            }
        });
        findViewById(R.id.btnStopLocationMonitoring).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStopLocationMonitoringOnClick((Button) view);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_thread_issues, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mHandlerThread != null) {
            stopLocationMonitoring();
            mHandlerThread.quit();
            mHandlerThread = null;
        }
    }

    private void btnWriteToFileOnClick(Button view) {
        FileOutputStream outStream = openOutStream("testout.dat");
        for (int i = 0; i < MAX_WRITES; i++) {
            simpleWrite(outStream, "Hello World");
        }
        closeOutStream(outStream);
    }

    private void btnWriteToFileAsyncTaskOnClick(Button view) {
        String messageToWrite = "Message to write to file";

        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBarMain);
        initializeProgressBar(pb);
        displayStartedMessage();

        new AsyncTask<String, Integer, Void>() {

            @Override
            protected Void doInBackground(String... params) {
                String outputValue = params[0];
                FileOutputStream outStream = openOutStream("testout.dat");
                for (int i = 0; i < MAX_WRITES; i++) {
                    slowWrite(outStream, outputValue);
                    publishProgress(i);
                }
                closeOutStream(outStream);
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                pb.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                displayCompletionMessage();
                cleanupProgressBar(pb);
            }
        }.execute(messageToWrite);
    }

    private void btnSendMessageToHandlerOnClick(Button view) {
        Handler handler = getMyHandler();
        long threadId = Thread.currentThread().getId();
        String messageText = String.format("Message sent from thread %d", threadId);

        Message msg = handler.obtainMessage(0, messageText);
        msg.sendToTarget();
    }

    private void btnCallRunnableOnHandlerOnClick(Button view) {
        Handler handler = getMyHandler();
        final long callingThreadId = Thread.currentThread().getId();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long runningThreadId = Thread.currentThread().getId();
                String messageText =
                        String.format("Runnable implementation running on thread %d = called from thread &d", runningThreadId, callingThreadId);
                Log.d(MainActivity.LogTag, messageText);
            }
        });
    }

    private void btnStartLocationMonitoringOnClick(Button view) {
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationListener = new MyLocationListener(this);
        HandlerThread handlerThread = getHandlerThread();
        Looper looper = handlerThread.getLooper();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;

        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0.0f, mLocationListener, looper);
    }

    private void btnStopLocationMonitoringOnClick(Button view) {
        stopLocationMonitoring();
    }

    private void stopLocationMonitoring() {
        if (mLocationListener != null) {
            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                return;

            lm.removeUpdates(mLocationListener);
            mLocationListener = null;
        }
    }


    private HandlerThread getHandlerThread(){
        if(mHandlerThread==null){
            mHandlerThread = new HandlerThread("HandlerThread");
            mHandlerThread.start();
        }
        return mHandlerThread;
    }

    private Handler getMyHandler() {
        if(mHandler==null){
            HandlerThread handlerThread =  getHandlerThread();
            mHandler = new MyHandler(handlerThread.getLooper());
        }
        return mHandler;
    }

    protected FileOutputStream openOutStream(String filename) {
        FileOutputStream outStream = null;

        try {
            outStream = openFileOutput("testout.dat", MODE_PRIVATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outStream;
    }

    protected void closeOutStream(FileOutputStream outStream) {
        try {
            if (outStream != null)
                outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void simpleWrite(FileOutputStream outStream, String buffer) {
        try {
            outStream.write(buffer.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void slowWrite(FileOutputStream outStream, String buffer) {
        try {
            outStream.write(buffer.getBytes());
            Thread.sleep(1500, 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void initializeProgressBar(ProgressBar pb) {
        pb.setMax(MAX_WRITES);
        pb.setProgress(0);
        pb.setVisibility(View.VISIBLE);
    }

    protected void cleanupProgressBar(ProgressBar pb) {
        pb.setVisibility(View.INVISIBLE);
    }

    protected void displayStartedMessage() {
        Toast.makeText(this, "File operation started", Toast.LENGTH_SHORT).show();
    }

    protected void displayCompletionMessage() {
        AlertDialog.Builder bldr = new AlertDialog.Builder(ThreadIssuesActivity.this);

        bldr.setTitle("Write to File")
            .setMessage("File operation complete")
            .setIcon(R.drawable.ic_drawer)
            .setCancelable(false)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
    }

}
