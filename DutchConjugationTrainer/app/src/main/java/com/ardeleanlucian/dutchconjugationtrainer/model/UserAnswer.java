package com.ardeleanlucian.dutchconjugationtrainer.model;

/**
 * Created by ardelean on 11/6/17.
 */

public class UserAnswer {

    /**
     * Checks whether a certain conjugation was done correctly or not
     *
     * @param spinnerPosition
     * @param conjugationIndex
     * @param answer
     * @param verb
     * @return
     */
    public boolean isAnswerCorrect(int spinnerPosition, int conjugationIndex, String answer, Verb verb) {
        boolean answerCorrect;

        if (answer.equalsIgnoreCase(verb.getVerbConjugation()[spinnerPosition][conjugationIndex])) {
            answerCorrect = true;
        } else {
            answerCorrect = false;
        }

        return answerCorrect;
    }
}
