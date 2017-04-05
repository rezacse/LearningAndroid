package com.example.learningandroid;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityResult extends AppCompatActivity {
    static final int ProvideInfoRequestCode=1000;
    static final int TakePictureRequestCode=1010;
    public Uri mPhotoPathUri;



    //<editor-fold desc="views fields Getters">

    public TextView getmClassNameTextView() {
        if(mClassNameTextView==null)
            mClassNameTextView = (TextView) findViewById(R.id.classNameTextView);
        return mClassNameTextView;
    }

    public TextView getmPersonNameTextView() {
        if(mPersonNameTextView==null)
            mPersonNameTextView = (TextView) findViewById(R.id.personNameTextView);
        return mPersonNameTextView;
    }

    public TextView getmEmailTextView() {
        if(mEmailTextView==null)
            mEmailTextView = (TextView) findViewById(R.id.emailNameTextView);
        return mEmailTextView;
    }

    public ImageView getmThumbnailImageView() {
        if(mThumbnailImageView==null)
            mThumbnailImageView = (ImageView) findViewById(R.id.thumbnailImageView);
        return mThumbnailImageView;
    }

    //</editor-fold>

    //<editor-fold desc="private view fields - Access Only Through Getters">

    TextView mClassNameTextView;
    TextView mPersonNameTextView;
    TextView mEmailTextView;
    ImageView mThumbnailImageView;

    //</editor-fold>



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        LogHelper.LogCallback(this, "onCreate");

        setupViews();
    }

    private void setupViews() {
        Button moreInformationButton = (Button) findViewById(R.id.provideInfoButton);
        moreInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleMoreInformationButton((Button) view);
            }
        });

        Button takePictureButton = (Button) findViewById(R.id.takePictureButton);
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTakePictureButton((Button) view);
            }
        });

        Button sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSendButton((Button) view);
            }
        });
    }


    private void handleMoreInformationButton(Button button) {
        Intent intent = new Intent(this, ProvideInfoActivity.class);
        startActivityForResult(intent, ProvideInfoRequestCode);
    }

    private void handleTakePictureButton(Button view) {
        mPhotoPathUri = PhotoHelper.GenerateTimeStampPhotoFileUri();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoPathUri);
        startActivityForResult(intent, TakePictureRequestCode);
    }

    private void handleSendButton(Button view) {
    }

    //<editor-fold desc="On activity result">

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        switch (requestCode) {
            case ProvideInfoRequestCode:
                handleProvideInfoResult(resultCode, resultIntent);
                break;

            case TakePictureRequestCode:
                handleTakePictureResult(resultCode, resultIntent);
                break;
        }
    }

    private void handleProvideInfoResult(int resultCode, Intent resultIntent) {
        if(resultCode==RESULT_OK){
            String className = resultIntent.getStringExtra(ProvideInfoActivity.ClassNameExtra);
            String personName = resultIntent.getStringExtra(ProvideInfoActivity.PersonNameExtra);
            String personEmail = resultIntent.getStringExtra(ProvideInfoActivity.PersonEmailExtra);

            getmClassNameTextView().setText(className);
            getmPersonNameTextView().setText(personName);
            getmEmailTextView().setText(personEmail);
        } else{
            Toast.makeText(this, "User Canceled", Toast.LENGTH_LONG).show();
        }
    }

    private void handleTakePictureResult(int resultCode, Intent resultIntent) {
        if(resultCode==RESULT_OK){
            String photoPathName = mPhotoPathUri.getPath();
            PhotoHelper.addPhotoToMediaStoreAndDisplayThumbnail(photoPathName,this,getmThumbnailImageView());
        } else{
            mPhotoPathUri = null;
            Toast.makeText(this, "User Canceled", Toast.LENGTH_LONG).show();
        }
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
