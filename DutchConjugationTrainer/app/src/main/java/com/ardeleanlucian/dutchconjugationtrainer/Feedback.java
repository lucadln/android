package com.ardeleanlucian.dutchconjugationtrainer;

import android.content.Context;
import android.view.View;

/**
 * Created by ardelean on 9/7/17.
 */

public class Feedback extends ScoresHandler {

    /**
     * Variable to hold reference score values. Whenever a new
     *   score will differ from the reference with more than 10
     *   percent, the user will be notified of this
     */
    private static float[] previousRatings;

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
     * Variable to old the number of conjugations the user entered
     *    since the last time he received a progress feedback
     */
    private static int conjugationsSinceLastFeedback = 0;

    /**
     * Constructor method
     *
     * @param context
     */
    public Feedback(Context context) {
        super(context);

        setRatingsForAllTenses();
    }

    /**
     * Method to set the previousRatings values
     */
    public void setPreviousRatings() {
        // Set reference ratings for future comparisons
        previousRatings = new float[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            previousRatings[i] = (float) Math.floor(ratings[i] / 10) * 10;
        }
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
            if (previousRatings == null) {
                // If no reference rating is set yet then set it now
                setPreviousRatings();
            } else {
                // If reference ratings exist then compare them with the current ratings
                float variation = ratings[tenseIndex] - previousRatings[tenseIndex];
                if ((conjugationsSinceLastFeedback >= 10) && (Math.abs(variation) >= 10)) {
                    FeedbackDisplay.informOnScoreVariation(view, ratings[tenseIndex], tenseIndex);
                    resetConjugationsSinceLastFeedback();
                    previousRatings = ratings;
                }
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
}
