package com.ardeleanlucian.dutchconjugationtrainer;

import android.content.Context;
import android.view.View;

/**
 * Created by ardelean on 9/7/17.
 */


/**
 * @ TODO Get rid of the simple incrementing and reset methods
 * @ TODO Split the giveFeedbackIfNecessary in more methods and only use it as a method to call those
 */

public class Feedback extends ScoresHandler {

    /**
     * Variable to hold reference score values. Whenever a new
     *   score will differ from the reference with more than 10
     *   percent, the user will be notified of this
     */
    private static float[] referenceRatings;

    /**
     * Variable to hold the number of correct consecutive answers
     *   that were given by the user
     */
    private static int correctConsecutiveAnswers = 0;

    /**
     * Variable to hold the number of wrong consecutive answers
     *   that were given by the user
     */
    private static int wrongConsecutiveAnswers = 0;

    /**
     * Variable to hold the number of conjugations the user entered
     *    since the last time he received a progress feedback
     */
    private static int conjugationsSinceLastFeedback = 0;

    /**
     * Variable to hold the number of conjugated verbs for each
     *   tense in particular
     */
    private static int[] numberOfConjugatedVerbs;

    /**
     * Constructor method
     *
     * @param context
     */
    public Feedback(Context context) {
        super(context);

        setRatingsForAllTenses();
        setNumberOfConjugatedVerbs(context);
    }

    /**
     * Method to set the referenceRatings values
     */
    public void setPreviousRatings() {
        // Set reference ratings for future comparisons
        referenceRatings = new float[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            referenceRatings[i] = (float) Math.floor(ratings[i] / 10) * 10;
        }
    }

    /**
     * Method to set the number of conjugated verbs
     */
    public void setNumberOfConjugatedVerbs(Context context) {
        numberOfConjugatedVerbs = new int[ratings.length];
        SharedPreferencesHandler prefs = new SharedPreferencesHandler(context, ratings.length);
        for (int i = 0; i < ratings.length; i++) {
            numberOfConjugatedVerbs[i] = prefs.getNumberOfConjugations(i);
        }
    }

    /**
     * Method to update the number the conjugated verbs in
     *   the android Shared Preferences
     */
    public void updateNumberOfConjugatedVerbs(Context context, int tenseIndex, int newValue) {
        SharedPreferencesHandler prefs = new SharedPreferencesHandler(context, ratings.length);
        prefs.updateNumberOfConjugations(tenseIndex, newValue);
    }

    /**
     * Method to decide if progress feedback is necessary
     *   (and if yes to call the corresponding feedback methods)
     */
    public void giveFeedbackIfNecessary(boolean giveFeedback, View view, int tenseIndex) {

        if (giveFeedback) {

            /*
             * Give feedback on correct consecutive answers
             */
            if (correctConsecutiveAnswers == 7) {
                FeedbackDisplay.informOnCorrectConsecutiveAnswers(view, correctConsecutiveAnswers);
            } else if (correctConsecutiveAnswers == 15) {
                FeedbackDisplay.informOnCorrectConsecutiveAnswers(view, correctConsecutiveAnswers);
            }

            /*
             * Give feedback on wrong consecutive answers
             */
            if (wrongConsecutiveAnswers == 7) {
                FeedbackDisplay.informOnWrongConsecutiveAnswers(view, wrongConsecutiveAnswers);
            } else if (wrongConsecutiveAnswers == 15) {
                FeedbackDisplay.informOnWrongConsecutiveAnswers(view, wrongConsecutiveAnswers);
            }

            /*
             * Give feedback on score variations
             */
            if (referenceRatings == null) {
                // If no reference rating is set yet then set it now
                setPreviousRatings();
            } else {
                // If reference ratings exist then compare them with the current ratings
                float variation = ratings[tenseIndex] - referenceRatings[tenseIndex];
                if ((conjugationsSinceLastFeedback >= 10) && (Math.abs(variation) >= 10)) {
                    FeedbackDisplay.informOnScoreVariation(view, ratings[tenseIndex], tenseIndex);
                    resetConjugationsSinceLastFeedback();
                    referenceRatings = ratings;
                }
            }

            /*
             * Give feedback on the number of conjugated verbs
             */
            if (((numberOfConjugatedVerbs[tenseIndex] % 100) == 0)
                    && (numberOfConjugatedVerbs[tenseIndex] != 0)) {
                /**
                 *
                 * @TODO Display feedback activity
                 */
            }

        }
    }

    /**
     * Static method to increment the number of correct consecutive answers
     */
    public static void incrementCorrectConsecutiveAnswers() {
        correctConsecutiveAnswers++;
    }

    /**
     * Static method to reset the number of correct consecutive answers to 0
     */
    public static void resetCorrectConsecutiveAnswers() {
        correctConsecutiveAnswers = 0;
    }

    /**
     * Static method to increment the number of wrong consecutive answers
     */
    public static void incrementWrongConsecutiveAnswers() {
        wrongConsecutiveAnswers++;
    }

    /**
     * Static method to reset the number of wrong consecutive answers to 0
     */
    public static void resetWrongConsecutiveAnswers() {
        wrongConsecutiveAnswers = 0;
    }

    /**
     * Static method to increment the conjugationsSinceLastFeedback
     */
    public static void incrementConjugationsSinceLastFeedback() {
        conjugationsSinceLastFeedback++;
    }

    /**
     * Static method to reset the number of conjugations given since last feedback
     */
    public static void resetConjugationsSinceLastFeedback() { conjugationsSinceLastFeedback = 0; }

    /**
     * Method to increment the number of conjugated verbs
     */
    public void incrementNumerOfConjugations(Context context, int tenseIndex) {
        numberOfConjugatedVerbs[tenseIndex]++;
        updateNumberOfConjugatedVerbs(context, tenseIndex, numberOfConjugatedVerbs[tenseIndex]);
    }
}
