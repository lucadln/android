package com.ardeleanlucian.dutchconjugationtrainer;

/**
 * Created by ardelean on 9/7/17.
 */

public class ProgressFeedback {

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
}
