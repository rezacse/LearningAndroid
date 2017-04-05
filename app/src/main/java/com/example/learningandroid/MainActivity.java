package com.example.learningandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    static final String button2EnabledState = "button2EnabledState";
    public static final String LogTag = "LogTag";

    public TextView getMtextView() {

        if(mtextView==null)
            mtextView =(TextView)findViewById(R.id.textViewOnClick);

        return mtextView;
    }

    TextView mtextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelper.LogCallback(this, "onCreate");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setUiEvents();

        if(savedInstanceState!=null)
            restoreState(savedInstanceState);
    }

    private void restoreState(Bundle savedInstanceState) {
        boolean isEnabled = savedInstanceState.getBoolean(button2EnabledState, false);
        Button button2 =(Button) findViewById(R.id.button2);
        button2.setEnabled(isEnabled);
    }

    //<editor-fold des="setUiEvents">

    void setUiEvents(){
        Button firstButton = (Button) findViewById(R.id.button1);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.handleFirstButtonClick((Button)view);
            }
        });

        Button secondButton = (Button) findViewById(R.id.button2);
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSecondButtonClick((Button)view);
            }
        });

        Button dialButton = (Button) findViewById(R.id.dialButton);
        dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01711240732"));
                startActivity(intent);
                Log.d("LogTag", "Intent.ACTION_DIAL");
            }
        });

        Button webButton = (Button) findViewById(R.id.webPageButton);
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://google.com"));
                startActivity(intent);
            }
        });

        Button otherAppButton = (Button) findViewById(R.id.otherAppButton);
        otherAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.learningandroid.action.BrowseApp");
                //intent.setData(Uri.parse("http://google.com"));
                startActivity(intent);
            }
        });

        Button showBroadcastActivityButton = (Button) findViewById(R.id.showBroadcastActivityButton);
        showBroadcastActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleShowBroadcastActivityButtonClick((Button) view);
            }
        });

        Button showThreadIssuesActivityButton = (Button) findViewById(R.id.showThreadIssuesActivityButton);
        showThreadIssuesActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleShowThreadIssuesActivityButtonClick((Button) view);
            }
        });

        Button showServiceActivityButton = (Button) findViewById(R.id.showServiceActivityButton);
        showServiceActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleShowServiceActivityButtonClick();
            }
        });

        Button showBatteryActivityButton = (Button) findViewById(R.id.showBatteryActivityButton);
        showBatteryActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBatteryActivityButtonClick();
            }
        });


    }

    private void handleBatteryActivityButtonClick() {
        Intent intent = new Intent(this, BatteryActivity.class);
        startActivity(intent);
    }

    private void handleShowServiceActivityButtonClick() {
        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
    }

    private void handleShowThreadIssuesActivityButtonClick(Button button) {

        Intent intent = new Intent(this, ThreadIssuesActivity.class);
        startActivity(intent);
    }

    private void handleShowBroadcastActivityButtonClick(Button button) {

        Intent intent = new Intent(this, BroadcastActivity.class);
        startActivity(intent);
    }

    private void handleFirstButtonClick(Button button) {
        mtextView = (TextView) findViewById(R.id.textViewOnClick);
        mtextView.setText("Clicked Button 1!!");

        Button secondButton = (Button) findViewById(R.id.button2);
        secondButton.setEnabled(true);
    }

    private void handleSecondButtonClick(Button button) {
        mtextView = (TextView) findViewById(R.id.textViewOnClick);
        mtextView.setText("Clicked Button 2!!");
    }

    //</editor-fold>


    //<editor-fold des="menuOptions">

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        boolean handled=true;

        int id = item.getItemId();
        switch (id){
            case R.id.action_activity2:
                //onOtherMenuClicked();
                OnClickedMenuShowActivity2();
                break;

            case R.id.action_activity3:
                //onOtherMenuClicked();
                OnClickedMenuActivityResult();
                break;

            case R.id.action_fragment:
                //onOtherMenuClicked();
                OnClickedMenuShowFragment();
                break;

            case R.id.action_tabbed:
                //onOtherMenuClicked();
                onClickedMenuShowTabbed();
                break;

            case R.id.action_other:
                onOtherMenuClicked();
                break;

            case R.id.action_exit:
                onExitMenuClicked();
                break;

            default:
                handled = super.onOptionsItemSelected(item);
        }

        return handled;
    }

    ///show toast
    private void onOtherMenuClicked(){
        Toast toast = Toast.makeText(this, "Other Click", Toast.LENGTH_LONG );
        toast.show();
    }

    private void OnClickedMenuShowActivity2(){
        Intent intent  = new Intent(this, Activity2.class);
        String imageUrl = "put your image url here";
        intent.putExtra("url", imageUrl);
        startActivity(intent);

    }

    private void OnClickedMenuActivityResult() {
        Intent intent = new Intent(this, ActivityResult.class);
        startActivity(intent);
    }

    private void OnClickedMenuShowFragment(){
        Intent intent  = new Intent(this, FragmentActivity.class);
        startActivity(intent);
    }


    private void onClickedMenuShowTabbed() {
        Intent intent  = new Intent(this, TabbedActivity.class);
        startActivity(intent);
    }

    private void onExitMenuClicked(){
        super.finish();
    }

    //</editor-fold>


    //<editor-fold des="life-cycle callbacks">

    @Override
    protected void onStart(){
        super.onStart();
        LogHelper.LogCallback(this, "onStart");
    }


    @Override
    protected void onResume(){
        super.onResume();
        LogHelper.LogCallback(this, "onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        LogHelper.LogCallback(this, "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        LogHelper.LogCallback(this, "onSaveInstanceState");

        Button button2 = (Button) findViewById(R.id.button2);
        outState.putBoolean(button2EnabledState, button2.isEnabled());
    }

    @Override
    protected void onStop(){
        super.onStop();
        LogHelper.LogCallback(this, "onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        LogHelper.LogCallback(this, "onDestroy");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        LogHelper.LogCallback(this, "onRestart");
    }

    //</editor-fold>

}
