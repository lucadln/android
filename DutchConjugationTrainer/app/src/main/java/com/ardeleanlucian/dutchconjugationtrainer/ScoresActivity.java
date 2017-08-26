package com.ardeleanlucian.dutchconjugationtrainer;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ardelean on 8/20/17.
 */

public class ScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add an action bar with navigation.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Scores");

        setContentView(R.layout.scores_activity);
    }

    /** Define actions to be taken when clicking on the 'Reset scores' button */
    public void onClickReset(View view) {
        Context context = view.getContext().getApplicationContext();
        Scores scores = new Scores(context);
        scores.resetScores();
        Snackbar snackBar = Snackbar.make(view, "All scores have been reset!",
                Snackbar.LENGTH_SHORT);
        snackBar.show();
    }
}
