package com.ardeleanlucian.systemapplication.database;

import android.provider.BaseColumns;

/**
 * Created by Ardelean Lucian on 1/4/2018.
 */

public final class DatabaseContract {

    // The contract class is not meant to be instantiated
    private DatabaseContract() {}

    /** Inner static class to hold database information **/
    public static class Database {

        public static final String DATABASE_NAME = "SystemApplication.db";
    }
    
    /** Inner static class that defines the CallHistory table contents **/
    public static class CallHistory implements BaseColumns {

        public static final String TABLE_NAME = "call_history";
        public static final String COLUMN_NAME_NUMBER = "number";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_DURATION = "duration";
        public static final String COLUMN_NAME_DIRECTION = "direction";
    }

    /** Create call_history table command **/
    public static final String SQL_CREATE_TABLE_CALL_HISTORY =
            "CREATE TABLE" + DatabaseContract.CallHistory.TABLE_NAME + " (" +
                    DatabaseContract.CallHistory._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.CallHistory.COLUMN_NAME_NUMBER + " TEXT," +
                    DatabaseContract.CallHistory.COLUMN_NAME_DATE + " TEXT," +
                    DatabaseContract.CallHistory.COLUMN_NAME_DURATION + " TEXT," +
                    DatabaseContract.CallHistory.COLUMN_NAME_DIRECTION + " TEXT)";

    /** Drop call_history table command **/
    public static final String SQL_DROP_TABLE_CALL_HISTORY =
            "DROP TABLE IF EXISTS" + DatabaseContract.CallHistory.TABLE_NAME;
}
