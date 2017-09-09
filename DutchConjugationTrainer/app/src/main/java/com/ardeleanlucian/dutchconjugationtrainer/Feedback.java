package com.ardeleanlucian.dutchconjugationtrainer;

import android.view.View;

/**
 * Created by ardelean on 9/7/17.
 */

public class Feedback {

    /**
     * Variable to hold reference score values. Whenever a new
     *   score will differ from the reference with more than 10
     *   percent, the user will be notified of this
     */
    private static float[] previousScore;

    /**
     * Variable to hold the current user scores
     */
    private static float[] currentScore;

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
     * Static method to give feedback on the number of correct
     *   consecutive answers
     */
    public static void giveFeedbackOnCorrectConsecutiveAnswers(View view) {
        // @TODO WIP
    }

    /**
     * Static method to give feedback on the number of wrong
     *   consecutive answers
     */
    public static void giveFeedbackOnWrongConsecutiveAnswers(View view) {
        // @TODO WIP
    }

    /**
     * Static method to give feedback on the score variation
     */
    public static void giveFeedbackOnScoreVariations() {
        // @TODO WIP
    }

    /**
     * Static method to decide if progress feedback is necessary
     *   (and if yes to call the corresponding feedback methods)
     */
    public static void giveFeedbackIfNecessary(View view) {
        // @TODO WIP

        if (correctConsecutiveAnswers == 5) {
            giveFeedbackOnCorrectConsecutiveAnswers(
                    view, "correctConsecutiveAnswers", correctConsecutiveAnswers);
        } else if (correctConsecutiveAnswers == 10) {
            giveFeedbackOnCorrectConsecutiveAnswers(
                    view, "correctConsecutiveAnswers", correctConsecutiveAnswers);
            resetCorrectConsecutiveAnswers();
        }

        if (wrongConsecutiveAnswers == 5) {
            giveFeedbackOnWrongConsecutiveAnswers(
                    view, "wrongConsecutiveAnswers", wrongConsecutiveAnswers);
        } else if (wrongConsecutiveAnswers == 10) {
            giveFeedbackOnWrongConsecutiveAnswers(
                    view, "wrongConsecutiveAnswers", wrongConsecutiveAnswers);
            resetWrongConsecutiveAnswers();
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
}
