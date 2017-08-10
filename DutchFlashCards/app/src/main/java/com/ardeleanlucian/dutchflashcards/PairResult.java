package com.ardeleanlucian.dutchflashcards;

/**
 * Created by ardelean on 7/9/17.
 */

public class PairResult {

    private String englishWord = "unknown";
    private String dutchWord = "unknown";

    public PairResult(String enWord, String nlWord)
    {
        englishWord = enWord;
        dutchWord = nlWord;
    }

    public String getPrimaryWord(String primLanguage) {
        if (primLanguage.equals("dutch")) {
            return dutchWord;
        } else {
            return englishWord;
        }
    }

    public String getTranslation(String primLanguage) {
        if (primLanguage.equals("dutch")) {
            return englishWord;
        } else {
            return dutchWord;
        }
    }
}
