package com.ardeleanlucian.dutchconjugationtrainer.model;

/**
 * Created by ardelean on 11/6/17.
 */

public class UserAnswer {

    private Verb verb;
    private int spinnerPosition;
    private int numberOfConjugatedPersons = 0;
    private String verbConjugationCorrectness = "correct";

    public UserAnswer(int spinnerPosition, Verb verb) {
        this.verb = verb;
        this.spinnerPosition = spinnerPosition;
    }

    /**
     * Checks whether an answer (a conjugation of a certain person in a certain tense) is correct
     *
     * @param conjugationIndex
     * @param userAnswer
     * @return
     */
    public String isAnswerInputCorrect(int conjugationIndex, String userAnswer) {
        if (userAnswer.equals("")) {
            // No input. Move on.
            return "correct";
        } else {
            numberOfConjugatedPersons++;
            userAnswer = userAnswer.toLowerCase();
            String correctAnswer = verb.getVerbConjugation()[spinnerPosition][conjugationIndex];
            int differentCharsCount = 0;
            int stringLength = 0;

            if ((stringLength = userAnswer.length()) != correctAnswer.length()) {
                // The strings are not equal
                return "incorrect";
            } else {
                for (int i = 0; i < stringLength; i++) {
                    if (userAnswer.charAt(i) != correctAnswer.charAt(i)) {
                        if (((correctAnswer.charAt(i) == 'ï') && (userAnswer.charAt(i) == 'i'))
                                || (((correctAnswer.charAt(i)) == 'ë') && (userAnswer.charAt(i) == 'e'))) {
                            // Answer still correct. Move on
                        } else {
                            differentCharsCount++;
                        }
                    }
                }
                if (differentCharsCount == 0) {
                    return "correct";
                } else if (differentCharsCount == 1) {
                    return "almost";
                }
                else {
                    return "incorrect";
                }
            }
        }
    }

    /**
     * Method to update the conjugation status (correct/incorrect)
     *   for a verb in a given tense
     *
     * @param answerCorrectness
     */
    public void updateConjugationStatus(String answerCorrectness) {
        // If the verb is correctly conjugated so far
        //   the conjugation can change depending on the last input
        if (verbConjugationCorrectness.equals("correct")) {
            verbConjugationCorrectness = answerCorrectness;
        // If the verb is conjugated almost correctly until now...
        } else if (verbConjugationCorrectness.equals("almost")) {
            if (answerCorrectness.equals("incorrect")) {
                // If the latest input is wrong the whole conjugation is wrong
                verbConjugationCorrectness = answerCorrectness;
            }
        } else {
            // If the verb is already wrongly conjugated
            //   a correct conjugation won't do any difference
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
    public String getConjugationCorrectnessForVerb() {
        return verbConjugationCorrectness;
    }
}
