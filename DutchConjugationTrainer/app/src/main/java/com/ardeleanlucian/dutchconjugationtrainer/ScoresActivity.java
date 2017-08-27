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
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by ardelean on 8/20/17.
 */

public class ScoresActivity extends AppCompatActivity {

    private String[] tenseOptions = {"Present", "Present Continuous", "Simple Past", "Past Perfect",
            "Condtional", "Conditional Perfect", "Future" };
    private float[] percentages = new float[tenseOptions.length];
    Scores scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add an action bar with navigation.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Scores");

        scores = new Scores(this.getApplicationContext());

        setContentView(R.layout.scores_activity);

        // Initialize bar chart
        HorizontalBarChart barChart = (HorizontalBarChart) findViewById(R.id.chart);

        obtainAllScores(scores);

        // Create bars
        ArrayList<BarEntry> yvalues = new ArrayList<>();
        yvalues.add(new BarEntry(0f, percentages[0]));
        yvalues.add(new BarEntry(1f, percentages[1]));
        yvalues.add(new BarEntry(2f, percentages[2]));
        yvalues.add(new BarEntry(3f, percentages[3]));
        yvalues.add(new BarEntry(4f, percentages[4]));
        yvalues.add(new BarEntry(5f, percentages[5]));
        yvalues.add(new BarEntry(6f, percentages[6]));

        // Create a data set
        BarDataSet dataSet = new BarDataSet(yvalues, "Tenses");
        dataSet.setDrawValues(true);

        // Create a data object from the dataSet
        BarData data = new BarData(dataSet);
        // Format data as percentage
        data.setValueFormatter(new PercentFormatter());

        // Make the chart use the acquired data
        barChart.setData(data);

        // Create the labels for the bars
        final ArrayList<String> xVals = new ArrayList<>();
        xVals.add("Present");
        xVals.add("Pres. Continuous");
        xVals.add("Simple Past");
        xVals.add("Past Perfect");
        xVals.add("Conditional");
        xVals.add("Cond. Perfect");
        xVals.add("Future");

        // Display labels for bars
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));

        // Set the maximum value that can be taken by the bars
        barChart.getAxisLeft().setAxisMaximum(100);

        // Bars are sliding in from left to right
        barChart.animateXY(1000, 1000);
        // Display scores inside the bars
        barChart.setDrawValueAboveBar(false);

        // Hide grid lines
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        // Hide graph description
        barChart.getDescription().setEnabled(false);
        // Hide graph legend
        barChart.getLegend().setEnabled(false);

        // Design
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);

        barChart.invalidate();
    }

    /** Define actions to be taken when clicking on the 'Reset scores' button */
    public void onClickReset(View view) {
        Context context = view.getContext().getApplicationContext();
        Scores scores = new Scores(context);
        scores.resetScores();
        Snackbar snackBar = Snackbar.make(view, "All scores have been reset!",
                Snackbar.LENGTH_SHORT);

        obtainAllScores(scores);

        snackBar.show();
    }

    /** Obtain the scores for all tenses */
    public void obtainAllScores(Scores scores) {

        for (int i = 0; i < tenseOptions.length; i++) {
            percentages[i] = scores.calculatePercentage(
                    scores.getNumberOfCorrectAnswers(tenseOptions[i]),
                    scores.getTotalNumberOfAnswers(tenseOptions[i]));
        }
    }
}