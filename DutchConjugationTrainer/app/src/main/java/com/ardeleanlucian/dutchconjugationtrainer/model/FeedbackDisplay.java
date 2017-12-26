package com.ardeleanlucian.dutchconjugationtrainer.model;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import static com.ardeleanlucian.dutchconjugationtrainer.model.Verb.tenses;

/**
 * Created by ardelean on 12/25/17.
 */

public class FeedbackDisplay extends Feedback {
    public FeedbackDisplay(Context context) {
        super(context);
    }

    public static void informOnCorrectConsecutiveConjugations(View view, int numberOfConsecutiveAnswers) {

        String message = "You have " + String.valueOf(numberOfConsecutiveAnswers)
                + " correct consecutive answers!";
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static void informOnWrongConsecutiveConjugations(View view, int numberOfConsecutiveAnswers) {

        String message = "You have " + String.valueOf(numberOfConsecutiveAnswers)
                + " wrong consecutive answers!";
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static void informOnScoreVariation(View view, float newRating, int tenseIndex) {
        String message = "The score for " + tenses[tenseIndex] + " is now "
                + String.format("%.0f", newRating) + "%!";
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
