package com.ardeleanlucian.dutchflashcards.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ardelean on 10/8/17.
 */

public class SharedPreferencesHandler {

    private SharedPreferences prefs;

    private final String PRIMARY_LANGUAGE_KEY = "com.ardeleanlucian.dutchflashcards.primary_language";
    private final String DIFFICULTY_LEVEL_KEY = "com.ardeleanlucian.dutchflashcards.difficulty_level";
    private final String LATEST_EASY_WORD_KEY = "com.ardeleanlucian.dutchflashcards.latest_easy_word";
    private final String LATEST_MEDIUM_WORD_KEY = "com.ardeleanlucian.dutchflashcards.latest_medium_word";
    private final String LATEST_HARD_WORD_KEY = "com.ardeleanlucian.dutchflashcards.latest_hard_word";

    private String primaryLanguage;
    private String difficultyLevel;
    private String latestEasyWord;
    private String latestMediumWord;
    private String latestHardWord;

    /**
     * Constructor method
     * @param context
     */
    public SharedPreferencesHandler(Context context) {

        prefs = context.getSharedPreferences(
                "com.ardeleanlucian.dutchflashcards", Context.MODE_PRIVATE);
    }


    /**
     * Methods to read values from the SharedPreferences.
     */
    public void readPrimaryLanguage() {
        primaryLanguage = prefs.getString(PRIMARY_LANGUAGE_KEY, "dutch");
    }

    public void readDifficultyLevel() {
        difficultyLevel = prefs.getString(DIFFICULTY_LEVEL_KEY, "easy");
    }

    public void readLatestEasyWord() {
        latestEasyWord = prefs.getString(LATEST_EASY_WORD_KEY, "test"); //@TODO make this not be hardcoded
    }

    public void readLatestMediumWord() {
        latestMediumWord = prefs.getString(LATEST_MEDIUM_WORD_KEY, "test"); //@TODO make this not be hardcoded
    }

    public void readLatestHardWord() {
        latestHardWord = prefs.getString(LATEST_HARD_WORD_KEY, "test"); //@TODO make this not be hardcoded
    }

    /**
     * Getters for the SharedPreferences values
     * @return
     */
    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getLatestEasyWord() {
        return latestEasyWord;
    }

    public String getLatestMediumWord() {
        return latestMediumWord;
    }

    public String getLatestHardWord() {
        return latestHardWord;
    }

    /**
     * Methods to update the current value in the android's Shared Preferences
     * @param newValue
     */
    public void updatePrimaryLanguage(String newValue) {
        prefs.edit().putString(PRIMARY_LANGUAGE_KEY, newValue).apply();
    }

    public void updateDifficultyLevel(String newValue) {
        prefs.edit().putString(DIFFICULTY_LEVEL_KEY, newValue).apply();
    }

    public void updateLatestEasyWord(String newValue) {
        prefs.edit().putString(LATEST_EASY_WORD_KEY, newValue).apply();
    }

    public void updateLatestMediumWord(String newValue) {
        prefs.edit().putString(LATEST_MEDIUM_WORD_KEY, newValue).apply();
    }
    
    public void updateLatestHardWord(String newValue) {
        prefs.edit().putString(LATEST_HARD_WORD_KEY, newValue).apply();
    }
}
