package com.ardeleanlucian.dutchconjugationtrainer.view;

import android.graphics.Color;
import android.view.View;

import com.ardeleanlucian.dutchconjugationtrainer.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import static com.ardeleanlucian.dutchconjugationtrainer.model.Verb.tenses;

/**
 * Created by ardelean on 11/19/17.
 */

public class ScoreChart {

    /**
     * Define the bar chart
     */
    private HorizontalBarChart barChart;
    /**
     * Define a list of colors to be used in the bar chart
     */
    List<Integer> colors;

    /**
     * Constructor method
     * @param view
     */
    public ScoreChart(View view) {

        // Initialize the bar chart
        barChart = view.findViewById(R.id.chart);

        // Initialize the <colors> variable
        colors = new ArrayList<>();
    }

    /**
     * Method to set data and design for the bar chart
     */
    public void disposeHorizontalBarChart(
            float[] scores, int[] correctConjugationsCount, int[] totalConjugationsCount) {
        /* Set a custom renderer. This helps to show chart labels either
         *   inside or outside the chart bars depending on their values */
        barChart.setRenderer(new CustomHorizontalBarChartRenderer(
                barChart,
                barChart.getAnimator(),
                barChart.getViewPortHandler(),
                scores));
        barChart.invalidate();

        // Add entry values for every tense
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < tenses.length; i++) {
            barEntries.add(new BarEntry((float) i, scores[i]));
        }

        // Create a data set from the entry values
        BarDataSet dataSet = new BarDataSet(barEntries, "Tenses");
        // Set data set values to be visible on the graph
        dataSet.setDrawValues(true);

        // Create a data object from the data set
        BarData data = new BarData(dataSet);
        // Make the chart use the acquired data
        barChart.setData(data);

        // Display data as <correctAnswers>/<totalAnswers>
        data.setValueFormatter(new CustomValueFormatter(
                correctConjugationsCount, totalConjugationsCount));

        // Create explanation labels for each bar
        final ArrayList<String> barLabels = new ArrayList<>();
        barLabels.add("Present");
        barLabels.add("Pres. Perfect");
        barLabels.add("Simple Past");
        barLabels.add("Past Perfect");
        barLabels.add("Conditional");
        barLabels.add("Cond. Perfect");
        barLabels.add("Future");
        // Display explanation labels
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barLabels));

        // Set the minimum and maximum bar values
        barChart.getAxisLeft().setAxisMaximum(100);
        barChart.getAxisLeft().setAxisMinimum(0);

        // Set a color for each bar in the chart based on its value
        setBarChartColors(scores);

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
        dataSet.setColors(colors);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
    }

    /**
     * Color the bars in the chart depending on their value
     *
     * @param percentages
     */
    public void setBarChartColors(float[] percentages) {

        int orange100 = Color.rgb(255, 123, 0);
        int orange90  = Color.rgb(255, 140, 33);
        int orange80  = Color.rgb(255, 157, 66);
        int orange70  = Color.rgb(255, 168, 86);
        int orange60  = Color.rgb(255, 179, 109);
        int orange50  = Color.rgb(255, 191, 132);
        int orange40  = Color.rgb(255, 207, 163);
        int orange30  = Color.rgb(255, 220, 188);
        int orange20  = Color.rgb(255, 229, 206);
        int orange10  = Color.rgb(255, 243, 232);

        for (int i = 0; i < percentages.length; i++) {
            if        (percentages[i] <= 10.0) {
                colors.add(orange10);
            } else if (percentages[i] <= 20.0) {
                colors.add(orange20);
            } else if (percentages[i] <= 30.0) {
                colors.add(orange30);
            } else if (percentages[i] <= 40.0) {
                colors.add(orange40);
            } else if (percentages[i] <= 50.0) {
                colors.add(orange50);
            } else if (percentages[i] <= 60.0) {
                colors.add(orange60);
            } else if (percentages[i] <= 70.0) {
                colors.add(orange70);
            } else if (percentages[i] <= 80.0) {
                colors.add(orange80);
            } else if (percentages[i] <= 90.0) {
                colors.add(orange90);
            } else { // if (percentages[i] > 90.0)
                colors.add(orange100);
            }
        }
    }
}
