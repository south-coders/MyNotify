package ir.southcoders.mynotify.common.utils.dbflow

import com.raizlabs.android.dbflow.annotation.Database

/**
 * Created by Farzad on 11/21/2017.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
object AppDatabase {

    val NAME = "AppDatabase"

    val VERSION = 2
}