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
    private final String LATEST_MODERATE_WORD_KEY = "com.ardeleanlucian.dutchflashcards.latest_moderate_word";
    private final String LATEST_HARD_WORD_KEY = "com.ardeleanlucian.dutchflashcards.latest_hard_word";

    /**
     * Constructor method
     *
     * @param context
     */
    public SharedPreferencesHandler(Context context) {
        prefs = context.getSharedPreferences(
                "com.ardeleanlucian.dutchflashcards", Context.MODE_PRIVATE);
    }

    /**
     * Methods to read values from the SharedPreferences.
     */
    public String readPrimaryLanguage() {
        return prefs.getString(PRIMARY_LANGUAGE_KEY, "dutch");
    }

    public String readDifficultyLevel() {
        return prefs.getString(DIFFICULTY_LEVEL_KEY, "easy");
    }

    public String readLatestEasyWord() {
        return prefs.getString(LATEST_EASY_WORD_KEY, "default");
    }

    public String readLatestModerateWord() {
        return prefs.getString(LATEST_MODERATE_WORD_KEY, "default");
    }

    public String readLatestHardWord() {
        return prefs.getString(LATEST_HARD_WORD_KEY, "default");
    }

    public String readLatestWord() {
        String difficulty = readDifficultyLevel();

        switch (difficulty) {
            case "easy":
                return readLatestEasyWord();
            case "moderate":
                return readLatestModerateWord();
            case "hard":
                return readLatestHardWord();
            default:
                return "Unknown difficulty '" + difficulty + "'.";
        }
    }

    /**
     * Methods to update values in the android's Shared Preferences
     * @param newValue
     */
    public void writtePrimaryLanguage(String newValue) {
        prefs.edit().putString(PRIMARY_LANGUAGE_KEY, newValue).apply();
    }

    public void writteDifficultyLevel(String newValue) {
        prefs.edit().putString(DIFFICULTY_LEVEL_KEY, newValue).apply();
    }

    public void writteLatestEasyWord(String newValue) {
        prefs.edit().putString(LATEST_EASY_WORD_KEY, newValue).apply();
    }

    public void writteLatestModerateWord(String newValue) {
        prefs.edit().putString(LATEST_MODERATE_WORD_KEY, newValue).apply();
    }
    
    public void writteLatestHardWord(String newValue) {
        prefs.edit().putString(LATEST_HARD_WORD_KEY, newValue).apply();
    }

    public void writteLatestWord(String newValue) {
        String difficulty = readDifficultyLevel();

        switch (difficulty) {
            case "easy":
                writteLatestEasyWord(newValue);
            case "moderate":
                writteLatestModerateWord(newValue);
            case "hard":
                writteLatestHardWord(newValue);
        }
    }
}
