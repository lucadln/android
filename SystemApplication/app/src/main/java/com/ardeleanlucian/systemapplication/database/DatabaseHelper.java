package com.ardeleanlucian.systemapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.ardeleanlucian.systemapplication.database.DatabaseContract.Database.DATABASE_NAME;
import static com.ardeleanlucian.systemapplication.database.DatabaseContract.SQL_CREATE_TABLE_CALL_HISTORY;
import static com.ardeleanlucian.systemapplication.database.DatabaseContract.SQL_DROP_TABLE_CALL_HISTORY;

/**
 * Created by Ardelean Lucian on 1/4/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // If the database schema is changed, the database version must be incremented
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /** Actions to take if the database doesn't exist yet **/
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_CALL_HISTORY);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DROP_TABLE_CALL_HISTORY);
        onCreate(db);
    }
}
