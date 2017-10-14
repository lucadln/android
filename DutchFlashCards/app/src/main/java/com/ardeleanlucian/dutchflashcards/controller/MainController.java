package com.ardeleanlucian.dutchflashcards.controller;

import android.content.Context;

import com.ardeleanlucian.dutchflashcards.model.FileReader;
import com.ardeleanlucian.dutchflashcards.model.SharedPreferencesHandler;
import com.ardeleanlucian.dutchflashcards.model.WordPair;

/**
 * Created by ardelean on 10/8/17.
 */

public class MainController {

    private Context context;
    private SharedPreferencesHandler prefs;
    private WordPair wordPair;

    /**
     * Constructor method
     * @param context
     */
    public MainController(Context context) {
        this.context = context;
        prefs = new SharedPreferencesHandler(context);
    }

    /**
     * Obtain a WordPair object that holds both the dutch and english word
     *
     * @param triggerAction specifies whether to read the next
     *                      or previous word pair from the file
     */
    public void readWordPair(String triggerAction) {

        // Read file
        FileReader fileReader = new FileReader(context);
        wordPair = fileReader
                .readFile(triggerAction, prefs.readLatestWord(), prefs.readDifficultyLevel());
    }

    /**
     * Getter for the dutch word
     */
    public String getDutchWord() {
        return wordPair.getDutchWord();
    }

    /**
     * Getter for the english word
     */
    public String getEnglishWord() {
        return wordPair.getEnglishWord();
    }

    /**
     * @return the primary language as saved in the android's Shared Preferences
     */
    public String getPrimaryLanguage() {
        return prefs.readPrimaryLanguage();
    }

    /**
     * @return the difficulty level as saved in the android's Shared Preferences
     */
    public String getDifficulty() { return prefs.readDifficultyLevel(); }

    /**
     * Method to update the difficulty level in android's Shared Preferences
     */
    public void updateDifficulty(String newDifficulty) {
        prefs.writeDifficultyLevel(newDifficulty);
    }

    /**
     * Method to update the primary language in android's Shared Preferences
     */
    public void updatePrimaryLanguage() {
        // Swap the value of the primary language
        if (prefs.readPrimaryLanguage().equals("dutch")) {
            prefs.writePrimaryLanguage("english");
        } else {
            prefs.writePrimaryLanguage("dutch");
        }
    }
}
