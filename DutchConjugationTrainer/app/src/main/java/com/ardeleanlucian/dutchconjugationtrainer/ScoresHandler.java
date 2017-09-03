package com.ardeleanlucian.dutchconjugationtrainer;

import android.content.Context;

/**
 * Created by ardelean on 8/20/17.
 */

public class ScoresHandler extends Scores {

    SharedPreferencesHandler sharedPreferencesHandler;
    int numberOfTenses;

    /**
     * Constructor method
     *
     * @param context
     */
    public ScoresHandler(Context context) {
        numberOfTenses = tenses.length;

        // Create a new object to read shared preferences
        sharedPreferencesHandler = new SharedPreferencesHandler(context, numberOfTenses);
    }

    /**
     * Method to set the number of correct answers for each available tense
     *
     * @return
     */
    public void setNumberOfCorrectAnswers() {
        correctAnswers = sharedPreferencesHandler.getNumberOfCorrectAnswers();
    }


    /**
     * Method to set the total number of answers for each available tense
     *
     * @return
     */
    public void setTotalNumberOfAnswers() {
        totalAnswers = sharedPreferencesHandler.getTotalNumberOfAnswers();
    }

    /**
     * Method to set the ratings for every tense. The rating is
     *   calculated as (correct answers / total answers * 100)
     */
    public void setRatings() {

        setNumberOfCorrectAnswers();
        setTotalNumberOfAnswers();

        // Iterate for each tense
        for (int i = 0; i < tenses.length; i++) {
            if (totalAnswers[i] == 0) {
                ratings[i] = 100f;
            } else {
                ratings[i] = correctAnswers[i] / totalAnswers[i] * 100f;
            }
        }
    }

    /**
     * Method to update ratings
     *
     * @param correctAnswer
     * @param tenseInUse
     */
    public void updateRatings(boolean correctAnswer, int tenseInUse) {
        // Obtain current scores
        setNumberOfCorrectAnswers();
        setTotalNumberOfAnswers();

        // Update scores
        if (correctAnswer) {
            correctAnswers[tenseInUse]++;
            totalAnswers[tenseInUse]++;
        } else {
            totalAnswers[tenseInUse]++;
        }

        // Save the new scores in the phone memory
        sharedPreferencesHandler.updateNumberOfCorrectAnswers(tenseInUse, correctAnswers[tenseInUse]);
        sharedPreferencesHandler.updateTotalNumberOfAnswers(tenseInUse, totalAnswers[tenseInUse]);
    }

    /**
     * Method to reset all ratings
     */
    public void resetScores() {
        for (int i = 0; i < numberOfTenses; i++) {
            sharedPreferencesHandler.updateNumberOfCorrectAnswers(i, 0);
            sharedPreferencesHandler.updateTotalNumberOfAnswers(i, 0);
        }
    }
}
