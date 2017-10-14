package com.ardeleanlucian.dutchflashcards.model;

/**
 * Created by ardelean on 10/14/17.
 */

public class WordPair {

    private String dutchWord;
    private String englishWord;

    public WordPair(String dutchWord, String englishWord) {
        this.dutchWord = dutchWord;
        this.englishWord = englishWord;
    }

    public String getDutchWord() {
        return dutchWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }
}
