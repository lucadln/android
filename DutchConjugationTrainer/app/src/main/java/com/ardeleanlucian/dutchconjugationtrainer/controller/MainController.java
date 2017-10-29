package com.ardeleanlucian.dutchconjugationtrainer.controller;

import android.content.Context;

import com.ardeleanlucian.dutchconjugationtrainer.model.FileReader;
import com.ardeleanlucian.dutchconjugationtrainer.model.SharedPreferencesHandler;
import com.ardeleanlucian.dutchconjugationtrainer.model.Verb;

/**
 * Created by ardelean on 10/15/17.
 */

public class MainController {

    private Context context;

    SharedPreferencesHandler sharedPreferencesHandler;

    /**
     * Constructor method
     * @param context
     */
    public MainController(Context context) {
        this.context = context;
        sharedPreferencesHandler = new SharedPreferencesHandler(context);
    }

    /**
     * Method to read and return the next verb from the verbs file
     *
     * @return a Verb object
     */
    public Verb obtainNextVerb() {
        FileReader fileReader = new FileReader();
        return fileReader.readNextVerb(context, sharedPreferencesHandler);
    }

    /**
     * Method to read and return the spinner index
     *
     * @return the spinner index
     */
    public int obtainSpinnerIndex() {
        return sharedPreferencesHandler.getSpinnerIndex();
    }

    /**
     * Method to update the value of the spinner index in the android's memory
     *
     * @param newValue
     */
    public void updateSpinnerPosition(int newValue) {
        sharedPreferencesHandler.setSpinnerIndex(newValue);
    }
}
