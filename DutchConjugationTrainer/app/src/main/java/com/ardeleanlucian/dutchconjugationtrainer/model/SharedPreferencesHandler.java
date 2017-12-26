package com.ardeleanlucian.dutchconjugationtrainer.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ardeleanlucian.dutchconjugationtrainer.R;

/**
 * Created by ardelean on 10/15/17.
 */

public class SharedPreferencesHandler {

    SharedPreferences sharedPreferences; // preferences and other variables saved in the android's SharedPreferences
    SharedPreferences applicationSettings; // settings from the SettingsActivity

    static final String CURRENT_VERB_KEY = "com.ardeleanlucian.dutchconjugationtrainer.current_verb";
    static final String SPINNER_INDEX_KEY = "com.ardeleanlucian.dutchconjugationtrainer.spinner_index";
    static final String SHOW_TRANSLATION_KEY = "com.ardeleanlucian.dutchconjugationtrainer.show_translation";
    static final String READ_ONLY_KEY = "com.ardeleanlucian.dutchconjugationtrainer.read_only";
    static final String GIVE_FEEDBACK_KEY = "com.ardeleanlucian.dutchconjugationtrainer.give_feedback";
    static final String CORRECTLY_CONJUGATED_VERBS_COUNT_KEY = "com.ardeleanlucian.dutchconjugationtrainer.correct_conjugations_";
    static final String TOTAL_CONJUGATED_VERBS_COUNT_KEY = "com.ardeleanlucian.dutchconjugationtrainer.total_conjugations_";
    static final String CONJUGATIONS_COUNT_SINCE_LAST_MILESTONE_KEY = "com.ardeleanlucian.dutchconjugationtrainer.conjugations_since_last_milestone_";

    /**
     * Constructor method
     */
    public SharedPreferencesHandler(Context context) {
        PreferenceManager.setDefaultValues(context, R.xml.preferences, false);
        applicationSettings = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences = context.getSharedPreferences(
                "com.ardeleanlucian.dutchconjugationtrainer", Context.MODE_PRIVATE);
    }

    /**
     * @return a boolean whose value shows weather the user receives
     *         feedback or not during using the application
     */
    public boolean isFeedbackGiven() {
        return applicationSettings.getBoolean(GIVE_FEEDBACK_KEY, true);
    }

    /**
     * @return the application run mode
     */
    public boolean isReadOnly() {
        return applicationSettings.getBoolean(READ_ONLY_KEY, false);
    }

    /**
     * @return a boolean whose value shows whether the translation
     *         for the infinitive of the verb is displayed or not
     */
    public boolean isTranslationDisplayed() {
        return applicationSettings.getBoolean(SHOW_TRANSLATION_KEY, true);
    }

    /**
     * @return the spinner index from the phone memory
     */
    public int getSpinnerIndex() {
        return sharedPreferences.getInt(SPINNER_INDEX_KEY, 0);
    }

    /**
     * @return the current verb from the phone memory
     */
    public String getCurrentVerb() {
        return sharedPreferences.getString(CURRENT_VERB_KEY, "denken");
    }

    /**
     * Set whether to give the user feedback or not
     *
     * @param newValue
     */
    public void setIfFeedbackGiven(boolean newValue) {
        applicationSettings.edit().putBoolean(GIVE_FEEDBACK_KEY, newValue).apply();
    }

    /**
     * Set the readOnly variable in the phone memory
     *
     * @param newValue
     */
    public void setIfReadOnly(boolean newValue) {
        applicationSettings.edit().putBoolean(READ_ONLY_KEY, newValue).apply();
    }

    /**
     * Decide whether to display the translation or not
     *
     * @param newValue
     */
    public void setIfTranslationDisplayed(boolean newValue) {
        applicationSettings.edit().putBoolean(SHOW_TRANSLATION_KEY, newValue).apply();
    }

    /**
     * Set a new value for the spinner index in the phone memory
     *
     * @param newValue
     */
    public void setSpinnerIndex(int newValue) {
        sharedPreferences.edit().putInt(SPINNER_INDEX_KEY, newValue).apply();
    }

    /**
     * Set a new value for the current verb in the phone memory
     *
     * @param newValue
     */
    public void setCurrentVerb(String newValue) {
        sharedPreferences.edit().putString(CURRENT_VERB_KEY, newValue).apply();
    }

    /**
     * @return correct conjugations for the tense in use
     */
    public int getCorrectConjugationsCount() {
        return sharedPreferences.getInt(
                CORRECTLY_CONJUGATED_VERBS_COUNT_KEY + String.valueOf(getSpinnerIndex()), 0);
    }

    /**
     * @param spinnerIndex
     * @return correct conjugations for a certain tense given by the spinnerIndex parameter
     */
    public int getCorrectConjugationsCount(int spinnerIndex) {
        return sharedPreferences.getInt(
                CORRECTLY_CONJUGATED_VERBS_COUNT_KEY + String.valueOf(spinnerIndex), 0);
    }

    /**
     * @return the total number of correctly conjugated verbs for the tense in use
     */
    public int getTotalConjugationsCount() {
        return sharedPreferences.getInt(
                TOTAL_CONJUGATED_VERBS_COUNT_KEY + String.valueOf(getSpinnerIndex()), 0);
    }

    /**
     * @return the total number of correctly conjugated verbs for a certain tense
     *           given by the spinnerIndex parameter
     */
    public int getTotalConjugationsCount(int spinnerIndex) {
        return sharedPreferences.getInt(
                TOTAL_CONJUGATED_VERBS_COUNT_KEY + String.valueOf(spinnerIndex), 0);
    }

    /**
     * Method to update the number of correctly conjugated verbs in the android's Shared Preferences
     * @param newValue
     */
    public void updateCorrectConjugationsCount(int newValue) {
        sharedPreferences.edit().putInt(CORRECTLY_CONJUGATED_VERBS_COUNT_KEY
                        + String.valueOf(getSpinnerIndex()), newValue).apply();
    }

    /**
     * Method to reset the number of correctly conjugated verbs in the android's Shared Prefrences
     * @param newValue
     * @param spinnerIndex
     */
    public void resetCorrectConjugationsCount(int newValue, int spinnerIndex) {
        sharedPreferences.edit().putInt(CORRECTLY_CONJUGATED_VERBS_COUNT_KEY
                + String.valueOf(spinnerIndex), newValue).apply();
    }

    /**
     * Method to update the total number of conjugated verbs in android's Shared Preferences
     * @param newValue
     */
    public void updateTotalConjugationsCount(int newValue) {
        sharedPreferences.edit().putInt(TOTAL_CONJUGATED_VERBS_COUNT_KEY
                + String.valueOf(getSpinnerIndex()), newValue).apply();
    }

    /**
     * Method to reset the number of correctly conjugated verbs in the android's Shared Prefrences
     * @param newValue
     * @param spinnerIndex
     */
    public void resetTotalConjugationsCount(int newValue, int spinnerIndex) {
        sharedPreferences.edit().putInt(TOTAL_CONJUGATED_VERBS_COUNT_KEY
                + String.valueOf(spinnerIndex), newValue).apply();
    }

    /**
     * @param spinnerIndex
     * @return the number of conjugated verbs since the last milestone
     *           for a certain tense given by the spinnerIndex parameter
     */
    public int getConjugationsCountSinceLastMilestone(int spinnerIndex) {
        return sharedPreferences.getInt(
                CONJUGATIONS_COUNT_SINCE_LAST_MILESTONE_KEY + String.valueOf(spinnerIndex), 0);
    }

    /**
     * Method to update the number of conjugations that were done for
     *   a certain tense given by the spinnerIndex parameter
     * @param newValue
     * @param spinnerIndex
     */
    public void updateConjugationsCountSinceLastMilestone(int newValue, int spinnerIndex) {
        sharedPreferences.edit().putInt(
                CONJUGATIONS_COUNT_SINCE_LAST_MILESTONE_KEY + String.valueOf(spinnerIndex), newValue).apply();
    }
}
