package com.example.learningandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelper.LogCallback(this, "onCreate");
        setContentView(R.layout.activity_2);
        setUiEvents();
    }

    //<editor-fold des="setUiEvents">

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        boolean handled=true;

        int id = item.getItemId();
        switch (id){
            case R.id.action_activity3:
                OnClickedMenuShowActivity3();
                break;

            case R.id.action_showToast:
                OnClickedMenuShowToast();
                break;

            case R.id.action_Close:
                OnClickedMenuClose();
                break;

            default:
                handled = super.onOptionsItemSelected(item);
        }

        return handled;
    }

    private void OnClickedMenuShowActivity3(){
        Intent intent  = new Intent(this, ActivityResult.class);
        startActivity(intent);
    }
    private void OnClickedMenuShowToast(){
        Toast toast = Toast.makeText(this, getString(R.string.thisIsAToast),Toast.LENGTH_LONG);
        toast.show();
    }

    private void OnClickedMenuClose(){
        finish();
    }

    //</editor-fold>

   // @Override
//    public void onClick(View view) {
//
//        Button button = (Button)view;
//        int id = button.getId();
//
//        switch (id){
//            case R.id.topFirstButton:
//                handleFirstButtonClick(button);
//                break;
//
//            case R.id.topSecondButton:
//                handleSecondButtonClick(button);
//                break;
//        }
//    }


    //<editor-fold des="setUiEvents">

    void setUiEvents(){
        Button topFirstButton = (Button) findViewById(R.id.topFirstButton);
        topFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity2.this.handleFirstButtonClick((Button)view);
            }
        });

        Button topSecondButton = (Button) findViewById(R.id.topSecondButton);
        topSecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSecondButtonClick((Button)view);
            }
        });
    }

    private void handleFirstButtonClick(Button button) {
        TextView textView = (TextView) findViewById(R.id.firstTopTextView);
        textView.setText("Clicked First!!");
    }


    private void handleSecondButtonClick(Button button) {
        TextView textView = (TextView) findViewById(R.id.secondTopTextView);
        textView.setText("Clicked Second!!");
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
