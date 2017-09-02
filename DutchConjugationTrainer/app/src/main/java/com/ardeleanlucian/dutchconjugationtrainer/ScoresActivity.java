package com.ardeleanlucian.dutchconjugationtrainer;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

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
        scoresChart.setHorizontalBarChart();
    }

    /** Define actions to be taken when clicking on the 'Reset scores' button */
    public void onClickReset(View view) {
        Context context = view.getContext().getApplicationContext();
        (new Scores(context)).resetScores();

        // Give user a confirmation message
        Snackbar snackBar = Snackbar.make(view, "All scores have been reset!",
                Snackbar.LENGTH_SHORT);
        snackBar.show();

        // Set and display the scores chart
        ScoresChart scoresChart = new ScoresChart(findViewById(R.id.scores_activity));
        scoresChart.setHorizontalBarChart();
    }
}