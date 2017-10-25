package com.ardeleanlucian.dutchconjugationtrainer.controller;

import android.content.Context;

import com.ardeleanlucian.dutchconjugationtrainer.model.Verb;

/**
 * Created by ardelean on 10/15/17.
 */

public class MainController {

    private int spinnerPosition;
    private Context context;

    private Verb nextVerb;

    /**
     * Constructor method
     * @param context
     */
    public MainController(Context context) {
        //@TODO
        this.context = context;
    }

    public int getSpinnerPosition() {
        return spinnerPosition;
    }

    public Verb getNextVerb() {
        return nextVerb;
    }
}
