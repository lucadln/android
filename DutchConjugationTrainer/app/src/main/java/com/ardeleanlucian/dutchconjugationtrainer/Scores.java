package com.ardeleanlucian.dutchconjugationtrainer;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ardelean on 8/20/17.
 */

public class Scores {


    private Context context;
    private SharedPreferences prefs;
    private String androidKeyForCorrectAnswers;
    private String androidKeyForTotalAnswers;

    private int correctAnswers;
    private int totalAnswers;


    // Constructor
    public Scores(Context context) {

        this.context = context;

        // Asign value to prefs for reading Shared Preferences
        prefs = context.getSharedPreferences(
                "com.ardeleanlucian.dutchconjugationtrainer", Context.MODE_PRIVATE);
    }


    public void validateConjugation() {
        // TODO
    }


    public void updateNumberOfCorrectAnswers(String selectedTense) {
        // TODO
    }


    public void updateTotalNumberOfAnswers(String selectedTense) {
        // TODO
    }


    // Method to get the number of correct answers
    public int getNumberOfCorrectAnswers(String selectedTense) {
        setAndroidKeyForCorrectAnswers(selectedTense);
        correctAnswers = prefs.getInt(androidKeyForCorrectAnswers, 0 );
        return correctAnswers;
    }


    // Method to get the total number of answers
    public int getTotalNumberOfAnswers(String selectedTense) {
        setAndroidKeyForTotalAnswers(selectedTense);
        totalAnswers = prefs.getInt(androidKeyForTotalAnswers, 0 );
        return totalAnswers;
    }


    /* Method to set the shared preferences key
     *   for the number of correct answers */
    public void setAndroidKeyForCorrectAnswers(String selectedTense) {
        androidKeyForCorrectAnswers = "com.ardeleanlucian.dutchconjugationtrainer"
                + selectedTense.toLowerCase().replaceAll(" ", "_");
    }


    /* Method to set the shared preferences key
     *   for the total number of conjugations */
    public void setAndroidKeyForTotalAnswers(String selectedTense) {
        androidKeyForTotalAnswers = "com.ardeleanlucian.dutchconjugationtrainer"
                + selectedTense.toLowerCase().replaceAll(" ", "_");
    }

}
