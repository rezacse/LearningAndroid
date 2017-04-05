package com.example.learningandroid;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Jim on 9/20/13.
 */
public class FileHelper {
  protected static FileOutputStream openOutStream(Context Context, String filename) {
    FileOutputStream outStream = null;

    try {
      outStream = Context.openFileOutput("testout.dat", android.content.Context.MODE_PRIVATE);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return outStream;
  }

  protected static void closeOutStream(FileOutputStream outStream) {
    try {
      if (outStream != null)
        outStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected static void slowWrite(FileOutputStream outStream, String buffer) {
    try {
      outStream.write(buffer.getBytes());
      Thread.sleep(1500, 0);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
