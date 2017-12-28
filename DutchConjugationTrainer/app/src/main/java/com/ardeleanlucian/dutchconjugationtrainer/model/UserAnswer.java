package com.ardeleanlucian.dutchconjugationtrainer.model;

/**
 * Created by ardelean on 11/6/17.
 */

public class UserAnswer {

    private Verb verb;
    private int spinnerPosition;
    private int numberOfConjugatedPersons = 0;
    private boolean verbConjugationCorrectness = true;

    public UserAnswer(int spinnerPosition, Verb verb) {
        this.verb = verb;
        this.spinnerPosition = spinnerPosition;
    }

    /**
     * Checks whether an answer (a conjugation of a certain person in a certain tense) is correct
     *
     * @param conjugationIndex
     * @param answer
     * @return
     */
    public boolean isAnswerInputCorrect(int conjugationIndex, String answer) {
        if (!answer.equals("")) {
            numberOfConjugatedPersons++;
            if ((answer.equalsIgnoreCase(verb.getVerbConjugation()[spinnerPosition][conjugationIndex]))
                    || (answer.equalsIgnoreCase(
                        verb.getVerbConjugation()[spinnerPosition][conjugationIndex]
                                .replace("ï", "i")))) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * Method to update the conjugation status (correct/incorrect)
     *   for a verb in a given tense
     *
     * @param answerCorrectness
     */
    public void updateConjugationStatus(boolean answerCorrectness) {
        // If the verb is correctly conjugated so far
        //   then check the current answer. If not then
        //   it doesn't matter what the current answer is since
        //   the verb conjugation is already wrong overall.
        if (verbConjugationCorrectness == true) {
            verbConjugationCorrectness = answerCorrectness;
        }
    }

    /**
     * @return the number of conjugated persons (ik, jij, ...)
     *         for a certain verb in a certain tense
     */
    public int getNumberOfConjugatedPersons() {
        return numberOfConjugatedPersons;
    }

    /**
     * @return a boolean value which represents the correctness
     *         of the conjugation of a verb in a certain tense
     */
    public boolean isVerbCorrectlyConjugated() {
        return verbConjugationCorrectness;
    }
}
