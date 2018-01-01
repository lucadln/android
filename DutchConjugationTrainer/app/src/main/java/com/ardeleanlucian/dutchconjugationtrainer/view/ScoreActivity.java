package com.ardeleanlucian.dutchconjugationtrainer.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ardeleanlucian.dutchconjugationtrainer.R;
import com.ardeleanlucian.dutchconjugationtrainer.controller.ScoreController;

/**
 * Created by ardelean on 10/19/17.
 */

public class ScoreActivity extends AppCompatActivity {

    ScoreController scoreController;
    Button RESET_SCORES;

    Context context;

    /**
     * Actions to take on activity creation
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_score);
        context = this;

        // Add an action bar and set navigation on it
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Scores");

        // Set and display the scores chart
        ScoreChart scoreChart = new ScoreChart(findViewById(R.id.score_layout));
        scoreController = new ScoreController(this);
        scoreChart.disposeHorizontalBarChart(
                scoreController.getScores(),
                scoreController.getCorrectConjugationsCount(),
                scoreController.getTotalConjugationsCount());

        RESET_SCORES = (Button) findViewById(R.id.reset_scores);
        RESET_SCORES.setOnClickListener(onClickReset);
    }

    /**
     * Actions to take when clicking the 'Reset scores' button
     */
    private final View.OnClickListener onClickReset = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new AlertDialog.Builder(context)
                    .setTitle("Reset scores")
                    .setMessage("Are you sure?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            resetConfirmed();
                        }})
                    .setNegativeButton(android.R.string.no, null).show();
        }
    };

    /**
     * Actions to take if the user has confirmed the reset
     */
    private void resetConfirmed() {
        // Pass the onClick event to the activity controller
        scoreController.onClickReset();

        // Give user a confirmation message
        Snackbar snackBar = Snackbar.make(findViewById(R.id.score_layout), "All scores have been reset!",
                Snackbar.LENGTH_SHORT);
        snackBar.show();

        // Set and display the updated scores chart
        ScoreChart scoreChart = new ScoreChart(findViewById(R.id.score_layout));
        scoreChart.disposeHorizontalBarChart(scoreController.getScores(),
                scoreController.getCorrectConjugationsCount(),
                scoreController.getTotalConjugationsCount());
    }
}
