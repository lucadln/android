package com.ardeleanlucian.dutchflashcards;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ardelean on 7/9/17.
 */

public class FileHandler {

    Context context;

    private String previousDutchWord;
    private String previousEnglishWord;
    private String dutchWord;
    private String englishWord;
    private String tempReader = "temp";
    private String initDutchWord;
    private String initEnglishWord;
    private static String lastDisplayedWord;
    private Boolean found = false;

    FileHandler(Context context) {
        this.context = context;
    }

    public PairResult readFile(Context context, String triggerAction, String fileToRead) {

        // Define a variable for reading Shared Preferences.
        SharedPreferences prefs = context.getSharedPreferences(
                "com.ardeleanlucian.dutchflashcards", Context.MODE_PRIVATE);


        /** Reading words from different files
         *   depending on the difficulty level
         *   the user chose.*/


        // When difficulty level is set to A1:
        if (fileToRead.equals("a1_list.txt")) {

            String indexKeyA1 = "com.ardeleanlucian.dutchflashcards.index_a1";
            lastDisplayedWord = prefs.getString(indexKeyA1, "a1_nl1");
            initDutchWord = "a1_nl1";
            initEnglishWord = "a1_en1";
            previousDutchWord = "a1_nl3";
            previousEnglishWord = "a1_en3";

            try {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(context.getAssets().open(fileToRead)));
                while (((tempReader = bufferedReader.readLine()) != null) && !found) {
                    if (tempReader.equals(lastDisplayedWord)) {
                        if (triggerAction == "next" || triggerAction == "tap") {
                            bufferedReader.readLine();
                            if ( (dutchWord = bufferedReader.readLine() ) == null ) {
                                dutchWord = initDutchWord;
                                englishWord = initEnglishWord;
                            } else {
                                englishWord = bufferedReader.readLine();
                            }
                        } else if (triggerAction == "previous") {
                            dutchWord = previousDutchWord;
                            englishWord = previousEnglishWord;
                        }

                        // Store the latest dutch word in the phone memory
                        lastDisplayedWord = dutchWord;
                        prefs.edit().putString(indexKeyA1, lastDisplayedWord).apply();
                        found = true;
                    } else {
                        // Keep the last word couple in memory.
                        previousDutchWord = tempReader;
                        previousEnglishWord = bufferedReader.readLine();
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }


        // When difficulty level is set to A2:
        } else if (fileToRead.equals("a2_list.txt")) {

            String indexKeyA2 = "com.ardeleanlucian.dutchflashcards.index_a2";
            lastDisplayedWord = prefs.getString(indexKeyA2, "a2_nl1");
            initDutchWord = "a2_nl1";
            initEnglishWord = "a2_en1";
            previousDutchWord = "a2_nl3";
            previousEnglishWord = "a2_en3";

            try {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(context.getAssets().open(fileToRead)));
                while (((tempReader = bufferedReader.readLine()) != null) && !found) {
                    if (tempReader.equals(lastDisplayedWord)) {
                        if (triggerAction == "next" || triggerAction == "tap") {
                            bufferedReader.readLine();
                            if ( (dutchWord = bufferedReader.readLine() ) == null ) {
                                dutchWord = initDutchWord;
                                englishWord = initEnglishWord;
                            } else {
                                englishWord = bufferedReader.readLine();
                            }
                        } else if (triggerAction == "previous") {
                            dutchWord = previousDutchWord;
                            englishWord = previousEnglishWord;
                        }

                        // Store the latest dutch word in the phone memory
                        lastDisplayedWord = dutchWord;
                        prefs.edit().putString(indexKeyA2, lastDisplayedWord).apply();
                        found = true;
                    } else {
                        // Keep the last word couple in memory.
                        previousDutchWord = tempReader;
                        previousEnglishWord = bufferedReader.readLine();
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }


        // When difficulty level is set to B1:
        } else {

            String indexKeyB1 = "com.ardeleanlucian.dutchflashcards.index_b1";
            lastDisplayedWord = prefs.getString(indexKeyB1, "b1_nl1");
            initDutchWord = "b1_nl1";
            initEnglishWord = "b1_en1";
            previousDutchWord = "b1_nl3";
            previousEnglishWord = "b1_en3";

            try {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(context.getAssets().open(fileToRead)));
                while (((tempReader = bufferedReader.readLine()) != null) && !found) {
                    if (tempReader.equals(lastDisplayedWord)) {
                        if (triggerAction == "next" || triggerAction == "tap") {
                            bufferedReader.readLine();
                            if ( (dutchWord = bufferedReader.readLine() ) == null ) {
                                dutchWord = initDutchWord;
                                englishWord = initEnglishWord;
                            } else {
                                englishWord = bufferedReader.readLine();
                            }
                        } else if (triggerAction == "previous") {
                            dutchWord = previousDutchWord;
                            englishWord = previousEnglishWord;
                        }

                        // Store the latest dutch word in the phone memory
                        lastDisplayedWord = dutchWord;
                        prefs.edit().putString(indexKeyB1, lastDisplayedWord).apply();
                        found = true;
                    } else {
                        // Keep the last word couple in memory.
                        previousDutchWord = tempReader;
                        previousEnglishWord = bufferedReader.readLine();
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new PairResult(englishWord, dutchWord);
    }
}