package com.ardeleanlucian.dutchconjugationtrainer.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ardelean on 10/15/17.
 */

public class SharedPreferencesHandler {

    SharedPreferences preferences;

    static final String CURRENT_VERB_KEY = "com.ardeleanlucian.dutchconjugationtrainer.current_verb";
    static final String SPINNER_INDEX_KEY = "com.ardeleanlucian.dutchconjugationtrainer.spinner_index";

    /**
     * Constructor method
     */
    public SharedPreferencesHandler(Context context) {
        preferences = context.getSharedPreferences(
                "com.ardeleanlucian.dutchconjugationtrainer", Context.MODE_PRIVATE);
    }

    /**
     * @return the spinner index from the phone memory
     */
    public int getSpinnerIndex() {
        return preferences.getInt(SPINNER_INDEX_KEY, 0);
    }

    /**
     * Set a new value for the spinner index in the phone memory
     */
    public void setSpinnerIndex(int newValue) {
        preferences.edit().putInt(SPINNER_INDEX_KEY, newValue).apply();
    }

    /**
     * @return the current verb from the phone memory
     */
    public String getCurrentVerb() {
        return preferences.getString(CURRENT_VERB_KEY, "denken");
    }

    /**
     * Set a new value for the current verb in the phone memory
     */
    public void setCurrentVerb(String newValue) {
        preferences.edit().putString(CURRENT_VERB_KEY, newValue).apply();
    }
}
