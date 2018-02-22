package ir.southcoders.mynotify.common.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Farzad on 11/25/2017.
 */

public class NotificationService extends NotificationListenerService {

    Context context;

    @Override

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Toast.makeText(context, "NotificationService on created", Toast.LENGTH_SHORT).show();
    }

    @Override

    public void onNotificationPosted(StatusBarNotification sbn) {
        if (!sbn.isOngoing()) {
            String pack = sbn.getPackageName();
            String ticker = "";
            if (sbn.getNotification().tickerText != null) {
                ticker = sbn.getNotification().tickerText.toString();
            }

            Bundle extras = sbn.getNotification().extras;
            String title = "";
            String text = "";

            if (extras.getString("android.title") != null) {
                title = extras.getString("android.title");
            }
            if (extras.getString("android.text") != null) {
                text = extras.getString("android.text");
            }

            //ferestadan data ha be mainActivity ba Broadcast
            Intent msgrcv = new Intent("Msg");
            msgrcv.putExtra("package", pack);
            msgrcv.putExtra("ticker", ticker);
            msgrcv.putExtra("title", title);
            msgrcv.putExtra("text", text);

            LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv);
        }
    }

    @Override

    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("Msg", "Notification Removed");
    }
}
