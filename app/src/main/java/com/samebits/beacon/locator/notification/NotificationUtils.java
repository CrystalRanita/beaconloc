package com.samebits.beacon.locator.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.samebits.beacon.locator.R;
import com.samebits.beacon.locator.ui.activity.MainNavigationActivity;

import org.altbeacon.beacon.Beacon;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationUtils {
    private static final String TAG = "DeviceNotificationUtils";

    public static void addEventNotification(Context context, int eventID, String title, String content) {
        NotificationManager nManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, MainNavigationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder notifyComp = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.error)
                .setAutoCancel(true)
                .setColor(0xffffd700)
                .setContentTitle(title)
                .setContentText(content)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentIntent(pIntent);

        nManager.notify(eventID, notifyComp.build());
    }
}
