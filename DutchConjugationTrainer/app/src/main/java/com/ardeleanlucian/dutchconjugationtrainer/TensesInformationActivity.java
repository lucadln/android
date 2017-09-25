package com.ardeleanlucian.dutchconjugationtrainer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ardelean on 9/25/17.
 */

public class TensesInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add an action bar with navigation.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Tenses Information");

        setContentView(R.layout.activity_tenses_information);
    }
}
