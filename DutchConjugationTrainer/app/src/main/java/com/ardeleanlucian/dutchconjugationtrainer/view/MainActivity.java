package com.ardeleanlucian.dutchconjugationtrainer.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ardeleanlucian.dutchconjugationtrainer.R;
import com.ardeleanlucian.dutchconjugationtrainer.controller.MainController;
import com.ardeleanlucian.dutchconjugationtrainer.model.Verb;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private Verb currentVerb;
    private MainController controller;
    private boolean readOnlyMode;
    private MainActivityHandler mainActivityHandler;

    /**
     * displayConjIndex is used in read-only mode to
     *   know what conjugation to show at a certain
     *   point (conjugation for ik at index 0,
     *   then for jij at index 1 and so on) */
    private int conjugationIndex = 0;

    /**
     * Actions to be taken on activity creation
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityHandler = new MainActivityHandler(this);
        mainActivityHandler.initializeLayoutElements();
        controller = new MainController(this);

        setSupportActionBar(mainActivityHandler.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        currentVerb = controller.obtainNextVerb();
        mainActivityHandler.setTextViewValues(
                currentVerb, controller.obtainSpinnerIndex(), controller.obtainReadOnlyPreference());

        setListenersForLayoutElements();
    }

    /**
     * Actions to be taken when one of the fields looses its focus
     *
     * @param v
     * @param hasFocus
     */
    View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            int conjugationIndex;
            String answer;

            switch(v.getId()){
                case R.id.ik_verb_field:
                    conjugationIndex = 0;
                    break;
                case R.id.jij_verb_field:
                    conjugationIndex = 1;
                    break;
                case R.id.hij_verb_field:
                    conjugationIndex = 2;
                    break;
                case R.id.wij_verb_field:
                    conjugationIndex = 3;
                    break;
                case R.id.jullie_verb_field:
                    conjugationIndex = 4;
                    break;
                case R.id.zij_verb_field:
                    conjugationIndex = 5;
                    break;
                default:
                    conjugationIndex = 999;
                    break;
            }

            answer = mainActivityHandler.editTextList[conjugationIndex].getText().toString();
            // Only continue processing stuff if the input field was filled in
            if (!answer.equals( "" )) {
                // Hide the field and show a read-only text instead
                mainActivityHandler.editTextList[conjugationIndex].setVisibility(GONE);
                mainActivityHandler.textViewList[conjugationIndex].setVisibility(VISIBLE);
                mainActivityHandler.textViewList[conjugationIndex].setText(answer);

                if (controller.checkIfAnswerCorrect(conjugationIndex, answer, currentVerb)) {
                    mainActivityHandler.textViewList[conjugationIndex].setTextColor(Color.rgb( 0, 153, 0 ));
                } else {
                    mainActivityHandler.textViewList[conjugationIndex].setTextColor(Color.RED);
                }

                if (mainActivityHandler.getNumberOfFilledEditTexts() == 6) {
                    mainActivityHandler.SKIP.setVisibility(View.GONE);
                    mainActivityHandler.NEXT.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    /**
     * Actions to be taken when the user selects a new tense
     *   through the spinner menu
     */
    public final Spinner.OnItemSelectedListener onSpinnerSelection
            = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int newSpinnerIndex = mainActivityHandler.spinner.getSelectedItemPosition();

            // Save the new position in android's SharedPreferences
            controller.updateSpinnerPosition(newSpinnerIndex);

            // Display the conjugations corresponding to the current tense selection
            conjugationIndex = 0;
            mainActivityHandler.resetConjugationSectionVisibility(
                    controller.obtainReadOnlyPreference(), controller.obtainShowTranslationPreference());
            mainActivityHandler.clearFields();
            mainActivityHandler.setTextViewValues(
                    currentVerb, newSpinnerIndex, controller.obtainReadOnlyPreference());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    /**
     * Actions to be taken when the user clicks on the 'Skip' button
     */
    private final View.OnClickListener onClickSkip = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mainActivityHandler.clearFields();
            conjugationIndex = 0;

            // Obtain and display the next verb
            currentVerb = controller.obtainNextVerb();
            mainActivityHandler.setTextViewValues(
                    currentVerb, controller.obtainSpinnerIndex(), controller.obtainReadOnlyPreference());

            mainActivityHandler.resetConjugationSectionVisibility(
                    controller.obtainReadOnlyPreference(), controller.obtainShowTranslationPreference());
        }
    };

    /**
     * Actions to be taken when the user clicks on the 'Next' button
     */
    private final View.OnClickListener onClickNext = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mainActivityHandler.clearFields();
            conjugationIndex = 0;

            // Obtain and display the next verb
            currentVerb = controller.obtainNextVerb();
            mainActivityHandler.setTextViewValues(
                    currentVerb, controller.obtainSpinnerIndex(), controller.obtainReadOnlyPreference());

            mainActivityHandler.resetConjugationSectionVisibility(
                    controller.obtainReadOnlyPreference(), controller.obtainShowTranslationPreference());

        }
    };

    /**
     * Actions to be taken when the user taps the screen.
     *   Only important when in read-only mode. Doesn't
     *   play any role otherwise.
     */
    private final View.OnClickListener onTapScreen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /* If the user opted for the read-only option
             *   then show the conjugation one by one for
             *   every person (ik, jij, hij...) on   screen tap */
            if (readOnlyMode = controller.obtainReadOnlyPreference()) {
                if (conjugationIndex == 0) {
                    mainActivityHandler.IK_VERB_TEXT.setVisibility(VISIBLE);
                    mainActivityHandler.JIJ.setVisibility(VISIBLE);
                } else if (conjugationIndex == 1) {
                    mainActivityHandler.JIJ_VERB_TEXT.setVisibility(VISIBLE);
                    mainActivityHandler.HIJ.setVisibility(VISIBLE);
                } else if (conjugationIndex == 2) {
                    mainActivityHandler.HIJ_VERB_TEXT.setVisibility(VISIBLE);
                    mainActivityHandler.WIJ.setVisibility(VISIBLE);
                } else if (conjugationIndex == 3) {
                    mainActivityHandler.WIJ_VERB_TEXT.setVisibility(VISIBLE);
                    mainActivityHandler.JULLIE.setVisibility(VISIBLE);
                } else if (conjugationIndex == 4) {
                    mainActivityHandler.JULLIE_VERB_TEXT.setVisibility(VISIBLE);
                    mainActivityHandler.ZIJ.setVisibility(VISIBLE);
                } else if (conjugationIndex == 5) {
                    mainActivityHandler.ZIJ_VERB_TEXT.setVisibility(VISIBLE);
                    mainActivityHandler.SKIP.setVisibility(GONE);
                    mainActivityHandler.NEXT.setVisibility(VISIBLE);
                }
                conjugationIndex++;
            }
        }
    };

    /**
     * Actions to be taken on menu selection
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.settings:
                Intent displaySettings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(displaySettings);
                return true;
            case R.id.scores:
                Intent displayScores = new Intent(MainActivity.this, ScoresActivity.class);
                startActivity(displayScores);
                return true;
            case R.id.tense_info:
                Intent displayTensesInfo = new Intent(MainActivity.this, TensesInfoActivity.class);
                startActivity(displayTensesInfo);
                return true;
            case R.id.about:
                Intent displayAbout = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(displayAbout);
                 return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Inflate menu in the toolbar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Set listeners for layout elements
     */
    private void setListenersForLayoutElements() {
        mainActivityHandler.spinner.setOnItemSelectedListener(onSpinnerSelection);
        mainActivityHandler.SKIP.setOnClickListener(onClickSkip);
        mainActivityHandler.NEXT.setOnClickListener(onClickNext);
        mainActivityHandler.TABLE_LAYOUT.setOnClickListener(onTapScreen);

        mainActivityHandler.IK_VERB_FIELD.setOnFocusChangeListener(onFocusChangeListener);
        mainActivityHandler.JIJ_VERB_FIELD.setOnFocusChangeListener(onFocusChangeListener);
        mainActivityHandler.HIJ_VERB_FIELD.setOnFocusChangeListener(onFocusChangeListener);
        mainActivityHandler.WIJ_VERB_FIELD.setOnFocusChangeListener(onFocusChangeListener);
        mainActivityHandler.JULLIE_VERB_FIELD.setOnFocusChangeListener(onFocusChangeListener);
        mainActivityHandler.ZIJ_VERB_FIELD.setOnFocusChangeListener(onFocusChangeListener);
    }
}