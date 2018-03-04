package com.ardeleanlucian.dutchconjugationtrainer.model;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ardeleanlucian.dutchconjugationtrainer.R;

import static com.ardeleanlucian.dutchconjugationtrainer.model.Verb.tenses;

/**
 * Created by ardelean on 12/25/17.
 */

public class FeedbackDisplayer extends Feedback {
    public FeedbackDisplayer(Context context) {
        super(context);
    }

    public static void informOnCorrectConsecutiveConjugations(View view, int numberOfConsecutiveAnswers) {

        String message = "You have " + String.valueOf(numberOfConsecutiveAnswers)
                + " correct consecutive answers!";
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static void informOnWrongConsecutiveConjugations(
            Context context, View view, int numberOfConsecutiveAnswers) {
        String message = "You have " + String.valueOf(numberOfConsecutiveAnswers)
                + " wrong consecutive answers!";

        LayoutInflater inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.bad_score_toast,
                (ViewGroup) view.findViewById(R.id.bad_score_container));

        TextView messageText = (TextView) layout.findViewById(R.id.message);
        messageText.setText(message);

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public static void informOnScoreVariation(View view, float newRating, int tenseIndex) {
        String message = "The score for " + tenses[tenseIndex] + " is now "
                + String.format("%.0f", newRating) + "%!";
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
