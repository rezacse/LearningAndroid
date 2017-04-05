package com.example.learningandroid;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ### on 11-03-2017.
 */

public class PhotoHelper {


    public static Uri GenerateTimeStampPhotoFileUri(){
        Uri photoFileUri = null;

        File outputDirectory = GetDirectory(Environment.DIRECTORY_PICTURES, "YourFolder");

        if(outputDirectory!=null) {
            String timeStamp = new SimpleDateFormat("yyyyMMDD_HHmmss").format(new Date());
            String photoFileName = "IMG_" + timeStamp + ".jpg";

            File photoFile = new File(outputDirectory, photoFileName);
            photoFileUri = Uri.fromFile(photoFile);
        }
        return photoFileUri;
    }

    public static File GetDirectory(String inWhichFolder, String yourFolderName ) {
        File outputDirectory = null;

        String externalStorageState = Environment.getExternalStorageState();
        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {

            File pictureDirectory = Environment.getExternalStoragePublicDirectory(inWhichFolder);

            outputDirectory = new File(pictureDirectory, yourFolderName);
            if (!outputDirectory.exists()) {
                if (!outputDirectory.mkdirs()) {
                    Log.e(LogHelper.LogTag, "Failed to create directory: " + outputDirectory.getAbsolutePath());
                    outputDirectory = null;
                }
            }
        }
        return outputDirectory;
    }

    public static void addPhotoToMediaStoreAndDisplayThumbnail(String pathName, Activity activity, ImageView imageView) {
        final ImageView thumbnailImageView = imageView;
        final Activity thumbnailActivity = activity;

        String[] filesToScan = {pathName};

        MediaScannerConnection.scanFile(thumbnailActivity, filesToScan, null, new MediaScannerConnection.OnScanCompletedListener(){

            @Override
            public void onScanCompleted(String filePath, Uri uri){
                long id = ContentUris.parseId(uri);
                ContentResolver contentResolver = thumbnailActivity.getContentResolver();

                final Bitmap thumbnail = MediaStore.Images.Thumbnails.getThumbnail(
                        contentResolver, id, MediaStore.Images.Thumbnails.MINI_KIND, null);

                thumbnailActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        thumbnailImageView.setImageBitmap(thumbnail);
                    }
                });
            }
        });
    }
}
