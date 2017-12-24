package com.ardeleanlucian.dutchconjugationtrainer.controller;

import android.content.Context;

import com.ardeleanlucian.dutchconjugationtrainer.model.FileReader;
import com.ardeleanlucian.dutchconjugationtrainer.model.ScoreHandler;
import com.ardeleanlucian.dutchconjugationtrainer.model.SharedPreferencesHandler;
import com.ardeleanlucian.dutchconjugationtrainer.model.UserAnswer;
import com.ardeleanlucian.dutchconjugationtrainer.model.Verb;

/**
 * Created by ardelean on 10/15/17.
 */

public class MainController {

    private Context context;
    private SharedPreferencesHandler sharedPreferencesHandler;
    private UserAnswer userAnswer;
    private Verb verb;
    private boolean answerCorrectness;
    private ScoreHandler scoreHandler;

    /**
     * Constructor method
     * @param context
     */
    public MainController(Context context) {
        this.context = context;
        sharedPreferencesHandler = new SharedPreferencesHandler(context);
        scoreHandler = new ScoreHandler(sharedPreferencesHandler);
    }

    /**
     * @return the verb in use
     */
    public Verb getVerb() {
        return verb;
    }

    /**
     * Method to read the next verb from the verbs file
     */
    private void obtainNextVerb() {
        FileReader fileReader = new FileReader();
        this.verb = fileReader.readNextVerb(context, sharedPreferencesHandler);
    }

    /**
     * @return read only preference
     */
    public boolean obtainReadOnlyPreference() { return sharedPreferencesHandler.isReadOnly(); }

    /**
     * @return show translation preference
     */
    public boolean obtainShowTranslationPreference() { return sharedPreferencesHandler.isTranslationDisplayed(); }

    /**
     * @return give feedback preference
     */
    public boolean obtainGiveFeedbackPreference() { return sharedPreferencesHandler.isFeedbackGiven(); }

    /**
     * @return the spinner index
     */
    public int obtainSpinnerIndex() {
        return sharedPreferencesHandler.getSpinnerIndex();
    }

    /**
     * Update the value of the readOnly preference in the android's memory
     *
     * @param newValue
     */
    public void updateReadOnlyPreference(boolean newValue) {
        sharedPreferencesHandler.setIfReadOnly(newValue);
    }

    /**
     * Update the value of the showTranslation preference in the android's memory
     *
     * @param newValue
     */
    public void updateShowTranslationPreference(boolean newValue) {
        sharedPreferencesHandler.setIfTranslationDisplayed(newValue);
    }

    /**
     * Update the giveFeedback preference in the android's memory
     *
     * @param newValue
     */
    public void updateGiveFeedbackPreference(boolean newValue) {
        sharedPreferencesHandler.setIfFeedbackGiven(newValue);
    }

    /**
     * Method to update the value of the spinner index in the android's memory
     *
     * @param newValue
     */
    public void updateSpinnerPosition(int newValue) {
        sharedPreferencesHandler.setSpinnerIndex(newValue);
    }

    /**
     * Actions to take when a textfield item looses focus
     * @param conjugationIndex
     * @param answer
     */
    public void onFieldFocusChange(int conjugationIndex, String answer) {
        answerCorrectness = userAnswer.isAnswerInputCorrect(conjugationIndex, answer);
        userAnswer.updateConjugationStatus(answerCorrectness);
    }

    public void onActivityCreate() {
        obtainNextVerb();
    };

    public void onClickNext() {
        if (userAnswer.isVerbCorrectlyConjugated()) {
            // Increment the correct and total conjugation count
            scoreHandler.incrementCorrectConjugationsCount();
            scoreHandler.incrementTotalConjugationsCount();
        } else {
            // Increment the total conjugation count
            scoreHandler.incrementTotalConjugationsCount();
        }

        // Bring on the next verb and prepare a new userAnswer object
        obtainNextVerb();
        userAnswer = new UserAnswer(sharedPreferencesHandler.getSpinnerIndex(), verb);
    }

    public void onClickSkip() {
        if (!userAnswer.isVerbCorrectlyConjugated()) {
            // Increment the total conjugation count
            scoreHandler.incrementTotalConjugationsCount();
        }

        // Bring on the next verb and prepare a new userAnswer object
        obtainNextVerb();
        userAnswer = new UserAnswer(sharedPreferencesHandler.getSpinnerIndex(), verb);
    }

    public void onSpinnerSelection() {
        if (userAnswer.isVerbCorrectlyConjugated()) {
            if (userAnswer.getNumberOfConjugatedPersons() == 6) {
                // Increment the correct and total conjugation count
                scoreHandler.incrementCorrectConjugationsCount();
                scoreHandler.incrementTotalConjugationsCount();
            }
        } else {
            scoreHandler.incrementTotalConjugationsCount();
        }

        userAnswer = new UserAnswer(sharedPreferencesHandler.getSpinnerIndex(), verb);
    }

    public void onMenuSelection() {
        if (userAnswer.isVerbCorrectlyConjugated()) {
            if (userAnswer.getNumberOfConjugatedPersons() == 6) {
                // Increment the correct and total conjugation count
                scoreHandler.incrementCorrectConjugationsCount();
                scoreHandler.incrementTotalConjugationsCount();
            }
        } else {
            scoreHandler.incrementTotalConjugationsCount();
        }

    }

    public void onScreenTap() {
    }

    /**
     * Returns whether the newly inputted field is correctly conjugated
     */
    public boolean isAnswerCorrect() {
        return answerCorrectness;
    }
}
