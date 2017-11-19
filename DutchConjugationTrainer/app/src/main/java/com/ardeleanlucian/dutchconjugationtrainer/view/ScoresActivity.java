package com.ardeleanlucian.dutchconjugationtrainer.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ardeleanlucian.dutchconjugationtrainer.R;
import com.ardeleanlucian.dutchconjugationtrainer.controller.ScoresController;

/**
 * Created by ardelean on 10/19/17.
 */

public class ScoresActivity extends AppCompatActivity {

    Button RESET_SCORES;

    /**
     * Actions to take on activity creation
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scores);

        // Add an action bar and set navigation on it
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Scores");

        // Set and display the scores chart
        //@TODO doesn't 'this' work the same as argument
        //@TODO instead of findviewById.... ?
        ScoresChart scoresChart = new ScoresChart(findViewById(R.id.scores_layout));
        scoresChart.disposeHorizontalBarChart();

        RESET_SCORES = (Button) findViewById(R.id.reset_scores);
        RESET_SCORES.setOnClickListener(onClickReset);
    }

    /**
     * Actions to take when clicking the 'Reset scores' button
     */
    private final View.OnClickListener onClickReset = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Get context and reset scores
            Context context = view.getContext().getApplicationContext();
            (new ScoresController(context)).resetScores();

            // Also reset the number of correct or wrong consecutive answers
            //        Feedback.resetCorrectConsecutiveAnswers(); @TODO when plugin in the feedback part
            //        Feedback.resetWrongConsecutiveAnswers(); @TODO when plugin in the feedback part

            // Give user a confirmation message
            Snackbar snackBar = Snackbar.make(view, "All scores have been reset!",
                    Snackbar.LENGTH_SHORT);
            snackBar.show();

            // Set and display the updated scores chart
            ScoresChart scoresChart = new ScoresChart(findViewById(R.id.scores_layout));
            scoresChart.disposeHorizontalBarChart();
        }
    };
}
