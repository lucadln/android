package com.ardeleanlucian.dutchconjugationtrainer;

import android.graphics.Color;
import android.view.View;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by ardelean on 9/2/17.
 */

public class ScoresChart {

    private Scores scores;
    private String[] tenseOptions = {"Present", "Present Continuous", "Simple Past", "Past Perfect",
            "Condtional", "Conditional Perfect", "Future" };
    private HorizontalBarChart barChart;
    private float[] percentages = new float[tenseOptions.length];
    private int[] correctAnswers = new int[tenseOptions.length];
    private int[] totalAnswers = new int[tenseOptions.length];

    /**
     * Constructor method
     * @param view
     */
    public ScoresChart(View view) {

        // Initialize the bar chart
        barChart = (HorizontalBarChart) view.findViewById(R.id.chart);
        scores = new Scores(view.getContext());
    }

    /**
     * Method to set the HorizontalBarChart
     */
    public void setHorizontalBarChart() {

        obtainAllScores(scores);

        /* Set a custom renderer. This helps to show chart labels either
        *      inside or outside the chart bars depending on their values */
        barChart.setRenderer(new CustomHorizontalBarChartRenderer(
                barChart,
                barChart.getAnimator(),
                barChart.getViewPortHandler(),
                percentages));
        barChart.invalidate();

        // Add entry values for every tense
        ArrayList<BarEntry> yvalues = new ArrayList<>();
        yvalues.add(new BarEntry(0f, percentages[0]));
        yvalues.add(new BarEntry(1f, percentages[1]));
        yvalues.add(new BarEntry(2f, percentages[2]));
        yvalues.add(new BarEntry(3f, percentages[3]));
        yvalues.add(new BarEntry(4f, percentages[4]));
        yvalues.add(new BarEntry(5f, percentages[5]));
        yvalues.add(new BarEntry(6f, percentages[6]));

        // Create a data set from the entry values
        BarDataSet dataSet = new BarDataSet(yvalues, "Tenses");
        // Set data set values to be visible on the graph
        dataSet.setDrawValues(true);

        // Create a data object from the data set
        BarData data = new BarData(dataSet);
        // Make the chart use the acquired data
        barChart.setData(data);
        // Display data as <correctAnswers>/<totalAnswers>
        data.setValueFormatter(new CustomValueFormatter(correctAnswers, totalAnswers));

        // Create explanation labels for each bar
        final ArrayList<String> xVals = new ArrayList<>();
        xVals.add("Present");
        xVals.add("Pres. Continuous");
        xVals.add("Simple Past");
        xVals.add("Past Perfect");
        xVals.add("Conditional");
        xVals.add("Cond. Perfect");
        xVals.add("Future");
        // Display explanation label
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));

        // Set the minimum and maximum bar values
        barChart.getAxisLeft().setAxisMaximum(100);
        barChart.getAxisLeft().setAxisMinimum(0);

        // Display scores inside the bars
        barChart.setDrawValueAboveBar(false);

        // Animate chart so that bars are sliding from left to right
        barChart.animateXY(1000, 1000);

        // Hide grid lines
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        // Hide graph description
        barChart.getDescription().setEnabled(false);
        // Hide graph legend
        barChart.getLegend().setEnabled(false);

        // Set colors and font style
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
    }

    /** Obtain the scores for all tenses */
    public void obtainAllScores(Scores scores) {

        for (int i = 0; i < tenseOptions.length; i++) {
            correctAnswers[i] = scores.getNumberOfCorrectAnswers(tenseOptions[i]);
            totalAnswers[i] = scores.getTotalNumberOfAnswers(tenseOptions[i]);
            percentages[i] = scores.calculatePercentage(correctAnswers[i], totalAnswers[i]);
        }
    }
}
