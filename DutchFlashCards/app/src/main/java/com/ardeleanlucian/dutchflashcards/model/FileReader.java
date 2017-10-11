package com.ardeleanlucian.dutchflashcards.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ardelean on 10/8/17.
 */

public class FileReader {

    private String dutchWord = "unknown";
    private String englishWord = "unknown";
    private String previousDutchWord = "unknown";
    private String previousEnglishWord = "unknown";

    private Context context;

    /**
     * Constructor method
     */
    public FileReader(Context context) {

        this.context = context;
    }

    /**
     * Method to read next/previous dutch-english words pair
     *
     * @param triggerAction
     */
    public void readFile(String triggerAction, String latestDutchWord, String difficulty) {

        String tempReader;
        boolean found = false;

        try {
            // Open the file containing the words
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(difficulty + "_words.txt")));

            // Read file line by line until you find the latest dutch word.
            while (((tempReader = bufferedReader.readLine()) != null) && !found) {

                // If we got to the line of the latest used dutch word we
                //   can now look for the previous/next dutch-english words
                //   pair.
                if (tempReader.equals(latestDutchWord)) {
                    bufferedReader.readLine();
                    found = true;

                    // Get the next words pair
                    if (triggerAction.equals("getNextPair")) {
                        // If we got to the end of the file then close it and
                        //   read it again from the beginning.
                        if ((dutchWord = bufferedReader.readLine()) == null) {
                            bufferedReader.close();
                            bufferedReader = new BufferedReader(
                                    new InputStreamReader(context.getAssets()
                                            .open(difficulty + "_words.txt")));
                            dutchWord = bufferedReader.readLine();
                        }
                        englishWord = bufferedReader.readLine();
                    }

                    // Get the previous words pair
                    else if (triggerAction.equals("getPreviousPair")) {
                        // If we are at the beginning of the file there are no
                        //   'previous' lines. Hence we will jump to the end
                        //   of the file instead.
                        if (previousDutchWord.equals("unknown")) {
                            while ((tempReader = bufferedReader.readLine()) != null) {
                                dutchWord = tempReader;
                                englishWord = bufferedReader.readLine();
                            }
                        } else {
                            dutchWord = previousDutchWord;
                            englishWord = previousEnglishWord;
                        }
                    }
                }

                // We didn't find the latest used dutch word. Save the
                //   current dutch-english words pair and then continue
                //   reading the file.
                else {
                    previousDutchWord = tempReader;
                    previousEnglishWord = bufferedReader.readLine();
                }
            }
            // If we looped through the file and didn't find the latest
            //   dutch word, then just set dutchWord as the first word
            //   in the file
            if (!found) {
                bufferedReader.close();
                bufferedReader = new BufferedReader(
                        new InputStreamReader(context.getAssets()
                                .open(difficulty + "_words.txt")));
                dutchWord = bufferedReader.readLine();
                englishWord = bufferedReader.readLine();
            }

            // Lastly, save the latest word in android's Shared Preferences
            SharedPreferencesHandler prefs = new SharedPreferencesHandler(context);
            prefs.writeLatestWord(dutchWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getWordsPair() {
        return new String[] {dutchWord, englishWord};
    }
}
