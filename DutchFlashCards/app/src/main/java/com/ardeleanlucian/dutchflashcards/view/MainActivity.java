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

public class MainActivity extends AppCompatActivity {

    private TextView word;
    private TextView wordTranslation;
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
        String TEMP_DIFF = "Temp"; // Stub - to use a get_difficulty() method @TODO
        toolbar.setTitle("Difficulty: " + TEMP_DIFF);
        setSupportActionBar(toolbar);

        word = (TextView) findViewById(R.id.word);
        wordTranslation = (TextView) findViewById(R.id.word_translation);
        previous = (Button) findViewById(R.id.previous);
        next = (Button) findViewById(R.id.next);

        previous.setOnClickListener(onClickPrevious);
        next.setOnClickListener(onClickNext);

        // Get shared preferences
        // Get words
        // Display words
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private final View.OnClickListener onClickPrevious = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // @TODO
        }
    };

    private final View.OnClickListener onClickNext = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // @TODO
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.swap_languages:
                return true;
            case R.id.difficulty_easy:
                return true;
            case R.id.difficulty_medium:
                return true;
            case R.id.difficulty_hard:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
