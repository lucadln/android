package com.ardeleanlucian.dutchconjugationtrainer.controller;

import android.app.Activity;
import android.content.Context;

import com.ardeleanlucian.dutchconjugationtrainer.R;
import com.ardeleanlucian.dutchconjugationtrainer.model.Feedback;
import com.ardeleanlucian.dutchconjugationtrainer.model.FileReader;
import com.ardeleanlucian.dutchconjugationtrainer.model.ScoreHandler;
import com.ardeleanlucian.dutchconjugationtrainer.model.SharedPreferencesHandler;
import com.ardeleanlucian.dutchconjugationtrainer.model.UserAnswer;
import com.ardeleanlucian.dutchconjugationtrainer.model.Verb;

/**
 * Created by ardelean on 10/15/17.
 */

public class ConjugationCenterController {

    private Context context;
    private SharedPreferencesHandler sharedPreferencesHandler;
    private UserAnswer userAnswer;
    private Verb verb;
    private String answerCorrectness;
    private ScoreHandler scoreHandler;


    /**
     * Constructor method
     * @param context
     */
    public ConjugationCenterController(Context context) {
        this.context = context;
        sharedPreferencesHandler = new SharedPreferencesHandler(context);
        scoreHandler = new ScoreHandler(sharedPreferencesHandler);
        userAnswer = new UserAnswer(sharedPreferencesHandler.getSpinnerIndex(), verb);
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
    public boolean isApplicationInLearningMode() { return sharedPreferencesHandler.isInLearningMode(); }


    /**
     * @return show translation preference
     */
    public boolean obtainShowTranslationPreference() { return sharedPreferencesHandler.isTranslationDisplayed(); }


    /**
     * @return the spinner index
     */
    public int obtainSpinnerIndex() {
        return sharedPreferencesHandler.getSpinnerIndex();
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


    /**
     * Actions to take on activity creation
     */
    public void onActivityCreate() {
        obtainNextVerb();
    }


    /**
     * Actions to take when clicking on the 'Next' button
     */
    public void onClickNext() {
        if (isApplicationInLearningMode() == false) {
            Feedback.incrementConjugationsCountSinceLastFeedback();
            if (userAnswer.getConjugationCorrectnessForVerb().equals("correct") ||
                    userAnswer.getConjugationCorrectnessForVerb().equals("almost")) {
                // Increment the correct and total conjugation count
                scoreHandler.incrementCorrectConjugationsCount();
                scoreHandler.incrementTotalConjugationsCount();
                // Register stats for feedback
                Feedback.incrementCorrectConsecutiveConjugationsCount();
                Feedback.resetWrongConsecutiveConjugationsCount();
            } else {
                // Increment the total conjugation count
                scoreHandler.incrementTotalConjugationsCount();
                // Register stats for feedback
                Feedback.incrementWrongConsecutiveConjugationsCount();
                Feedback.resetCorrectConsecutiveConjugationsCount();
            }
            giveFeedbackIfNecessary();
        }

        // Bring on the next verb and prepare a new userAnswer object
        obtainNextVerb();
        userAnswer = new UserAnswer(sharedPreferencesHandler.getSpinnerIndex(), verb);
    }


    /**
     * Actions to take when clicking on the 'Skip' button
     */
    public void onClickSkip() {
        if (isApplicationInLearningMode() == false) {
            Feedback.incrementConjugationsCountSinceLastFeedback();
            if (userAnswer.getConjugationCorrectnessForVerb().equals("incorrect")) {
                // Increment the total conjugation count
                scoreHandler.incrementTotalConjugationsCount();
                // Register stats for feedback
                Feedback.incrementWrongConsecutiveConjugationsCount();
                Feedback.resetCorrectConsecutiveConjugationsCount();
                giveFeedbackIfNecessary();
            }
        }

        // Bring on the next verb and prepare a new userAnswer object
        obtainNextVerb();
        userAnswer = new UserAnswer(sharedPreferencesHandler.getSpinnerIndex(), verb);
    }


    /**
     * Actions to take when selecting a new tense from the upper dropdown
     * @param spinnerIndex
     */
    public void onSpinnerSelection(int spinnerIndex) {
        if (isApplicationInLearningMode() == false) {
            if (userAnswer.getConjugationCorrectnessForVerb().equals("correct") ||
                    userAnswer.getConjugationCorrectnessForVerb().equals("almost")) {
                if (userAnswer.getNumberOfConjugatedPersons() == 6) {
                    // Increment the correct and total conjugation count
                    scoreHandler.incrementCorrectConjugationsCount();
                    scoreHandler.incrementTotalConjugationsCount();
                    // Register stats for feedback
                    Feedback.incrementCorrectConsecutiveConjugationsCount();
                    Feedback.resetWrongConsecutiveConjugationsCount();
                    giveFeedbackIfNecessary();
                }
            } else {
                scoreHandler.incrementTotalConjugationsCount();
                // Register stats for feedback
                Feedback.incrementWrongConsecutiveConjugationsCount();
                Feedback.resetCorrectConsecutiveConjugationsCount();
                giveFeedbackIfNecessary();
            }
        }

        userAnswer = new UserAnswer(spinnerIndex, verb);
        updateSpinnerPosition(spinnerIndex);
    }


    /**
     * Actions to take when selecting an item from the upper menu
     */
    public void onMenuSelection() {
        if (isApplicationInLearningMode() == false) {
            if (userAnswer.getConjugationCorrectnessForVerb().equals("correct") ||
                    userAnswer.getConjugationCorrectnessForVerb().equals("almost")) {
                if (userAnswer.getNumberOfConjugatedPersons() == 6) {
                    // Increment the correct and total conjugation count
                    scoreHandler.incrementCorrectConjugationsCount();
                    scoreHandler.incrementTotalConjugationsCount();
                    // Register stats for feedback
                    Feedback.incrementCorrectConsecutiveConjugationsCount();
                    Feedback.resetWrongConsecutiveConjugationsCount();
                    giveFeedbackIfNecessary();
                }
            } else {
                scoreHandler.incrementTotalConjugationsCount();
                // Register stats for feedback
                Feedback.incrementWrongConsecutiveConjugationsCount();
                Feedback.resetCorrectConsecutiveConjugationsCount();
                giveFeedbackIfNecessary();
            }
        }
    }

    public void onScreenTap() {
    }


    /**
     * Returns whether the newly inputted field is correctly conjugated
     */
    public String isAnswerCorrect() {
        return answerCorrectness;
    }


    /**
     * Method to give feedback if certain conditions are met in the Feedback class
     */
    public void giveFeedbackIfNecessary() {
        (new Feedback(context)).giveFeedbackIfNecessary(
                sharedPreferencesHandler.isFeedbackGiven(),
                ((Activity) context).findViewById(R.id.main_content),
                sharedPreferencesHandler.getSpinnerIndex());
    }
}
