package com.ardeleanlucian.dutchconjugationtrainer.model;

/**
 * Created by ardelean on 12/23/17.
 */

public class ScoreHandler extends Score {

    int numberOfTenses;
    SharedPreferencesHandler sharedPreferencesHandler;

    /**
     * Constructor method
     */
    public ScoreHandler(SharedPreferencesHandler sharedPreferencesHandler) {
        this.sharedPreferencesHandler = sharedPreferencesHandler;
        numberOfTenses = tenses.length;
    }

    /**
     * @return the number of correct conjugations for the tense in use
     */
    public int getCorrectTenseConjugationsCount() {
        return sharedPreferencesHandler.getCorrectConjugationsCount();
    }

    /**
     * @return the number of correct conjugations for a specific tense
     *         given by the spinnerIndex parameter
     */
    public int getCorrectTenseConjugationsCount(int spinnerIndex) {
        return sharedPreferencesHandler.getCorrectConjugationsCount(spinnerIndex);
    }

    /**
     * @return the number of correct conjugations for all tenses
     */
    public int[] getAllCorrectConjugationsCount() {
        int[] correctConjugations = new int[7];
        for (int spinnerIndex = 0; spinnerIndex < 7; spinnerIndex++) {
            correctConjugations[spinnerIndex] = getCorrectTenseConjugationsCount(spinnerIndex);
        }
        return correctConjugations;
    }

    /**
     * Method to increment the number of correctly conjugated verbs
     *   for the tense in use and save the result in the android's
     *   Shared Preferences
     */
    public void incrementCorrectConjugationsCount() {
        // Get current count
        int count = getCorrectTenseConjugationsCount();
        // Increment count
        count++;
        // Save the new value
        sharedPreferencesHandler.updateCorrectConjugationsCount(count);
    }

    /**
     * @return the total number of conjugated verbs for a certain tense
     */
    public int getTotalTenseConjugationsCount() {
        return sharedPreferencesHandler.getTotalConjugationsCount();
    }

    /**
     * @param spinnerIndex
     * @return the total number of conjugations for a specific tense
     *           given by the spinnerIndex parameter
     */
    public int getTotalTenseConjugationsCount(int spinnerIndex) {
        return sharedPreferencesHandler.getTotalConjugationsCount(spinnerIndex);
    }

    /**
     * @return the total number of conjugated verbs for all tenses
     */
    public int[] getAllTotalConjugationsCount() {
        int[] totalConjugationsCount = new int[7];
        for (int spinnerIndex = 0; spinnerIndex < 7; spinnerIndex++) {
            totalConjugationsCount[spinnerIndex] = getTotalTenseConjugationsCount(spinnerIndex);
        }
        return totalConjugationsCount;
    }

    /**
     * Method to increment the total number of conjugated verbs
     *   and save the result in the android's Shared Preferences
     */
    public void incrementTotalConjugationsCount() {
        // Get current count
        int count = getTotalTenseConjugationsCount();
        // Increment count
        count++;
        // Save the new value
        sharedPreferencesHandler.updateTotalConjugationsCount(count);
    }

    /**
     * @return the score for a specific tense
     */
    public float getScoreForTense(int spinnerIndex) {
        int correctConjugationsCount = sharedPreferencesHandler.getCorrectConjugationsCount(spinnerIndex);
        int totalConjugationsCount = sharedPreferencesHandler.getTotalConjugationsCount(spinnerIndex);
        if (totalConjugationsCount == 0) {
            return 100f;
        } else {
            return correctConjugationsCount * 100f / totalConjugationsCount;
        }
    }

    /**
     * @return the scores for all tenses
     */
    public float[] getAllScores() {
        float[] scores = new float[7];
        for (int spinnerIndex = 0; spinnerIndex < 8; spinnerIndex++) {
            scores[spinnerIndex] = getScoreForTense(spinnerIndex);
        }

        return scores;
    }

    /**
     * Method to reset the scores for all tenses
     */
    public void resetScores() {
        for (int spinnerPosition = 0; spinnerPosition < 8; spinnerPosition++) {
            sharedPreferencesHandler.resetCorrectConjugationsCount(0, spinnerPosition);
            sharedPreferencesHandler.resetTotalConjugationsCount(0, spinnerPosition);
        }
    }
}
