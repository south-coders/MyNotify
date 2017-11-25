package ir.southcoders.mynotify.view.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import ir.southcoders.mynotify.R;
import ir.southcoders.mynotify.common.utils.dbflow.NotificationTable;

/**
 * Created by Farzad on 11/25/2017.
 */

public final class MainActivity extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));

        List<NotificationTable> notificationList = SQLite.select(). from(NotificationTable.class).queryList();
        Toast.makeText(this, "Saved Notifications Count : "+notificationList.size(), Toast.LENGTH_SHORT).show();

        for (NotificationTable sn : notificationList){
            Log.w("Id : ",sn.getId()+"");
            Log.w("Title : ",sn.getTitle()+"");
            Log.w("Data : ",sn.getDate()+"");
            Log.w("ImagePath : ",sn.getImagePath()+"");
            Log.w("Package : ",sn.getPackage()+"");
            Log.w("Ticker : ",sn.getTicker()+"");
            Log.w("Text : ",sn.getText()+"");
            Log.w("____________","____________");
        }
    }
    private BroadcastReceiver onNotice= new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show();
            String pack = intent.getStringExtra("package");
            String ticker = intent.getStringExtra("ticker");
            String title = intent.getStringExtra("title");
            String text = intent.getStringExtra("text");

            Log.i("Package",pack);
            Log.i("Ticker",ticker);
            Log.i("Title",title);
            Log.i("Text",text);

            NotificationTable notification = new NotificationTable();
            notification.setTitle(title);
            notification.setTicker(ticker);
            notification.setPackage(pack);
            notification.setDate("Date");
            notification.setText(text);
            notification.setImagePath("ImagePath");
            notification.save();
        }
    };
}
