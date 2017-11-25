package ir.southcoders.mynotify.common.utils.dbflow;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Farzad on 11/21/2017.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "AppDatabase";

    public static final int VERSION = 1;
}