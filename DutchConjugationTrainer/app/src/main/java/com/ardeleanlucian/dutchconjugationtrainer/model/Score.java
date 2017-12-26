package com.ardeleanlucian.dutchconjugationtrainer.model;

import static com.ardeleanlucian.dutchconjugationtrainer.model.Verb.tenses;

/**
 * Created by ardelean on 10/15/17.
 */

public class Score {

    /**
     * The number of correct conjugated verbs for each tense in particular
     */
    protected int[] correctlyConjugatedVerbs = new int[tenses.length];

    /**
     * The total number of conjugated verbs for each tense in particular
     */
    protected int[] totalConjugatedVerbs = new int[tenses.length];

    /**
     * The user rating for each tense in particular
     */
    protected float[] rating = new float[tenses.length];
}
