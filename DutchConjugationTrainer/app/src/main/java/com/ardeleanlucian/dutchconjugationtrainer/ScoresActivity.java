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

        // Set the layout to use
        setContentView(R.layout.scores_activity);

        // Add an action bar and set navigation on it
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Scores");

        // Set and display the scores chart
        ScoresChart scoresChart = new ScoresChart(findViewById(R.id.scores_activity));
        scoresChart.disposeHorizontalBarChart();
    }

    public void onClickReset(View view) {
        // Get context and reset scores
        Context context = view.getContext().getApplicationContext();
        (new ScoresHandler(context)).resetScores();

        // Give user a confirmation message
        Snackbar snackBar = Snackbar.make(view, "All scores have been reset!",
                Snackbar.LENGTH_SHORT);
        snackBar.show();

        // Set and display the updated scores chart
        ScoresChart scoresChart = new ScoresChart(findViewById(R.id.scores_activity));
        scoresChart.disposeHorizontalBarChart();
    }
}