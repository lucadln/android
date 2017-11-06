package com.ardeleanlucian.dutchconjugationtrainer.model;

/**
 * Created by ardelean on 11/6/17.
 */

public class UserAnswer {

    private static boolean[] correctConjugation;

    /**
     * Constructor method
     */
    public UserAnswer() {
        correctConjugation = new boolean[6];
    }

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
        correctConjugation[conjugationIndex] = answerCorrect;

        return answerCorrect;
    }

    /**
     *
     * @return a boolean value to show whether the conjugation
     *         for a certain verb in a certain tense was done
     *         correctly or not for all the persons (ik, jij ...)
     */
    public boolean isEntireConjugationCorrect() {
        for (int i = 0; i < 5; i++) {
            if (!correctConjugation[i]) {
                return false;
            }
        }
        return true;
    }
}
