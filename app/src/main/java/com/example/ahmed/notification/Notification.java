package com.example.ahmed.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;

/**
 * Created by AHMED on 20/05/2018.
 */

public class Notification {
    static int id = 0;
    NotificationManager notificationManager;
    NotificationCompat.Builder notification;
    Context context;

    public Notification(Context context) {
        this.context = context;
    }

    public void myNotification() {
        Bitmap picture = BitmapFactory.decodeResource(context.getResources(), R.drawable.ahmed);

        notification = new NotificationCompat.Builder(context);
        notification.setContentTitle("Successful");
        notification.setSmallIcon(R.drawable.ahmed);
        notification.setLargeIcon(picture);
        notification.setContentText("you are welcome");
        notification.setAutoCancel(true);

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification.build());
        id++;

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);
        notification.addAction(R.drawable.ahmed, "ahmed", pendingIntent);
    }

}
