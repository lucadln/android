package com.ardeleanlucian.dutchconjugationtrainer;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ardelean Lucian on 8/6/2017.
 */

public class VerbsFileReader {

    Context context;
    private String tempReader;
    private boolean found = false;
    private String infinitive = "unknown";
    private String translation = "unknown";
    private String ikVerb = "unknown";
    private String jijVerb = "unknown";
    private String hijVerb = "unknown";
    private String wijVerb = "unknown";
    private String jullieVerb = "unknown";
    private String zijVerb = "unknown";

    public VerbsFileReader(Context context) {
        this.context = context;
    }

    public TenseConjugationResult readFile(Context context, String tense, String action) {

        // Define a variable for reading Shared Preferences.
        SharedPreferences prefs = context.getSharedPreferences(
                "com.ardeleanlucian.dutchconjugationtrainer", Context.MODE_PRIVATE);
        String currentVerbKey = "com.ardeleanlucian.dutchconjugationtrainer.current_verb";
        // Get the next verb from the phone settings - 'denken' is default
        String currentVerb = prefs.getString(currentVerbKey, "denken");
        String initVerb = "denken";

        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("verbs.txt")));
            while (((tempReader = bufferedReader.readLine()) != null) && !found) {
                if (tempReader.equals(currentVerb)) {
                    if (action == "next") {
                        // Skip the conjugations and move to the next word
                        for (int i = 0; i < 44; i++) {
                            bufferedReader.readLine();
                        }
                        if ((currentVerb = bufferedReader.readLine()) == null) {
                            /** We got to the end of the verb file.
                            /* We'll reading from the beggining again. */
                            currentVerb = initVerb;
                        } else {
                            prefs.edit().putString(currentVerbKey, currentVerb).apply();
                        }
                        readFile(context, tense, "none");
                    } else {
                        infinitive = currentVerb;
                        translation = bufferedReader.readLine();
                        switch (tense) {
                            // Skip reading lines in the 'verbs.txt' file if needed.
                            case "present":
                                break;
                            case "present_continuous":
                                for (int i = 0; i < 6; i++) {
                                    bufferedReader.readLine();
                                }
                                break;
                            case "simple_past":
                                for (int i = 0; i < 12; i++) {
                                    bufferedReader.readLine();
                                }
                                break;
                            case "past_perfect":
                                for (int i = 0; i < 18; i++) {
                                    bufferedReader.readLine();
                                }
                                break;
                            case "conditional":
                                for (int i = 0; i < 24; i++) {
                                    bufferedReader.readLine();
                                }
                                break;
                            case "conditional_perfect":
                                for (int i = 0; i < 30; i++) {
                                    bufferedReader.readLine();
                                }
                                break;
                            case "future":
                                for (int i = 0; i < 36; i++) {
                                    bufferedReader.readLine();
                                }
                                break;
                        }
                        ikVerb = bufferedReader.readLine();
                        jijVerb = bufferedReader.readLine();
                        hijVerb = bufferedReader.readLine();
                        wijVerb = bufferedReader.readLine();
                        jullieVerb = bufferedReader.readLine();
                        zijVerb = bufferedReader.readLine();
                    }
                    found = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TenseConjugationResult(infinitive, translation, ikVerb, jijVerb,
                                          hijVerb, wijVerb, jullieVerb, zijVerb);
    }
}
