package com.ardeleanlucian.dutchconjugationtrainer.model;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ardeleanlucian.dutchconjugationtrainer.view.FeedbackActivity;
import static com.ardeleanlucian.dutchconjugationtrainer.model.Verb.tenses;

/**
 * Created by ardelean on 12/24/17.
 */

public class Feedback extends ScoreHandler {

    /**
     * Variable to hold reference score values. Whenever a new
     *   score will differ from the reference with more than 10
     *   percent, the user will be notified of this
     */
    private static float[] referenceScores;

    /**
     * Variable to hold the number of correct consecutive answers
     *   that were given by the user
     */
    private static int correctConsecutiveConjugationsCount = 0;

    /**
     * Variable to hold the number of wrong consecutive answers
     *   that were given by the user
     */
    private static int wrongConsecutiveConjugationsCount = 0;

    /**
     * Variable to hold the number of conjugations the user entered
     *    since the last time he received a feedback through a snackbar message
     */
    private static int conjugationsCountSinceLastFeedback = 0;

    /**
     * Variable to hold the number of conjugated verbs for each
     *   tense in particular
     */
    private static int[] conjugationsCount;

    /**
     * Variable to hold the number of verbs that were conjugated since
     *   the last milestone feedback. This is because it is not desirable
     *   to annoy the user interrupting his activity too often for feedback
     */
    private static int[] conjugationsCountSinceLastMilestone;
    private Context context;

    /**
     * Constructor method
     *
     * @param context
     */
    public Feedback(Context context) {
        super(new SharedPreferencesHandler(context));
        this.context = context;

        obtainConjugationsCount();
        obtainConjugationsCountSinceLastMilestone();
    }

    /**
     * Method to obtain the total number of conjugated verbs
     */
    public void obtainConjugationsCount() {
        ScoreHandler scoreHandler = new ScoreHandler(new SharedPreferencesHandler(context));
        conjugationsCount = scoreHandler.getAllTotalConjugationsCount();
    }

    /**
     * Method to obtain the total number of conjugated verbs since the last milestone
     */
    public void obtainConjugationsCountSinceLastMilestone() {
        conjugationsCountSinceLastMilestone = new int[tenses.length];
        SharedPreferencesHandler sharedPreferencesHandler = new SharedPreferencesHandler(context);
        for (int spinnerIndex = 0; spinnerIndex < tenses.length; spinnerIndex++) {
            conjugationsCountSinceLastMilestone[spinnerIndex]
                    = sharedPreferencesHandler.getConjugationsCountSinceLastMilestone(spinnerIndex);
        }
    }

    /**
     * Method to increment the number of conjugations since the last milestone
     */
    public void incrementConjugationsCountSinceLastMilestone() {
        int spinnerIndex = sharedPreferencesHandler.getSpinnerIndex();
        conjugationsCountSinceLastMilestone[spinnerIndex]++;
        sharedPreferencesHandler.updateConjugationsCountSinceLastMilestone(
                conjugationsCountSinceLastMilestone[spinnerIndex], spinnerIndex);
    }

    /**
     * Method to calculate reference score values
     */
    public void calculateReferenceScores() {
        // Initialize variable
        referenceScores = new float[tenses.length];
        for (int i = 0; i < tenses.length; i++) {
            referenceScores[i] = (float) Math.floor(getAllScores()[i] / 10) * 10;
        }
    }

    /**
     * Method to update the number of conjugated verbs since last milestone
     * @param tenseIndex
     * @param newValue
     */
    public void updateConjugationsCountSinceLastMilestone(int tenseIndex, int newValue) {
        SharedPreferencesHandler prefs = new SharedPreferencesHandler(context);
        prefs.updateConjugationsCountSinceLastMilestone(tenseIndex, newValue);
    }

    /**
     * Method to decide if progress feedback is necessary
     *   (and if yes to call the corresponding feedback methods)
     */
    public void giveFeedbackIfNecessary(boolean giveFeedback, View view, int spinnerIndex) {

        if (giveFeedback) {

            /*
             * Give feedback on correct consecutive conjugations
             */
            if (correctConsecutiveConjugationsCount == 7) {
                FeedbackDisplay.informOnCorrectConsecutiveConjugations(view, correctConsecutiveConjugationsCount);
            } else if (correctConsecutiveConjugationsCount == 15) {
                FeedbackDisplay.informOnCorrectConsecutiveConjugations(view, correctConsecutiveConjugationsCount);
            }

            /*
             * Give feedback on wrong consecutive conjugations
             */
            if (wrongConsecutiveConjugationsCount == 7) {
                FeedbackDisplay.informOnWrongConsecutiveConjugations(view, wrongConsecutiveConjugationsCount);
            } else if (wrongConsecutiveConjugationsCount == 15) {
                FeedbackDisplay.informOnWrongConsecutiveConjugations(view, wrongConsecutiveConjugationsCount);
            }

            /*
             * Give feedback on score variations
             */
            if (referenceScores == null) {
                // If no reference score is set yet then set it now
                calculateReferenceScores();
            } else {
                // If reference scores exist then compare them with the current scores
                float variation = getAllScores()[spinnerIndex] - referenceScores[spinnerIndex];
                if ((conjugationsCountSinceLastFeedback >= 10) && (Math.abs(variation) >= 10)) {
                    FeedbackDisplay.informOnScoreVariation(view, getAllScores()[spinnerIndex], spinnerIndex);
                    resetConjugationsCountSinceLastFeedback();
                    referenceScores = getAllScores();
                }
            }

            /*
             * Give feedback on the number of conjugated verbs
             */
            if (((conjugationsCount[spinnerIndex] % 100) == 0)
                    && (conjugationsCount[spinnerIndex] != 0)) {

                Intent displayFeedback = new Intent(view.getContext(), FeedbackActivity.class);
                displayFeedback.putExtra("feedbackMotivation", "conjugationsCount");
                displayFeedback.putExtra("conjugationsCount", conjugationsCount[spinnerIndex]);
                displayFeedback.putExtra("tense", tenses[spinnerIndex]);
                view.getContext().startActivity(displayFeedback);
            } else if (conjugationsCountSinceLastMilestone[spinnerIndex] > 75) {

                if (getAllScores()[spinnerIndex] == 100f) {
                    Intent displayFeedback = new Intent(view.getContext(), FeedbackActivity.class);
                    displayFeedback.putExtra("feedbackMotivation", "ratingEquals100");
                    displayFeedback.putExtra("tense", tenses[spinnerIndex]);
                    view.getContext().startActivity(displayFeedback);
                    updateConjugationsCountSinceLastMilestone(spinnerIndex, 0);
                } else if (getAllScores()[spinnerIndex] > 90f) {
                    Intent displayFeedback = new Intent(view.getContext(), FeedbackActivity.class);
                    displayFeedback.putExtra("feedbackMotivation", "ratingOver90");
                    displayFeedback.putExtra("tense", tenses[spinnerIndex]);
                    view.getContext().startActivity(displayFeedback);
                    updateConjugationsCountSinceLastMilestone(spinnerIndex, 0);
                } else if (getAllScores()[spinnerIndex] < 10f) {
                    Intent displayFeedback = new Intent(view.getContext(), FeedbackActivity.class);
                    displayFeedback.putExtra("feedbackMotivation", "ratingUnder10");
                    displayFeedback.putExtra("tense", tenses[spinnerIndex]);
                    view.getContext().startActivity(displayFeedback);
                    updateConjugationsCountSinceLastMilestone(spinnerIndex, 0);
                } else if (getAllScores()[spinnerIndex] < 40f) {
                    Intent displayFeedback = new Intent(view.getContext(), FeedbackActivity.class);
                    displayFeedback.putExtra("feedbackMotivation", "ratingUnder40");
                    displayFeedback.putExtra("tense", tenses[spinnerIndex]);
                    view.getContext().startActivity(displayFeedback);
                    updateConjugationsCountSinceLastMilestone(spinnerIndex, 0);
                }
            }
        }
    }

    /**
     * Static method to increment the number of correct consecutive answers
     */
    public static void incrementCorrectConsecutiveConjugationsCount() {
        correctConsecutiveConjugationsCount++;
    }

    /**
     * Static method to reset the number of correct consecutive answers
     */
    public static void resetCorrectConsecutiveConjugationsCount() {
        correctConsecutiveConjugationsCount = 0;
    }

    /**
     * Static method to increment the number of wrong consecutive answers
     */
    public static void incrementWrongConsecutiveConjugationsCount() {
        wrongConsecutiveConjugationsCount++;
    }

    /**
     * Static method to reset the number of wrong consecutive answers
     */
    public static void resetWrongConsecutiveConjugationsCount() {
        wrongConsecutiveConjugationsCount = 0;
    }

    /**
     * Static method to increment the conjugationsCountSinceLastFeedback
     */
    public static void incrementConjugationsCountSinceLastFeedback() {
        conjugationsCountSinceLastFeedback++;
    }

    /**
     * Static method to reset the number of conjugations given since last feedback
     */
    public static void resetConjugationsCountSinceLastFeedback() {
        conjugationsCountSinceLastFeedback = 0;
    }
}
