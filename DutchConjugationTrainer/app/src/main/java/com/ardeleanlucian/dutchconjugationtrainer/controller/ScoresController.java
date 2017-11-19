package com.ardeleanlucian.dutchconjugationtrainer.controller;

import android.content.Context;

import com.ardeleanlucian.dutchconjugationtrainer.model.Score;
import com.ardeleanlucian.dutchconjugationtrainer.model.SharedPreferencesHandler;

/**
 * Created by ardelean on 11/19/17.
 */

public class ScoresController extends Score {

    private Context context;
    private SharedPreferencesHandler sharedPreferencesHandler;

    /**
     * Constructor method
     *
     * @param context
     */
    public ScoresController(Context context) {
        this.context = context;
        sharedPreferencesHandler = new SharedPreferencesHandler(context);
    }

    /**
     * Set the number of correct answers for a particular tense
     *
     * @param tenseIndex
     */
    public void setNumberOfCorrectAnswers(int tenseIndex) {
        correctConjugatedVerbs[tenseIndex] = sharedPreferencesHandler.getNumberOfCorrectAnswers(tenseIndex);
    }

    /**
     * Set the total number of answers for a particular tense
     *
     * @param tenseIndex
     */
    public void setTotalNumberOfAnswers(int tenseIndex) {
        correctConjugatedVerbs[tenseIndex] = sharedPreferencesHandler.getTotalNumberOfAnswers(tenseIndex);
    }

    /**
     * Set the number of correct answers for all tenses
     */
    public void setNumberOfCorrectAnswersForAllTenses() {
        for (int i = 0; i < tenses.length; i++) {
            setNumberOfCorrectAnswers(i);
        }
    }

    /**
     * Set the total number of answers for all tenses
     */
    public void setTotalNumberOfAnswersForAllTenses() {
        for (int i = 0; i < tenses.length; i++) {
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
        return correctConjugatedVerbs[tenseIndex];
    }

    /**
     * Get the total number of answers for a particular tense
     *
     * @param tenseIndex
     * @return
     */
    public int getTotalNumberOfAnswers(int tenseIndex) {
        setTotalNumberOfAnswers(tenseIndex);
        return totalConjugatedVerbs[tenseIndex];
    }

    /**
     * Get the number of correct answers for all tenses
     *
     * @return
     */
    public int[] getNumberOfCorrectAnswersForAllTenses() {
        setNumberOfCorrectAnswersForAllTenses();
        return correctConjugatedVerbs;
    }

    /**
     * Get the total number of answers for all tenses
     *
     * @return
     */
    public int[] getTotalNumberOfAnswersForAllTenses() {
        setTotalNumberOfAnswersForAllTenses();
        return totalConjugatedVerbs;
    }

    /**
     * Method to calculate and set ratings for a particular tense
     *
     * @param tenseIndex
     */
    public void setRating(int tenseIndex) {
        setNumberOfCorrectAnswers(tenseIndex);
        setTotalNumberOfAnswers(tenseIndex);

        if (totalConjugatedVerbs[tenseIndex] == 0) {
            rating[tenseIndex] = 100f;
        } else {
            rating[tenseIndex]
                    = correctConjugatedVerbs[tenseIndex] * 100f / totalConjugatedVerbs[tenseIndex] ;
        }
    }

    /**
     * Method to set ratings for all tenses
     */
    public void setRatingsForAllTenses() {
        for (int i = 0; i < tenses.length; i++) {
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
        return rating[tenseIndex];
    }

    /**
     * Method to get ratings for all tenses
     *
     * @return
     */
    public float[] getRatingsForAllTenses() {
        setRatingsForAllTenses();
        return rating;
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
            correctConjugatedVerbs[tenseIndex]++;
            totalConjugatedVerbs[tenseIndex]++;
        } else {
            totalConjugatedVerbs[tenseIndex]++;
        }

        // Save the new numbers in the android Shared Preferences
        sharedPreferencesHandler.updateNumberOfCorrectAnswers(tenseIndex, correctConjugatedVerbs[tenseIndex]);
        sharedPreferencesHandler.updateTotalNumberOfAnswers(tenseIndex, totalConjugatedVerbs[tenseIndex]);
    }

    /**
     * Method to reset all ratings
     */
    public void resetScores() {
//        Feedback fdback = new Feedback(context); @TODO when adding the feedback part
        for (int tenseIndex = 0; tenseIndex < tenses.length; tenseIndex++) {
            sharedPreferencesHandler.updateNumberOfCorrectAnswers(tenseIndex, 0);
            sharedPreferencesHandler.updateTotalNumberOfAnswers(tenseIndex, 0);

//            fdback.updateConjugationsSinceLastMilestone(context, tenseIndex, 0); @TODO when adding the feedback part
        }
    }
}
