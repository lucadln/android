package com.ardeleanlucian.dutchconjugationtrainer.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.ardeleanlucian.dutchconjugationtrainer.R;

/**
 * Created by ardelean on 10/19/17.
 */

public class FeedbackActivity extends AppCompatActivity {
    private TextView FEEDBACK_MESSAGE;
    private Button BACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide action bar
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_feedback);
        FEEDBACK_MESSAGE = (TextView) findViewById(R.id.feedback_message);
        BACK = (Button) findViewById(R.id.back);

        String feedbackMotivation = getIntent().getExtras().getString("feedbackMotivation");

        if (feedbackMotivation.equals("numberOfConjugatedVerbs")) {
            String numberOfConjugatedVerbs = String.valueOf(getIntent().getExtras()
                    .getInt("numberOfConjugatedVerbs"));
            String tense = getIntent().getExtras().getString("tense");
            FEEDBACK_MESSAGE.setText("You have conjugated " + numberOfConjugatedVerbs
                    + " verbs for the tense " + tense + "!");
        } else if (feedbackMotivation.equals("ratingEquals100")) {
            String tense = getIntent().getExtras().getString("tense");
            FEEDBACK_MESSAGE.setText("Your score for the tense " + tense + " is now 100!");
        } else if (feedbackMotivation.equals("ratingOver90")) {
            String tense = getIntent().getExtras().getString("tense");
            FEEDBACK_MESSAGE.setText("Your score for the tense " + tense + " is over 90!");
        } else if (feedbackMotivation.equals("ratingUnder40")) {
            String tense = getIntent().getExtras().getString("tense");
            FEEDBACK_MESSAGE.setText("Your score for the tense " + tense + " is under 40!");
        } else if (feedbackMotivation.equals("ratingUnder10")) {
            String tense = getIntent().getExtras().getString("tense");
            FEEDBACK_MESSAGE.setText("Your score for the tense " + tense + " is under 10!");
        }

        BACK.setOnClickListener(onClickBack);
    }

    /**
     * Actions to be taken when clicking on the 'Back' button
     */
    private final View.OnClickListener onClickBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent displayMainActivity= new Intent(
                    FeedbackActivity.this, MainActivity.class);
            startActivity(displayMainActivity);
        }
    };
}
