package com.ardeleanlucian.dutchflashcards.controller;

import android.content.Context;

import com.ardeleanlucian.dutchflashcards.model.FileReader;
import com.ardeleanlucian.dutchflashcards.model.SharedPreferencesHandler;

/**
 * Created by ardelean on 10/8/17.
 */

public class MainController {

    Context context;
    SharedPreferencesHandler prefs;

    /**
     * Constructor method
     * @param context
     */
    public MainController(Context context) {
        this.context = context;
        prefs = new SharedPreferencesHandler(context);
    }

    /**
     * @param triggerAction
     * @return the next/previous pair of words depending on the trigger action
     */
    public String[] getWordsPair(String triggerAction) {

        // Read file
        FileReader fileReader = new FileReader(context);
        fileReader.readFile(triggerAction, prefs.readLatestWord(), prefs.readDifficultyLevel());

        return fileReader.getWordsPair();
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
