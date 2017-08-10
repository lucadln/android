package com.ardeleanlucian.dutchflashcards;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String primaryWord = "unknown";
    String translatedWord = "unknown";
    String primaryLanguage;
    String fileToRead;
    boolean showTranslation = false;

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define variables for reading Shared Preferences.
        SharedPreferences prefs = this.getSharedPreferences(
                "com.ardeleanlucian.dutchflashcards", Context.MODE_PRIVATE);
        String languageKey = "com.ardeleanlucian.dutchflashcards.mainlanguage";
        String fileKey = "com.ardeleanlucian.dutchflashcards.filetoread";
        // Read the user preferences
        primaryLanguage = prefs.getString(languageKey, "dutch");
        fileToRead = prefs.getString(fileKey, "a1_list.txt");

        // Define and set toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        if (fileToRead.equals("a1_list.txt")) {
            myToolbar.setTitle("Difficulty: Easy");
        } else if (fileToRead.equals("a2_list.txt")) {
            myToolbar.setTitle("Difficulty: Medium");
        } else {
            myToolbar.setTitle("Difficulty: Hard");
        }
        setSupportActionBar(myToolbar);

        FileHandler fileHandler = new FileHandler(this);
        PairResult pairResult = fileHandler.readFile(this, "tap", fileToRead);
        primaryWord = pairResult.getPrimaryWord(primaryLanguage);
        translatedWord = pairResult.getTranslation(primaryLanguage);

        TextView tvPrimaryWord = (TextView) findViewById(R.id.primary_word);
        tvPrimaryWord.setText(primaryWord);

        TextView tvTranslatedWord = (TextView) findViewById(R.id.translated_word);
        tvTranslatedWord.setText(translatedWord);
        tvTranslatedWord.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Define variables for reading Shared Preferences.
        SharedPreferences prefs = this.getSharedPreferences(
                "com.ardeleanlucian.dutchflashcards", Context.MODE_PRIVATE);
        String languageKey = "com.ardeleanlucian.dutchflashcards.mainlanguage";
        String fileKey = "com.ardeleanlucian.dutchflashcards.filetoread";

        // Define variables for text views
        TextView tvPrimaryWord = (TextView) findViewById(R.id.primary_word);
        TextView tvTranslatedWord = (TextView) findViewById(R.id.translated_word);

        // Define a variable for handling the toolbar's title
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        switch (item.getItemId()) {

            case R.id.action_swap: // The user presses the swap languages action button.
                String temp;

                if (primaryLanguage.equals("dutch")) {
                    // Switch to english.
                    primaryLanguage = "english";
                    // Swap values between primaryWord and translatedWord
                    temp = primaryWord;
                    primaryWord = translatedWord;
                    translatedWord = temp;
                    // Save option in the phone Shared Preferences.
                    prefs.edit().putString(languageKey, "english").apply();
                    // Inform user about changes
                    CharSequence confirmEnglish = "The primary language is now english.";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(this, confirmEnglish, duration);
                    toast.show();
                } else {
                    // If primaryLanguage.eguals("english") then switch to dutch
                    primaryLanguage = "dutch";
                    // Swap values between primaryWord and translatedWord
                    temp = primaryWord;
                    primaryWord = translatedWord;
                    translatedWord = temp;
                    // Save option in the phone Shared Preferences.
                    prefs.edit().putString(languageKey, "dutch").apply();
                    // Inform user about changes
                    CharSequence confirmDutch = "The primary language is now dutch.";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(this, confirmDutch, duration);
                    toast.show();
                }

                // Swap the position of the words
                tvPrimaryWord.setText(primaryWord);
                tvTranslatedWord.setText(translatedWord);

                return true;

            case R.id.diffChoiceA1: // The user selects difficulty level A1
                fileToRead = "a1_list.txt";
                // Save option in the phone Shared Preferences.
                prefs.edit().putString(fileKey, fileToRead).apply();

                /** Read a new pair of words from the
                 *   difficulty corresponding file */

                // Set the values for the next word
                FileHandler fileHandlerA1 = new FileHandler(this);
                PairResult pairResultA1 = fileHandlerA1.readFile(this, "next", fileToRead);
                primaryWord = pairResultA1.getPrimaryWord(primaryLanguage);
                translatedWord = pairResultA1.getTranslation(primaryLanguage);
                tvPrimaryWord.setText(primaryWord);
                tvTranslatedWord.setText(translatedWord);

                // Hide translation
                tvTranslatedWord.setVisibility(View.INVISIBLE);
                showTranslation = false;

                // Change toolbar title
                myToolbar.setTitle("Difficulty: Easy");
                setSupportActionBar(myToolbar);

                // Inform user about changes
                CharSequence informA1 = "The difficulty level is set to A1";
                int durationA1 = Toast.LENGTH_SHORT;

                Toast toastA1 = Toast.makeText(this, informA1, durationA1);
                toastA1.show();

                return true;

            case R.id.diffChoiceA2: // The user selects difficulty level A2
                fileToRead = "a2_list.txt";
                // Save option in the phone Shared Preferences.
                prefs.edit().putString(fileKey, fileToRead).apply();

                /** Read a new pair of words from the
                 *   difficulty corresponding file */

                // Set the values for the next word
                FileHandler fileHandlerA2 = new FileHandler(this);
                PairResult pairResultA2 = fileHandlerA2.readFile(this, "next", fileToRead);
                primaryWord = pairResultA2.getPrimaryWord(primaryLanguage);
                translatedWord = pairResultA2.getTranslation(primaryLanguage);
                tvPrimaryWord.setText(primaryWord);
                tvTranslatedWord.setText(translatedWord);

                // Hide translation
                tvTranslatedWord.setVisibility(View.INVISIBLE);
                showTranslation = false;

                // Change toolbar title
                myToolbar.setTitle("Difficulty: Medium");
                setSupportActionBar(myToolbar);

                // Inform user about changes
                CharSequence informA2 = "The difficulty level is set to A2";
                int durationA2 = Toast.LENGTH_SHORT;

                Toast toastA2 = Toast.makeText(this, informA2, durationA2);
                toastA2.show();

                return true;

            case R.id.diffChoiceB1: // The user selects difficulty level B1
                fileToRead = "b1_list.txt";
                // Save option in the phone Shared Preferences.
                prefs.edit().putString(fileKey, fileToRead).apply();

                /** Read a new pair of words from the
                 *   difficulty corresponding file */

                // Set the values for the next word
                FileHandler fileHandlerB1 = new FileHandler(this);
                PairResult pairResultB1 = fileHandlerB1.readFile(this, "next", fileToRead);
                primaryWord = pairResultB1.getPrimaryWord(primaryLanguage);
                translatedWord = pairResultB1.getTranslation(primaryLanguage);
                tvPrimaryWord.setText(primaryWord);
                tvTranslatedWord.setText(translatedWord);

                // Hide translation
                tvTranslatedWord.setVisibility(View.INVISIBLE);
                showTranslation = false;

                // Change toolbar title
                myToolbar.setTitle("Difficulty: Hard");
                setSupportActionBar(myToolbar);

                // Inform user about changes
                CharSequence informB1 = "The difficulty level is set to B1";
                int durationB1 = Toast.LENGTH_SHORT;

                Toast toastB1 = Toast.makeText(this, informB1, durationB1);
                toastB1.show();

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    public void onTapScreen(View view) {
        TextView tvPrimaryWord = (TextView) findViewById(R.id.primary_word);
        TextView tvTranslatedWord = (TextView) findViewById(R.id.translated_word);
        // Show translation
        if (showTranslation == false) {
            tvTranslatedWord.setVisibility(View.VISIBLE);
            showTranslation = true;
        } else {
            // Set the values for the next word
            FileHandler fileHandler = new FileHandler(this);
            PairResult pairResult = fileHandler.readFile(this, "tap", fileToRead);
            primaryWord = pairResult.getPrimaryWord(primaryLanguage);
            translatedWord = pairResult.getTranslation(primaryLanguage);
            tvPrimaryWord.setText(primaryWord);
            tvTranslatedWord.setText(translatedWord);

            tvTranslatedWord.setVisibility(View.INVISIBLE);
            showTranslation = false;
        }
    }

    /** Define actions to be taken when clicking on the 'Next' button */
    public void onClickNext(View view) {
        TextView tvPrimaryWord = (TextView) findViewById(R.id.primary_word);
        TextView tvTranslatedWord = (TextView) findViewById(R.id.translated_word);

        // Set the values for the next word
        FileHandler fileHandler = new FileHandler(this);
        PairResult pairResult = fileHandler.readFile(this, "next", fileToRead);
        primaryWord = pairResult.getPrimaryWord(primaryLanguage);
        translatedWord = pairResult.getTranslation(primaryLanguage);
        tvPrimaryWord.setText(primaryWord);
        tvTranslatedWord.setText(translatedWord);

        // Hide translation
        tvTranslatedWord.setVisibility(View.INVISIBLE);
        showTranslation = false;
    }

    public void onClickPrevious(View view) {
        TextView tvPrimaryWord = (TextView) findViewById(R.id.primary_word);
        TextView tvTranslatedWord = (TextView) findViewById(R.id.translated_word);

        // Set the values for the next word
        FileHandler fileHandler = new FileHandler(this);
        PairResult pairResult = fileHandler.readFile(this, "previous", fileToRead);
        primaryWord = pairResult.getPrimaryWord(primaryLanguage);
        translatedWord = pairResult.getTranslation(primaryLanguage);
        tvPrimaryWord.setText(primaryWord);
        tvTranslatedWord.setText(translatedWord);

        // Hide translation
        tvTranslatedWord.setVisibility(View.INVISIBLE);
        showTranslation = false;
    }
}