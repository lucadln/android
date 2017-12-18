package com.ardeleanlucian.dutchconjugationtrainer.model;

/**
 * Created by ardelean on 11/6/17.
 */

public class UserAnswer {

    private Verb verb;
    private int spinnerPosition;
    private int numberOfConjugatedPositions = 0;
    private boolean verbConjugationCorrectness = true;

    public UserAnswer(int spinnerPosition, Verb verb) {
        this.verb = verb;
        this.spinnerPosition = spinnerPosition;
    }

    /**
     * Checks whether a certain conjugation was done correctly or not
     *
     * @param conjugationIndex
     * @param answer
     * @return
     */
    public boolean isAnswerInputCorrect(int conjugationIndex, String answer) {
        boolean answerCorrectness;

        if (answer.equalsIgnoreCase(verb.getVerbConjugation()[spinnerPosition][conjugationIndex])) {
            answerCorrectness = true;
        } else {
            answerCorrectness = false;
        }

        return answerCorrectness;
    }

    /**
     * Method to update the conjugation status (correct/incorrect)
     *   for a verb in a given tense
     *
     * @param answerCorrectness
     */
    public void updateConjugationStatus(boolean answerCorrectness) {
        numberOfConjugatedPositions++;
        // If the verb is correctly conjugated so far
        //   then check the current answer. If not then
        //   it doesn't matter what the current answer is since
        //   the verb conjugation is already wrong overall.
        if (verbConjugationCorrectness == true) {
            verbConjugationCorrectness = answerCorrectness;
        }
    }

    /**
     * @return the number of conjugated positions (ik, jij, ...)
     *         for a certain verb in a certain tense
     */
    public int getNumberOfConjugatedPositions() {
        return numberOfConjugatedPositions;
    }

    /**
     * @return a boolean value which represents the correctness
     *         of the conjugation so far for a certain verb in a
     *         certain tense
     */
    public boolean isVerbCorrectlyConjugated() {
        return verbConjugationCorrectness;
    }
}
