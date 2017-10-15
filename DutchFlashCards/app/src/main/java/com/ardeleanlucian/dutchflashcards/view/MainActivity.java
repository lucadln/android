package com.ardeleanlucian.dutchflashcards.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ardeleanlucian.dutchflashcards.R;
import com.ardeleanlucian.dutchflashcards.controller.MainController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView word;
    private TextView translation;
    private Button previous;
    private Button next;
    private Toolbar toolbar;

    private MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mainController = new MainController(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Difficulty: " + mainController.getDifficulty());
        setSupportActionBar(toolbar);

        word = (TextView) findViewById(R.id.word);
        translation = (TextView) findViewById(R.id.translation);
        previous = (Button) findViewById(R.id.previous);
        next = (Button) findViewById(R.id.next);

        previous.setOnClickListener(this);
        next.setOnClickListener(this);
        findViewById(R.id.constraint_layout).setOnClickListener(this);

        mainController.readWordPair("nextPair");
        displayWords(mainController.getDutchWord(),
                mainController.getEnglishWord(), mainController.getPrimaryLanguage());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.previous:
                mainController.readWordPair("previousPair");
                displayWords(mainController.getDutchWord(),
                        mainController.getEnglishWord(), mainController.getPrimaryLanguage());
                translation.setVisibility(View.INVISIBLE);
                break;
            case R.id.next:
                mainController.readWordPair("nextPair");
                displayWords(mainController.getDutchWord(),
                        mainController.getEnglishWord(), mainController.getPrimaryLanguage());
                translation.setVisibility(View.INVISIBLE);
                break;
            case R.id.constraint_layout:
                // If the translation is not visible, display it on
                //   screen tap.
                if (translation.getVisibility() != View.VISIBLE) {
                    translation.setVisibility(View.VISIBLE);
                }
                // If translation is already displayed and the screen
                //   is tapped then show the next word pair.
                else {
                    translation.setVisibility(View.INVISIBLE);
                    mainController.readWordPair("nextPair");
                    displayWords(mainController.getDutchWord(),
                            mainController.getEnglishWord(), mainController.getPrimaryLanguage());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.swap_languages:
                mainController.updatePrimaryLanguage();
                break;
            case R.id.difficulty_easy:
                mainController.updateDifficulty("easy");
                toolbar.setTitle("Difficulty: " + mainController.getDifficulty());
                break;
            case R.id.difficulty_moderate:
                mainController.updateDifficulty("moderate");
                toolbar.setTitle("Difficulty: " + mainController.getDifficulty());
                break;
            case R.id.difficulty_hard:
                mainController.updateDifficulty("hard");
                toolbar.setTitle("Difficulty: " + mainController.getDifficulty());
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        mainController.readWordPair("nextPair");
        displayWords(mainController.getDutchWord(),
                mainController.getEnglishWord(), mainController.getPrimaryLanguage());

        return true;
    }

    private void displayWords(String dutchWord, String englishWord, String primaryLanguage) {

        // If the primary language is dutch then display
        //   the dutch word first and the english word
        //   second (as translation).
        if (primaryLanguage.equals("dutch")) {
            word.setText(dutchWord);
            translation.setText(englishWord);
        } else {
            word.setText(englishWord);
            translation.setText(dutchWord);
        }
    }
}
