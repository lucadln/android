package com.ardeleanlucian.dutchconjugationtrainer.controller;

import android.content.Context;

import com.ardeleanlucian.dutchconjugationtrainer.model.Verb;

/**
 * Created by ardelean on 10/15/17.
 */

public class MainController {

    private int spinnerPosition;
    private Context context;

    /**
     * Constructor method
     * @param context
     */
    public MainController(Context context) {
        this.context = context;
    }

    public int getSpinnerPosition() {
        return spinnerPosition;
    }

    /**
     * Method to read and return the next verb from the verbs file
     *
     * @return a Verb object
     */
    public Verb obtainNextVerb() {
        return new Verb(toString(), toString(), new String[0][0]); // return something @TODO
    }
}
