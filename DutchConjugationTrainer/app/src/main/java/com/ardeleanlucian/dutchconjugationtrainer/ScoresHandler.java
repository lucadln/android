package com.ardeleanlucian.dutchconjugationtrainer;

import android.content.Context;

/**
 * Created by ardelean on 8/20/17.
 */

public class ScoresHandler extends Scores {

    SharedPreferencesHandler sharedPreferencesHandler;
    int numberOfTenses;
    Context context;

    /**
     * Constructor method
     *
     * @param context
     */
    public ScoresHandler(Context context) {
        this.context = context;
        numberOfTenses = tenses.length;

        // Create a new object to read shared preferences
        sharedPreferencesHandler = new SharedPreferencesHandler(context, numberOfTenses);
    }

    /**
     * Set the number of correct answers for a particular tense
     *
     * @param tenseIndex
     */
    public void setNumberOfCorrectAnswers(int tenseIndex) {
        correctAnswers[tenseIndex] = sharedPreferencesHandler.getNumberOfCorrectAnswers(tenseIndex);
    }

    /**
     * Set the total number of answers for a particular tense
     *
     * @param tenseIndex
     */
    public void setTotalNumberOfAnswers(int tenseIndex) {
        totalAnswers[tenseIndex] = sharedPreferencesHandler.getTotalNumberOfAnswers(tenseIndex);
    }

    /**
     * Set the number of correct answers for all tenses
     */
    public void setNumberOfCorrectAnswersForAllTenses() {
        for (int i = 0; i < numberOfTenses; i++) {
            setNumberOfCorrectAnswers(i);
        }
    }

    /**
     * Set the total number of answers for all tenses
     */
    public void setTotalNumberOfAnswersForAllTenses() {
        for (int i = 0; i < numberOfTenses; i++) {
            setTotalNumberOfAnswers(i);
        }
    }

    /**
     * Get the number of correct answers for a particular tense
     *
     * @param tenseIndex
     * @return
     */
    public int getNumberOfCorrectAnswers(int tenseIndex) {
        setNumberOfCorrectAnswers(tenseIndex);
        return correctAnswers[tenseIndex];
    }

    /**
     * Get the total number of answers for a particular tense
     *
     * @param tenseIndex
     * @return
     */
    public int getTotalNumberOfAnswers(int tenseIndex) {
        setTotalNumberOfAnswers(tenseIndex);
        return totalAnswers[tenseIndex];
    }

    /**
     * Get the number of correct answers for all tenses
     *
     * @return
     */
    public int[] getNumberOfCorrectAnswersForAllTenses() {
        setNumberOfCorrectAnswersForAllTenses();
        return correctAnswers;
    }

    /**
     * Get the total number of answers for all tenses
     *
     * @return
     */
    public int[] getTotalNumberOfAnswersForAllTenses() {
        setTotalNumberOfAnswersForAllTenses();
        return totalAnswers;
    }

    /**
     * Method to calculate and set ratings for a particular tense
     *
     * @param tenseIndex
     */
    public void setRating(int tenseIndex) {
        setNumberOfCorrectAnswers(tenseIndex);
        setTotalNumberOfAnswers(tenseIndex);

        if (totalAnswers[tenseIndex] == 0) {
            ratings[tenseIndex] = 100f;
        } else {
            ratings[tenseIndex] = correctAnswers[tenseIndex] * 100f / totalAnswers[tenseIndex] ;
        }
    }

    /**
     * Method to set ratings for all tenses
     */
    public void setRatingsForAllTenses() {
        for (int i = 0; i < numberOfTenses; i++) {
            setRating(i);
        }
    }

    /**
     * Method to get ratings for a particular tense
     *
     * @param tenseIndex
     * @return
     */
    public float getRatings(int tenseIndex) {
        setRating(tenseIndex);
        return ratings[tenseIndex];
    }

    /**
     * Method to get ratings for all tenses
     *
     * @return
     */
    public float[] getRatingsForAllTenses() {
        setRatingsForAllTenses();
        return ratings;
    }

    /**
     * Method to update the number of given answers (correct and total answers)
     *
     * @param correctAnswer
     * @param tenseIndex
     */
    public void updateNumberOfAnswers(boolean correctAnswer, int tenseIndex) {
        // Obtain current scores
        setNumberOfCorrectAnswers(tenseIndex);
        setTotalNumberOfAnswers(tenseIndex);

        // Update object variables
        if (correctAnswer) {
            correctAnswers[tenseIndex]++;
            totalAnswers[tenseIndex]++;
        } else {
            totalAnswers[tenseIndex]++;
        }

        // Save the new numbers in the android Shared Preferences
        sharedPreferencesHandler.updateNumberOfCorrectAnswers(tenseIndex, correctAnswers[tenseIndex]);
        sharedPreferencesHandler.updateTotalNumberOfAnswers(tenseIndex, totalAnswers[tenseIndex]);
    }

    /**
     * Method to reset all ratings
     */
    public void resetScores() {
        Feedback fdback = new Feedback(context);
        for (int tenseIndex = 0; tenseIndex < numberOfTenses; tenseIndex++) {
            sharedPreferencesHandler.updateNumberOfCorrectAnswers(tenseIndex, 0);
            sharedPreferencesHandler.updateTotalNumberOfAnswers(tenseIndex, 0);

            fdback.updateConjugationsSinceLastMilestone(context, tenseIndex, 0);
        }
    }
}
