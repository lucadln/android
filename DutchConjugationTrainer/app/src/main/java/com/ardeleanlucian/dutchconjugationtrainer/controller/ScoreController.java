package com.ardeleanlucian.dutchconjugationtrainer.controller;

import android.content.Context;

import com.ardeleanlucian.dutchconjugationtrainer.model.Score;
import com.ardeleanlucian.dutchconjugationtrainer.model.ScoreHandler;
import com.ardeleanlucian.dutchconjugationtrainer.model.SharedPreferencesHandler;

/**
 * Created by ardelean on 11/19/17.
 */

public class ScoreController extends Score {

    Context context;
    SharedPreferencesHandler sharedPreferencesHandler;
    ScoreHandler scoreHandler;

    /**
     * Constructor method
     * @param context
     */
    public ScoreController(Context context) {
        this.context = context;
        sharedPreferencesHandler = new SharedPreferencesHandler(context);
        scoreHandler = new ScoreHandler(sharedPreferencesHandler);
    }

    /**
     * Actions to be taken on score reset
     */
    public void onClickReset() {
        scoreHandler.resetScores();

        // Also reset the number of correct or wrong consecutive answers
        //        Feedback.resetCorrectConsecutiveAnswers(); @TODO when plugin in the feedback part
        //        Feedback.resetWrongConsecutiveAnswers(); @TODO when plugin in the feedback part
    }

    /**
     * @return an array containing the correct conjugations for each tense in particular
     */
    public int[] getCorrectConjugationsCount() {
        return scoreHandler.getAllCorrectConjugationsCount();
    }

    /**
     * @return an array containing the total conjugations for each tense in particular
     */
    public int[] getTotalConjugationsCount() {
        return scoreHandler.getAllTotalConjugationsCount();
    }

    /**
     * @return an array containing the scores for each tense in particular
     */
    public float[] getScores() {
        return scoreHandler.getAllScores();
    }
}
