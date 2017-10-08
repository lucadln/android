package com.ardeleanlucian.dutchflashcards.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ardeleanlucian.dutchflashcards.R;

public class MainActivity extends AppCompatActivity {

    TextView word;
    TextView wordTranslation;
    Button previous;
    Button next;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        word = (TextView) findViewById(R.id.word);
        wordTranslation = (TextView) findViewById(R.id.word_translation);
        previous = (Button) findViewById(R.id.previous);
        next = (Button) findViewById(R.id.next);

        String TEMP_DIFF = "Temp"; // Stub - to use a get_difficulty() method @TODO
        toolbar.setTitle("Difficulty: " + TEMP_DIFF);
        setSupportActionBar(toolbar);

        previous.setOnClickListener(onClickPrevious);
        next.setOnClickListener(onClickNext);
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
}
