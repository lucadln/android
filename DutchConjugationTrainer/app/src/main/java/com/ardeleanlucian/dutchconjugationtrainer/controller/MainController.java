package com.ardeleanlucian.dutchconjugationtrainer.controller;

import android.content.Context;

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
        //@TODO
        this.context = context;
    }

    public int getSpinnerPosition() {
        return spinnerPosition;
    }
}
