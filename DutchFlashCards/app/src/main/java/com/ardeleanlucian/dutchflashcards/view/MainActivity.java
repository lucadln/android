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

        displayWords(mainController.getWordsPair("getNextPair"), mainController.getPrimaryLanguage());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.previous:
                displayWords(mainController.getWordsPair("getPreviousPair"), mainController.getPrimaryLanguage());
                break;
            case R.id.next:
                displayWords(mainController.getWordsPair("getNextPair"), mainController.getPrimaryLanguage());
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

        displayWords(mainController.getWordsPair("getNextPair"), mainController
                .getPrimaryLanguage());

        return true;
    }

    private void displayWords(String[] wordsPair, String primaryLanguage) {

        // If the primary language is dutch then display
        //   the dutch word first and the english word
        //   second (as translation).
        if (primaryLanguage.equals("dutch")) {
            word.setText(wordsPair[0]);
            translation.setText(wordsPair[1]);
        } else {
            word.setText(wordsPair[1]);
            translation.setText(wordsPair[0]);
        }
    }
}
