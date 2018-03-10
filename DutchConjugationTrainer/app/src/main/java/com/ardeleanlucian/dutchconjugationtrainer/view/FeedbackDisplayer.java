package com.ardeleanlucian.dutchconjugationtrainer.view;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.ardeleanlucian.dutchconjugationtrainer.R;
import com.ardeleanlucian.dutchconjugationtrainer.model.Feedback;

import static com.ardeleanlucian.dutchconjugationtrainer.model.Verb.tenses;

/**
 * Created by ardelean on 12/25/17.
 */

public class FeedbackDisplayer extends Feedback {
    public FeedbackDisplayer(Context context) {
        super(context);
    }

    public static void informOnCorrectConsecutiveConjugations(
            Context context, View view, int numberOfConsecutiveAnswers) {

        String message = "You have " + String.valueOf(numberOfConsecutiveAnswers)
                + " correct consecutive answers!";

        CustomToast toast = new CustomToast(context, R.layout.good_score_toast, view.findViewById(R.id.good_score_container));
        toast.setToastMessage(message);
        toast.show();
    }

    public static void informOnWrongConsecutiveConjugations(
            Context context, View view, int numberOfConsecutiveAnswers) {

        String message = "You have " + String.valueOf(numberOfConsecutiveAnswers)
                + " wrong consecutive answers!";

        CustomToast toast = new CustomToast(context, R.layout.bad_score_toast, view.findViewById(R.id.bad_score_container));
        toast.setToastMessage(message);
        toast.show();
    }

    public static void informOnScoreVariation(
            Context context, View view, float newRating, int tenseIndex) {

        String message = "The score for " + tenses[tenseIndex] + " is now "
                + String.format("%.0f", newRating) + "%!";

        CustomToast toast = new CustomToast(context, R.layout.toast_score_variation, view.findViewById(R.id.bad_score_container));
        toast.setToastMessage(message);
        toast.show();
    }
}