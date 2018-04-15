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
            String correctAnswer[];
            String correctAnswerRow = verb.getVerbConjugation()[spinnerPosition][conjugationIndex];
            String status = "";
            int differentCharsCount = 0;
            int stringLength = 0;
            int conjugationAlternatives = 1;

            if (correctAnswerRow.contains("/")) {
                conjugationAlternatives = 2;
                correctAnswer = correctAnswerRow.split("/");
            } else {
                conjugationAlternatives = 1;
                correctAnswer = new String[1];
                correctAnswer[0] = correctAnswerRow;
            }

            for (int i = 0; i < conjugationAlternatives; i++) {
                differentCharsCount = 0;
                if ((stringLength = correctAnswer[i].length()) != userAnswer.length()) {
                    status = "incorrect"; // The string length differs
                } else {
                    // Compare character by character
                    for (int j = 0; j < stringLength; j++) {
                        if (userAnswer.charAt(j) != correctAnswer[i].charAt(j)) {
                            if (((correctAnswer[i].charAt(j) == 'ï') && (userAnswer.charAt(j) == 'i'))
                                    || (((correctAnswer[i].charAt(j)) == 'ë') && (userAnswer.charAt(j) == 'e'))) {
                                // The answer is correct
                            } else {
                                differentCharsCount++; // The characters from current index are different
                            }
                        }
                    }
                    if (differentCharsCount == 0) {
                        status = "correct";
                        break;
                    } else if (differentCharsCount == 1) {
                        status = "almost";
                        break;
                    } else {
                        status = "incorrect";
                    }
                }
            }
            return status;
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
