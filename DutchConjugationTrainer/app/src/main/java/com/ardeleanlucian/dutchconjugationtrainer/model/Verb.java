package com.ardeleanlucian.dutchconjugationtrainer.model;

/**
 * Class to hold any information regarding a certain verb
 *   i.e. translation, conjugation etc.
 *
 * Created by ardelean on 10/25/17.
 */

public class Verb {

    private String verbInfinitive;
    private String verbTranslation;
    private String verbConjugation[][];

    /**
     * Constructor method
     *
     * @param verbInfinitive
     * @param verbTranslation
     * @param verbConjugation
     */
    public Verb(String verbInfinitive, String verbTranslation, String[][] verbConjugation) {
        setVerbInfinitive(verbInfinitive);
        setVerbTranslation(verbTranslation);
        setVerbConjugation(verbConjugation);
    }

    /**
     * Set the infinitive verb
     *
     * @param verbInfinitive
     */
    public void setVerbInfinitive(String verbInfinitive) {
        this.verbInfinitive = verbInfinitive;
    }

    /**
     * Set the translation for the verb
     *
     * @param verbConjugation
     */
    public void setVerbConjugation(String[][] verbConjugation) {
        this.verbConjugation = new String[7][6];
        this.verbConjugation = verbConjugation.clone();
    }

    /**
     * Set the verb conjugation for all tenses
     *
     * @param verbTranslation
     */
    public void setVerbTranslation(String verbTranslation) {
        this.verbTranslation = verbTranslation;
    }

    public String getVerbInfinitive() {
        return verbInfinitive;
    }

    public String getVerbTranslation() {
        return verbTranslation;
    }

    public String[][] getVerbConjugation() {
        return verbConjugation;
    }
}
