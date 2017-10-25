package com.ardeleanlucian.dutchconjugationtrainer.model;

/**
 * Class to hold any information regarding a certain verb
 *   i.e. translation, conjugation etc.
 *
 * Created by ardelean on 10/25/17.
 */

public class Verb {

    private String verbInfinitive;
    private String verbInfinitiveTranslation;
    private String verbConjugation[][];

    /**
     * Constructor method
     *
     * @param verbInfinitive
     * @param verbInfinitiveTranslation
     * @param verbConjugation
     */
    public Verb(String verbInfinitive, String verbInfinitiveTranslation, String[][] verbConjugation) {
        setVerbInfinitive(verbInfinitive);
        setVerbInfinitiveTranslation(verbInfinitiveTranslation);
        setVerbConjugation(verbConjugation);
    }

    public void setVerbConjugation(String[][] verbConjugation) {
        this.verbConjugation = verbConjugation; // @TODO - May break because not initialized?
    }

    public void setVerbInfinitiveTranslation(String verbInfinitiveTranslation) {
        this.verbInfinitiveTranslation = verbInfinitiveTranslation;
    }

    public void setVerbInfinitive(String verbInfinitive) {
        this.verbInfinitive = verbInfinitive;
    }

    public String getVerbInfinitive() {
        return verbInfinitive;
    }

    public String getVerbInfinitiveTranslation() {
        return verbInfinitiveTranslation;
    }

    public String[][] getVerbConjugation() {
        return verbConjugation;
    }
}
