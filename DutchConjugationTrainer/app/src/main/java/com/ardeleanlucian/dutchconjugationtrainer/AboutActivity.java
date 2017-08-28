package com.ardeleanlucian.dutchconjugationtrainer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ardelean on 8/28/17.
 */

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add an action bar with navigation.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("About");

        setContentView(R.layout.about_activity);
    }
}
