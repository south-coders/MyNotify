package ir.southcoders.mynotify.common.utils.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ir.southcoders.mynotify.model.NotificationModel;

/**
 * Created by Farzad on 2/17/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;

    private static final String DATABASE_NAME = "mynotify";

    private static final String TABLE_NOTIFICATIONS = "contacts";

    private static final String KEY_ID = "id";
    private static final String KEY_PACKAGE = "package";
    private static final String KEY_TICKER = "ticker";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE = "date";
    private static final String KEY_IMAGE_PATH = "image_path";
    private static final String KEY_TEXT = "text";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NOTIFICATIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_PACKAGE + " TEXT,"
                + KEY_TICKER + " TEXT,"
                + KEY_TITLE + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_IMAGE_PATH + " TEXT,"
                + KEY_TEXT + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATIONS);
        onCreate(db);
    }

    public void addNotification(NotificationModel notificationModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, notificationModel.getId());
        values.put(KEY_DATE, notificationModel.getDate());
        values.put(KEY_IMAGE_PATH, notificationModel.getImagePath());
        values.put(KEY_PACKAGE, notificationModel.getPackage());
        values.put(KEY_TEXT, notificationModel.getText());
        values.put(KEY_TICKER, notificationModel.getTicker());
        values.put(KEY_TITLE, notificationModel.getTitle());

        // Inserting Row
        db.insert(TABLE_NOTIFICATIONS, null, values);
        db.close(); // Closing database connection
    }

    public NotificationModel getNotification(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NOTIFICATIONS
                , new String[]{KEY_ID, KEY_PACKAGE, KEY_TICKER, KEY_TICKER, KEY_TITLE, KEY_DATE, KEY_IMAGE_PATH, KEY_TEXT}
                , KEY_ID + "=?"
                , new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setId(cursor.getString(0));
        notificationModel.setPackage(cursor.getString(1));
        notificationModel.setTicker(cursor.getString(2));
        notificationModel.setTitle(cursor.getString(3));
        notificationModel.setDate(cursor.getString(4));
        notificationModel.setImagePath(cursor.getString(5));
        notificationModel.setText(cursor.getString(6));

        return notificationModel;
    }

    public List<NotificationModel> getAllNotifications() {
        List<NotificationModel> notificationList = new ArrayList<NotificationModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTIFICATIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NotificationModel notificationModel = new NotificationModel();
                notificationModel.setId(cursor.getString(0));
                notificationModel.setPackage(cursor.getString(1));
                notificationModel.setTicker(cursor.getString(2));
                notificationModel.setTitle(cursor.getString(3));
                notificationModel.setDate(cursor.getString(4));
                notificationModel.setImagePath(cursor.getString(5));
                notificationModel.setText(cursor.getString(6));
                // Adding contact to list
                notificationList.add(notificationModel);
            } while (cursor.moveToNext());
        }

        // return contact list
        return notificationList;
    }

    public int getAllNotificationsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NOTIFICATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
