package com.ardeleanlucian.dutchconjugationtrainer.view;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import static com.ardeleanlucian.dutchconjugationtrainer.model.Verb.tenses;

/**
 * Created by ardelean on 9/2/17.
 */

public class CustomValueFormatter implements IValueFormatter {

    public int index;
    private int[] correctAnswers;
    private int[] totalAnswers;

    // Constructor
    public CustomValueFormatter(int[] correctAnswers, int[] totalAnswers) {

        int numberOfTenses = tenses.length;

        this.correctAnswers = new int[numberOfTenses];
        this.totalAnswers = new int[numberOfTenses];

        for (int i = 0; i < numberOfTenses; i++) {
            this.correctAnswers[i] = correctAnswers[i];
            this.totalAnswers[i] = totalAnswers[i];
        }
    }

    // Define a new way to format values
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

        if (entry.toString().contains("x: 0.0")) {
            index = 0;
        } else if (entry.toString().contains("x: 1.0")) {
            index = 1;
        } else if (entry.toString().contains("x: 2.0")) {
            index = 2;
        } else if (entry.toString().contains("x: 3.0")) {
            index = 3;
        } else if (entry.toString().contains("x: 4.0")) {
            index = 4;
        } else if (entry.toString().contains("x: 5.0")) {
            index = 5;
        } else if (entry.toString().contains("x: 6.0")) {
            index = 6;
        } else {
            return null;
        }

        if (totalAnswers[index] == 0) {
            // No conjugations were yet written in the current tense
            return "N/A";
        } else {
            // Display value labels in format <correctAnswers> / <totalAnswers>
            return String.valueOf(correctAnswers[index]) + "/" + String.valueOf(totalAnswers[index]);
        }
    }
}