package com.ardeleanlucian.dutchconjugationtrainer.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import com.ardeleanlucian.dutchconjugationtrainer.R;
import com.ardeleanlucian.dutchconjugationtrainer.controller.ConjugationCenterController;
import com.ardeleanlucian.dutchconjugationtrainer.model.ScreenHandler;
import com.ardeleanlucian.dutchconjugationtrainer.model.Utils;

public class ConjugationCenter extends AppCompatActivity {

    private ConjugationCenterController conjugationCenterController;
    private ConjugationCenterHandler conjugationCenterHandler;
    /**
     * conjugationIndex is used in learning mode to
     *   know what conjugation to show at a certain
     *   point (conjugation for 'ik' at index 0,
     *   then for 'jij' at index 1 and so on...) */
    private int conjugationIndex = 0;

    /**
     * Actions to be taken on activity creation
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get phone screen size and set a layout depending on it
        double screenSize = ScreenHandler.getScreenSize();
        if (screenSize >= 4.9) {
            setContentView(R.layout.conjugation_center_phone_big);
        } else {
            setContentView(R.layout.conjugation_center_phone_small);
        }

        conjugationCenterHandler = new ConjugationCenterHandler(this);
        conjugationCenterHandler.initializeLayoutElements();

        setSupportActionBar(conjugationCenterHandler.getToolbar());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Pass the 'onActivityCreate' event to the activity controller
        conjugationCenterController = new ConjugationCenterController(this);
        conjugationCenterController.onActivityCreate();

        // Set the textView values
        conjugationCenterHandler.setTextViewValues(conjugationCenterController.getVerb(), conjugationCenterController.obtainSpinnerIndex());
        // Set listeners for elements in the current activity
        setListenersForLayoutElements();

        // @TODO
        /* If the application is started for the first time
                 *   in learning mode then instruct the user to tap
                 *   screen for conjugation */
//        if ((firstTimeReadOnly) && (readOnlyPref)) {
//            String infoTapScreen = "Tap screen for conjugation";
//            final Snackbar snackBar = Snackbar.make(view, infoTapScreen,
//                    Snackbar.LENGTH_INDEFINITE);
//
//                    /* The snackbar message remains on the
//                     *   screen until is dismissed */
//            snackBar.setAction("Dismiss", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    snackBar.dismiss();
//                    prefs.edit().putBoolean(firstReadKey, false).apply();
//                    firstTimeReadOnly = false;
//                }
//            });
//            snackBar.show();
//        }
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
            TextView textViewList[] = {
                    conjugationCenterHandler.getIkVerbText(),
                    conjugationCenterHandler.getJijVerbText(),
                    conjugationCenterHandler.getHijVerbText(),
                    conjugationCenterHandler.getWijVerbText(),
                    conjugationCenterHandler.getJullieVerbText(),
                    conjugationCenterHandler.getZijVerbText()};
            EditText editTextList[] = {
                    conjugationCenterHandler.getIkVerbField(),
                    conjugationCenterHandler.getJijVerbField(),
                    conjugationCenterHandler.getHijVerbField(),
                    conjugationCenterHandler.getWijVerbField(),
                    conjugationCenterHandler.getJullieVerbField(),
                    conjugationCenterHandler.getZijVerbField()};
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
                    conjugationIndex = -1;
                    break;
            }

            if (conjugationIndex != -1) { // If one of the text fields looses focus...
                // Get text field content
                answer = editTextList[conjugationIndex].getText().toString();
                conjugationCenterController.onFieldFocusChange(conjugationIndex, answer);
                if (!answer.equals("")) { // If the text field was filled in...
                    // Hide the field and show a read-only text instead
                    conjugationCenterHandler.setVisibility(editTextList[conjugationIndex], GONE);
                    conjugationCenterHandler.setVisibility(textViewList[conjugationIndex], VISIBLE);
                    conjugationCenterHandler.setTextViewAnswer(textViewList[conjugationIndex], answer);

                    if (conjugationCenterController.isAnswerCorrect()) {
                        conjugationCenterHandler.setTextViewColor(textViewList[conjugationIndex], "green");
                    } else {
                        conjugationCenterHandler.setTextViewColor(textViewList[conjugationIndex], "red");
                    }
                }
                if (conjugationCenterHandler.getNumberOfFilledEditTexts() == 5) {
                    conjugationCenterHandler.switchFocusThief(true);

                } else if (conjugationCenterHandler.getNumberOfFilledEditTexts() == 6) {
                    conjugationCenterHandler.setVisibility(conjugationCenterHandler.getSkip(), GONE);
                    conjugationCenterHandler.setVisibility(conjugationCenterHandler.getNext(), VISIBLE);
                    Utils.hideKeyboard(
                            conjugationCenterHandler.getContext(), editTextList[conjugationIndex]);
                    conjugationCenterHandler.switchFocusThief(false);
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
            int spinnerIndex = conjugationCenterHandler.getSpinner().getSelectedItemPosition();

            conjugationCenterController.onSpinnerSelection(spinnerIndex);

            // Display the conjugations corresponding to the current tense selection
            conjugationIndex = 0;
            conjugationCenterHandler.resetConjugationSectionVisibility(
                    conjugationCenterController.obtainReadOnlyPreference(), conjugationCenterController.obtainShowTranslationPreference());
            conjugationCenterHandler.clearFields();
            conjugationCenterHandler.setTextViewValues(conjugationCenterController.getVerb(), spinnerIndex);

            // Move focus again on the first layout element
            conjugationCenterHandler.resetFocus();
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
            conjugationCenterController.onClickSkip();

            conjugationCenterHandler.clearFields();
            conjugationIndex = 0;

            // Obtain and display the next verb
            conjugationCenterHandler.setTextViewValues(conjugationCenterController.getVerb(), conjugationCenterController.obtainSpinnerIndex());
            conjugationCenterHandler.resetConjugationSectionVisibility(
                    conjugationCenterController.obtainReadOnlyPreference(), conjugationCenterController.obtainShowTranslationPreference());

            // Move focus again on the first layout element
            conjugationCenterHandler.resetFocus();
        }
    };

    /**
     * Actions to be taken when the user clicks on the 'Next' button
     */
    private final View.OnClickListener onClickNext = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            conjugationCenterController.onClickNext();

            conjugationCenterHandler.clearFields();
            conjugationIndex = 0;

            // Obtain and display the next verb
            conjugationCenterHandler.setTextViewValues(conjugationCenterController.getVerb(), conjugationCenterController.obtainSpinnerIndex());
            conjugationCenterHandler.resetConjugationSectionVisibility(
                    conjugationCenterController.obtainReadOnlyPreference(), conjugationCenterController.obtainShowTranslationPreference());

            // Move focus again on the first layout element
            conjugationCenterHandler.resetFocus();
        }
    };

    /**
     * Actions to be taken when the user taps the screen.
     *   Only important when in learning mode. Doesn't
     *   play any role otherwise.
     */
    private final View.OnClickListener onTapScreen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /* If the user opted for the learning mode
             *   then show the conjugation one by one for
             *   every person (ik, jij, hij...) on screen tap */
            if (conjugationCenterController.obtainReadOnlyPreference()) {
                if (conjugationIndex == 0) {
                    conjugationCenterHandler.getIkVerbText().setVisibility(VISIBLE);
                    conjugationCenterHandler.getJij().setVisibility(VISIBLE);
                } else if (conjugationIndex == 1) {
                    conjugationCenterHandler.getJijVerbText().setVisibility(VISIBLE);
                    conjugationCenterHandler.getHij().setVisibility(VISIBLE);
                } else if (conjugationIndex == 2) {
                    conjugationCenterHandler.getHijVerbText().setVisibility(VISIBLE);
                    conjugationCenterHandler.getWij().setVisibility(VISIBLE);
                } else if (conjugationIndex == 3) {
                    conjugationCenterHandler.getWijVerbText().setVisibility(VISIBLE);
                    conjugationCenterHandler.getJullie().setVisibility(VISIBLE);
                } else if (conjugationIndex == 4) {
                    conjugationCenterHandler.getJullieVerbText().setVisibility(VISIBLE);
                    conjugationCenterHandler.getZij().setVisibility(VISIBLE);
                } else if (conjugationIndex == 5) {
                    conjugationCenterHandler.getZijVerbText().setVisibility(VISIBLE);
                    conjugationCenterHandler.getSkip().setVisibility(GONE);
                    conjugationCenterHandler.getNext().setVisibility(VISIBLE);
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
        conjugationCenterController.onMenuSelection();

        switch (id) {

            case R.id.settings:
                Intent displaySettings = new Intent(ConjugationCenter.this, SettingsActivity.class);
                startActivity(displaySettings);
                return true;
            case R.id.scores:
                Intent displayScores = new Intent(ConjugationCenter.this, ScoreActivity.class);
                startActivity(displayScores);
                return true;
            case R.id.tense_info:
                Intent displayTensesInfo = new Intent(ConjugationCenter.this, TensesInfoActivity.class);
                startActivity(displayTensesInfo);
                return true;
            case R.id.about:
                Intent displayAbout = new Intent(ConjugationCenter.this, AboutActivity.class);
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
        conjugationCenterHandler.getSpinner().setOnItemSelectedListener(onSpinnerSelection);
        conjugationCenterHandler.getSkip().setOnClickListener(onClickSkip);
        conjugationCenterHandler.getNext().setOnClickListener(onClickNext);
        conjugationCenterHandler.getUpperContent().setOnClickListener(onTapScreen);
        conjugationCenterHandler.getConjugationSection().setOnClickListener(onTapScreen);

        conjugationCenterHandler.getIkVerbField().setOnFocusChangeListener(onFocusChangeListener);
        conjugationCenterHandler.getJijVerbField().setOnFocusChangeListener(onFocusChangeListener);
        conjugationCenterHandler.getHijVerbField().setOnFocusChangeListener(onFocusChangeListener);
        conjugationCenterHandler.getWijVerbField().setOnFocusChangeListener(onFocusChangeListener);
        conjugationCenterHandler.getJullieVerbField().setOnFocusChangeListener(onFocusChangeListener);
        conjugationCenterHandler.getZijVerbField().setOnFocusChangeListener(onFocusChangeListener);
    }
}