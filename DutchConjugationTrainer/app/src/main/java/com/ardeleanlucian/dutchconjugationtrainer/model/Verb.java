package com.ardeleanlucian.dutchconjugationtrainer.model;

/**
 * Class to hold any information regarding a certain verb
 *   i.e. translation, conjugation etc.
 *
 * Created by ardelean on 10/25/17.
 */

public class Verb {

    /**
     * The tenses in which the verbs can be conjugated
     */
    public static final String tenses[] = {
            "Present",
            "Present Perfect",
            "Simple Past",
            "Past Perfect",
            "Conditional",
            "Conditional Perfect",
            "Future"
    };

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
        this.verbConjugation = new String[tenses.length][6];
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
