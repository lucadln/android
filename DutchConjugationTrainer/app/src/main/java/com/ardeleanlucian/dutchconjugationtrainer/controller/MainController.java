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

    SharedPreferencesHandler sharedPreferencesHandler;

    /**
     * Constructor method
     * @param context
     */
    public MainController(Context context) {
        this.context = context;
        sharedPreferencesHandler = new SharedPreferencesHandler(context);
    }

    /**
     * Method to read and return the next verb from the verbs file
     *
     * @return a Verb object
     */
    public Verb obtainNextVerb() {
        FileReader fileReader = new FileReader();
        return fileReader.readNextVerb(context, sharedPreferencesHandler);
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
     * @param verb
     * @return
     */
    public boolean checkIfAnswerCorrect(int conjugationIndex, String answer, Verb verb) {
        UserAnswer userAnswer = new UserAnswer();

        return userAnswer.isAnswerCorrect(obtainSpinnerIndex(), conjugationIndex, answer, verb);
    }
}
