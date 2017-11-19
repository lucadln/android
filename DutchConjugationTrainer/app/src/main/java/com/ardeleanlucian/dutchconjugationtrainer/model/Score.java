package com.ardeleanlucian.dutchconjugationtrainer.model;

/**
 * Created by ardelean on 10/15/17.
 */

public class Score {

    /**
     * The tenses in which the verbs can be conjugated
     */
    public static final String tenses[] = {
            "Present",
            "Present Continuous",
            "Simple Past",
            "Past Perfect",
            "Condtional",
            "Conditional Perfect",
            "Future" };

    /**
     * The number of correct conjugated verbs for each tense in particular
     */
    protected int[] correctConjugatedVerbs = new int[tenses.length];

    /**
     * The total number of conjugated verbs for each tense in particular
     */
    protected int[] totalConjugatedVerbs = new int[tenses.length];

    /**
     * The user rating for each tense in particular
     */
    protected float[] rating = new float[tenses.length];
}
