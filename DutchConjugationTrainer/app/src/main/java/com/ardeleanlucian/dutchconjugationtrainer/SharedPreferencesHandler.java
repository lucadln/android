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
     * Variable to hold the keys for accessing the number of
     *   conjugated verbs
     */
    String keysForNumberOfConjugations[];

    /**
     * Variable to hold the keys for accessing the number of
     *   conjugated verbs since the last feedback
     */
    String keysForConjugationsSinceLastMilestone[];

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
        // Initialize arrays for shared preferences keys
        keysForCorrectAnswers = new String[numberOfTenses];
        keysForTotalAnswers = new String[numberOfTenses];
        keysForNumberOfConjugations = new String[numberOfTenses];
        keysForConjugationsSinceLastMilestone = new String[numberOfTenses];

        // Set android keys
        setAndroidKeysForCorrectAnswers();
        setAndroidKeysForTotalAnswers();
        setAndroidKeysForNumberOfConjugations();
    }

    /**
     * Method to set the shared preferences key
     *   for the number of conjugated verbs
     */
    public void setAndroidKeysForNumberOfConjugations() {
        for (int i = 0; i < numberOfTenses; i++) {
            keysForNumberOfConjugations[i] = "com.ardeleanlucian.dutchconjugationtrainer_"
                    + String.valueOf(i);
        }
    }

    /**
     * Method to set the shared preferences key for
     *   the number of verbs that were conjugated since
     *   the last milestone feedback was received
     */
    public void setKeysForConjugationsSinceLastMilestone() {
        for (int i = 0; i < numberOfTenses; i++) {
            keysForConjugationsSinceLastMilestone[i] = "com.ardeleanlucian.dutchconjugationtrainer."
                    + "conjugations_since_milestone_" + String.valueOf(i);
        }
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
     * Method to get the number of conjugated verbs for a certain tense
     *
     * @param tenseIndex
     * @return
     */
    public int getNumberOfConjugations(int tenseIndex) {
        int numberOfConjugations = preferences.getInt(keysForNumberOfConjugations[tenseIndex], 0);
        return numberOfConjugations;
    }

    public int getConjugationsSinceLastMilestone(int tenseIndex) {
        int conjugationsSinceLastMilestone = preferences.getInt(keysForConjugationsSinceLastMilestone[tenseIndex], 0);
        return conjugationsSinceLastMilestone;
    }

    /**
     * Method to get the number of correct answers for a certain tense
     *
     * @param tenseIndex
     * @return
     */
    public int getNumberOfCorrectAnswers(int tenseIndex) {
        int numberOfCorrectAnswers = preferences.getInt(keysForCorrectAnswers[tenseIndex], 0);
        return numberOfCorrectAnswers;
    }

    /**
     * Method to get the total number of answers for a certain tense
     *
     * @param tenseIndex
     * @return
     */
    public int getTotalNumberOfAnswers(int tenseIndex) {
        int totalNumberOfAnswers = preferences.getInt(keysForTotalAnswers[tenseIndex], 0);
        return totalNumberOfAnswers;
    }

    /**
     * Method to update the number of conjugated verbs in android Shared Preferences
     *
     * @param tenseIndex
     * @param newValue
     */
    public void updateNumberOfConjugations(int tenseIndex, int newValue) {
        preferences.edit().putInt(keysForNumberOfConjugations[tenseIndex], newValue).apply();
    }

    public void updateConjugationsSinceLastMilestone(int tenseIndex, int newValue) {
        preferences.edit().putInt(keysForConjugationsSinceLastMilestone[tenseIndex], newValue).apply();
    }

    /**
     * Method to update the number of correct answers in android Shared Preferences
     *
     * @param tenseIndex
     * @param newValue
     */
    public void updateNumberOfCorrectAnswers(int tenseIndex, int newValue) {
        preferences.edit().putInt(keysForCorrectAnswers[tenseIndex], newValue).apply();
    }

    /**
     * Method to update the total number of answers in android Shared Preferences
     *
     * @param tenseIndex
     * @param newValue
     */
    public void updateTotalNumberOfAnswers(int tenseIndex, int newValue) {
        preferences.edit().putInt(keysForTotalAnswers[tenseIndex], newValue).apply();
    }
}
