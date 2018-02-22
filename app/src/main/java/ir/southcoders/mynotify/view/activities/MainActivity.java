package ir.southcoders.mynotify.view.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import ir.southcoders.mynotify.R;
import ir.southcoders.mynotify.common.utils.sqlite.DatabaseHandler;
import ir.southcoders.mynotify.model.NotificationModel;
import ir.southcoders.mynotify.view.adapters.AdapterRecent;

/**
 * Created by Farzad on 11/25/2017.
 */

public final class MainActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler = null;
    RecyclerView recyclerView ;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));

        databaseHandler = new DatabaseHandler(getApplicationContext());

        List<NotificationModel> notificationList = databaseHandler.getAllNotifications();
        Toast.makeText(this, "Saved Notifications Count : "+notificationList.size(), Toast.LENGTH_SHORT).show();

        for (NotificationModel notificationModel : notificationList){
            Log.w("Id : ",notificationModel.getId()+"");
            Log.w("Title : ",notificationModel.getTitle()+"");
            Log.w("Data : ",notificationModel.getDate()+"");
            Log.w("ImagePath : ",notificationModel.getImagePath()+"");
            Log.w("Package : ",notificationModel.getPackage()+"");
            Log.w("Ticker : ",notificationModel.getTicker()+"");
            Log.w("Text : ",notificationModel.getText()+"");
            Log.w("____________","____________");
        }

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        AdapterRecent adapterRecent = new AdapterRecent(getApplicationContext(),notificationList);
        recyclerView.setAdapter(adapterRecent);
    }
    private BroadcastReceiver onNotice= new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (databaseHandler == null){
                databaseHandler = new DatabaseHandler(getApplicationContext());
            }

            //Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show();
            String pack = intent.getStringExtra("package");
            String ticker = intent.getStringExtra("ticker");
            String title = intent.getStringExtra("title");
            String text = intent.getStringExtra("text");

            NotificationModel notificationModel = new NotificationModel();
            notificationModel.setTitle(title);
            notificationModel.setTicker(ticker);
            notificationModel.setPackage(pack);
            notificationModel.setDate("Date");
            notificationModel.setText(text);
            notificationModel.setImagePath("ImagePath");

            databaseHandler.addNotification(notificationModel);
        }
    };
}
