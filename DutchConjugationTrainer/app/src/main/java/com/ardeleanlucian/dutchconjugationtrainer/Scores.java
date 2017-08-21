package com.ardeleanlucian.dutchconjugationtrainer;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ardelean on 8/20/17.
 */

public class Scores {


    private Context context;
    private SharedPreferences prefs;

    private int correctAnswers;
    private int totalAnswers;

    private String correctAnswersKey;
    private String totalAnswersKey;


    // Constructor
    public Scores(Context context, int position) {

        /* Get the context here so there is no need to
         *   pass it to each method */
        this.context = context;

        // Define a variable for reading Shared Preferences
        prefs = context.getSharedPreferences(
                "com.ardeleanlucian.dutchconjugationtrainer", Context.MODE_PRIVATE);
        // Set the keys for accessing scores
        setCorrectAnswersKey( position );
        setTotalAnswersKey  ( position );
    }


    // Method to get the number of correct answers
    public int getNumberOfCorrectAnswers(int position) {

        correctAnswers = prefs.getInt( correctAnswersKey, 0 );
        return correctAnswers;
    }


    // Method to get the total number of answers
    public int getTotalNumberOfConjugations(int position) {

        totalAnswers = prefs.getInt( totalAnswersKey, 0 );
        return totalAnswers;
    }


    /* Method to set the shared preferences key
     *   for the number of correct answers */
    public void setCorrectAnswersKey(int position) {

        switch (position) {
            case 0:
                correctAnswersKey
                        = "com.ardeleanlucian.dutchconjugationtrainer.present_correct";
                break;
            case 1:
                correctAnswersKey
                        = "com.ardeleanlucian.dutchconjugationtrainer.present_continuous_correct";
                break;
            case 2:
                correctAnswersKey
                        = "com.ardeleanlucian.dutchconjugationtrainer.simple_past_correct";
                break;
            case 3:
                correctAnswersKey
                        = "com.ardeleanlucian.dutchconjugationtrainer.past_perfect_correct";
                break;
            case 4:
                correctAnswersKey
                        = "com.ardeleanlucian.dutchconjugationtrainer.conditional_correct";
                break;
            case 5:
                correctAnswersKey
                        = "com.ardeleanlucian.dutchconjugationtrainer.conditional_perfect_correct";
                break;
            case 6:
                correctAnswersKey
                        = "com.ardeleanlucian.dutchconjugationtrainer.future_correct";
                break;

        }
    }


    /* Method to set the shared preferences key
     *   for the total number of conjugations */
    public void setTotalAnswersKey(int position) {

        switch (position) {
            case 0:
                totalAnswersKey
                        = "com.ardeleanlucian.dutchconjugationtrainer.present_total";
                break;
            case 1:
                totalAnswersKey
                        = "com.ardeleanlucian.dutchconjugationtrainer.present_continuous_total";
                break;
            case 2:
                totalAnswersKey
                        = "com.ardeleanlucian.dutchconjugationtrainer.simple_past_total";
                break;
            case 3:
                totalAnswersKey
                        = "com.ardeleanlucian.dutchconjugationtrainer.past_perfect_total";
                break;
            case 4:
                totalAnswersKey
                        = "com.ardeleanlucian.dutchconjugationtrainer.conditional_total";
                break;
            case 5:
                totalAnswersKey
                        = "com.ardeleanlucian.dutchconjugationtrainer.conditional_perfect_total";
                break;
            case 6:
                totalAnswersKey
                        = "com.ardeleanlucian.dutchconjugationtrainer.future_total";
                break;

        }
    }

}
