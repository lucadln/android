package com.ardeleanlucian.dutchconjugationtrainer;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ardelean on 9/3/17.
 */

public class SharedPreferencesHandler {

    /**
     * Variable to help reading and writing shared preferences
     */
    SharedPreferences preferences;

    /**
     * Variables to hold the keys for accessing correct/total answers
     *   from the android Shared Preferences
     */
    String keysForCorrectAnswers[];
    String keysForTotalAnswers[];

    /**
     * Variable to hold the number of tenses that the application handles
     */
    int numberOfTenses;


    /**
     * Basic constructor method
     *
     * @param context
     */
    public SharedPreferencesHandler(Context context) {

        // Initialize 'preferences' variable
        preferences = context.getSharedPreferences(
                "com.ardeleanlucian.dutchconjugationtrainer", Context.MODE_PRIVATE);
    }

    /**
     * Constructor method with 2 parameters
     *
     * @param context
     * @param numberOfTenses
     */
    public SharedPreferencesHandler(Context context, int numberOfTenses) {

        // Initialize 'preferences' variable
        preferences = context.getSharedPreferences(
                "com.ardeleanlucian.dutchconjugationtrainer", Context.MODE_PRIVATE);

        // Pass the numberOfTenses value to the object variable
        this.numberOfTenses = numberOfTenses;

        // Set android keys
        setAndroidKeysForCorrectAnswers();
        setAndroidKeysForTotalAnswers();
    }

    /**
     * Method to set the shared preferences key
     *   for correct answers
     */
    public void setAndroidKeysForCorrectAnswers() {
        for (int i = 0; i < numberOfTenses; i++) {
            keysForCorrectAnswers[i] = "com.ardeleanlucian.dutchconjugationtrainer.correct_"
                    + String.valueOf(i);
        }
    }

    /**
     * Method to set the shared preferences key
     *   for total answers
     */
    public void setAndroidKeysForTotalAnswers() {
        for (int i = 0; i < numberOfTenses; i++) {
            keysForTotalAnswers[i] = "com.ardeleanlucian.dutchconjugationtrainer.total_"
                    + String.valueOf(i);
        }
    }

    /**
     * Method to get the number of correct answers for each available tense
     *
     * @return
     */
    public int[] getNumberOfCorrectAnswers() {
        int[] numberOfCorrectAnswers = new int[numberOfTenses];
        for (int i = 0; i < numberOfTenses; i++) {
            numberOfCorrectAnswers[i] = preferences.getInt(keysForCorrectAnswers[i], 0);
        }
        return numberOfCorrectAnswers;
    }

    /**
     * Method to get the total number of answers for each available tense
     *
     * @return
     */
    public int[] getTotalNumberOfAnswers() {
        int[] totalNumberOfAnswers = new int[numberOfTenses];
        for (int i = 0; i < numberOfTenses; i++) {
            totalNumberOfAnswers[i] = preferences.getInt(keysForCorrectAnswers[i], 0);
        }
        return totalNumberOfAnswers;
    }

    /**
     * Method to update the number of correct answers in android Shared Preferences
     *
     * @param tenseInUse
     * @param newValue
     */
    public void updateNumberOfCorrectAnswers(int tenseInUse, int newValue) {
        preferences.edit().putInt(keysForCorrectAnswers[tenseInUse], newValue).apply();
    }

    /**
     * Method to update the total number of answers in android Shared Preferences
     *
     * @param tenseInUse
     * @param newValue
     */
    public void updateTotalNumberOfAnswers(int tenseInUse, int newValue) {
        preferences.edit().putInt(keysForTotalAnswers[tenseInUse], newValue).apply();
    }
}
