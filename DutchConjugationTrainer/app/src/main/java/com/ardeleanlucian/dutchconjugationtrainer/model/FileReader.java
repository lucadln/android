package com.ardeleanlucian.dutchconjugationtrainer.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ardelean on 10/15/17.
 */

public class FileReader {

    private String infinitive = "unknown";
    private String translation = "unknown";
    private String[][] conjugation;

    /**
     * Constructor method
     */
    public FileReader() {
        conjugation = new String[7][6]; //@TODO make it so that it is not hardcoded
    }

    /**
     * Method to read the next verb in the file
     *
     * @return a Verb object containing its infinitive, translation and conjugation
     */
    public Verb readNextVerb(Context context) {

        boolean found = false;

        String tempReader;
        String currentVerb = "denken"; //@TODO make it so that this is taken from the SharedPreferences

        try {
            // Open file
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("verbs.txt")));
            // Start reading
            while (((tempReader = bufferedReader.readLine()) != null) && !found) {
                if (tempReader.equals(currentVerb)) {
                    found = true;

                    // Skip lines to get the next verb. Those
                    //   lines represent the conjugation of the
                    //   current verb.
                    for (int i = 0; i < 43; i++) {
                        bufferedReader.readLine();
                    }
                    if ((currentVerb = bufferedReader.readLine()) == null) {
                        // We got to the end of the file. We'll
                        //   start reading from the beginning once more
                        bufferedReader.close();
                        bufferedReader = new BufferedReader(
                                new InputStreamReader(context.getAssets().open("verbs.txt")));
                        currentVerb = bufferedReader.readLine();
                    }
                    //@TODO Save the new currentVerb in the android's SharedPreferences
                    infinitive = currentVerb;
                    translation = bufferedReader.readLine();
                    for (int i = 0; i < 7; i++) {
                        for (int j = 0; j < 6; j++) {
                            conjugation[i][j] = bufferedReader.readLine();
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Verb(infinitive, translation, conjugation);
    }
}
