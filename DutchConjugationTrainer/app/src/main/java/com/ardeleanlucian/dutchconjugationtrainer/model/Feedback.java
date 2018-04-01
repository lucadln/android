package com.ardeleanlucian.dutchconjugationtrainer.model;

import android.content.Context;
import android.view.View;

import com.ardeleanlucian.dutchconjugationtrainer.view.FeedbackDisplayer;

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
     *    since the last time he received a feedback
     */
    private static int conjugationsCountSinceLastFeedback = 0;

    private Context context;

    /**
     * Constructor method
     *
     * @param context
     */
    public Feedback(Context context) {
        super(new SharedPreferencesHandler(context));
        this.context = context;
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
     * Method to decide if progress feedback is necessary
     *   (and if yes to call the corresponding feedback display methods)
     */
    public void giveFeedbackIfNecessary(boolean giveFeedbackPreference, View view, int spinnerIndex) {

        if (giveFeedbackPreference == true) {

            /*
             * Give feedback on correct consecutive conjugations
             */
            if (correctConsecutiveConjugationsCount == 7) {
                FeedbackDisplayer.informOnCorrectConsecutiveConjugations(context, view, correctConsecutiveConjugationsCount);
            } else if (correctConsecutiveConjugationsCount == 15) {
                FeedbackDisplayer.informOnCorrectConsecutiveConjugations(context, view, correctConsecutiveConjugationsCount);
            }

            /*
             * Give feedback on wrong consecutive conjugations
             */
            if (wrongConsecutiveConjugationsCount == 7) {
                FeedbackDisplayer.informOnWrongConsecutiveConjugations(
                        context, view, wrongConsecutiveConjugationsCount);
            } else if (wrongConsecutiveConjugationsCount == 15) {
                FeedbackDisplayer.informOnWrongConsecutiveConjugations(
                        context, view, wrongConsecutiveConjugationsCount);
            }

            /*
             * Give feedback on score variations
             */
            if (referenceScores == null) { // If no reference score is set yet then set it now
                calculateReferenceScores();
            } else { // If reference scores already exist then compare them with the current scores
                float variation = getAllScores()[spinnerIndex] - referenceScores[spinnerIndex];
//                if ((conjugationsCountSinceLastFeedback >= 10) && (Math.abs(variation) >= 10)) {
                if (1 == 1) {
                    FeedbackDisplayer.informOnScoreVariation(context, view, getAllScores()[spinnerIndex], spinnerIndex);
                    resetConjugationsCountSinceLastFeedback();
                    referenceScores = getAllScores();
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
