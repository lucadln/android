package com.ardeleanlucian.dutchconjugationtrainer.controller;

import android.content.Context;

import com.ardeleanlucian.dutchconjugationtrainer.model.FileReader;
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

    /**
     * Constructor method
     * @param context
     */
    public MainController(Context context) {
        this.context = context;
        sharedPreferencesHandler = new SharedPreferencesHandler(context);
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
     * Method to check if the newly inputted answer is correct
     * @param conjugationIndex
     * @param answer
     * @return
     */
    // @TODO checkIfAnswerCorrect to be changed with something like onFocusChange?
    // @TODO is it correct to do both a return and an update for the conjugation status?
    public boolean checkIfAnswerCorrect(int conjugationIndex, String answer) {
        boolean answerCorrectness = userAnswer.isAnswerInputCorrect(conjugationIndex, answer);
        userAnswer.updateConjugationStatus(answerCorrectness);

        return answerCorrectness;
    }

    public void onFieldFocusChange() {
        //@TODO
    }

    public void onActivityCreate() {
        //@TODO
        obtainNextVerb();
        userAnswer = new UserAnswer(sharedPreferencesHandler.getSpinnerIndex(), verb);
    };

    public void onClickNext() {
        //@TODO ADD SCORES HERE
        obtainNextVerb();
        userAnswer = new UserAnswer(sharedPreferencesHandler.getSpinnerIndex(), verb);
    }

    public void onClickSkip() {
        //@TODO ADD SCORES HERE
        obtainNextVerb();
        userAnswer = new UserAnswer(sharedPreferencesHandler.getSpinnerIndex(), verb);
    }

    public void onSpinnerSelection() {
        //@TODO ADD SCORES HERE

        userAnswer = new UserAnswer(sharedPreferencesHandler.getSpinnerIndex(), verb);
    }

    public void onMenuSelection() {
        //@TODO ADD SCORES HERE

    }

    public void onScreenTap() {
        //@TODO
    }
}
