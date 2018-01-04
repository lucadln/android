package com.ardeleanlucian.systemapplication;

import android.content.Context;

import com.ardeleanlucian.systemapplication.database.DatabaseHelper;

/**
 * Created by Ardelean Lucian on 1/4/2018.
 */

public class MainController {

    Context context;

    public MainController(Context context) {
        this.context = context;
    }

    /**
     * Actions to take on activity creation
     */
    public void onCreate() {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
    }
}
