package com.example.learningandroid;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;

/**
 * Created by ### on 21/03/2017.
 */
public class MyNotificationHelper {
  public static final int LOCATION_NOTIFICATION_ID = 1;

  public static void displayNotification(Context context, Location location) {

    String mapUri = String.format("http://maps.google.com/maps?q=%.6f,%.6f",
            location.getLatitude(), location.getLongitude());
    Uri uri = Uri.parse(mapUri);
    Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mapIntent, 0);

    String message = formatNotificationMessage(location.getProvider(), location.getLatitude(), location.getLongitude());

    Notification.Builder builder = new Notification.Builder(context);
    builder.setContentTitle("Current Location")
            .setContentText(message)
            .setSmallIcon(getNotificationIcon())
            .setContentIntent(pendingIntent);

    Notification notification = builder.getNotification();

    NotificationManager mgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

//    String tag = this.getClass().getName();
    mgr.notify(LOCATION_NOTIFICATION_ID, notification);
  }

  public static void removeNotification(Context context) {
    NotificationManager mgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    mgr.cancel(LOCATION_NOTIFICATION_ID);
  }

  private static String formatNotificationMessage(String provider, double latitude, double longitude) {
    return String.format("%.6f/%.6f Provider:%s", latitude, longitude, provider);
  }

  private static int getNotificationIcon() {
    boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
    return useWhiteIcon ? R.drawable.ic_drawer : R.drawable.notification;
  }

}
