package com.ardeleanlucian.dutchconjugationtrainer;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by ardelean on 9/9/17.
 */

public class FeedbackDisplay extends Feedback {

    public FeedbackDisplay(Context context) {
        super(context);
    }

    public static void informOnCorrectConsecutiveAnswers(View view, int numberOfConsecutiveAnswers) {

        String message = "You have " + String.valueOf(numberOfConsecutiveAnswers)
                + " correct consecutive answers!";
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static void informOnWrongConsecutiveAnswers(View view, int numberOfConsecutiveAnswers) {

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
