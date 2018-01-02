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
import com.ardeleanlucian.dutchconjugationtrainer.controller.MainController;
import com.ardeleanlucian.dutchconjugationtrainer.model.Utils;

public class MainActivity extends AppCompatActivity {

    private MainController mainController;
    private MainActivityHandler mainActivityHandler;
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

        // Initialize layout
        setContentView(R.layout.activity_main);
        mainActivityHandler = new MainActivityHandler(this);
        mainActivityHandler.initializeLayoutElements();

        setSupportActionBar(mainActivityHandler.getToolbar());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Pass the 'onActivityCreate' event to the activity controller
        mainController = new MainController(this);
        mainController.onActivityCreate();

        // Set the textView values
        mainActivityHandler.setTextViewValues(mainController.getVerb(), mainController.obtainSpinnerIndex());
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
                    mainActivityHandler.getIkVerbText(),
                    mainActivityHandler.getJijVerbText(),
                    mainActivityHandler.getHijVerbText(),
                    mainActivityHandler.getWijVerbText(),
                    mainActivityHandler.getJullieVerbText(),
                    mainActivityHandler.getZijVerbText()};
            EditText editTextList[] = {
                    mainActivityHandler.getIkVerbField(),
                    mainActivityHandler.getJijVerbField(),
                    mainActivityHandler.getHijVerbField(),
                    mainActivityHandler.getWijVerbField(),
                    mainActivityHandler.getJullieVerbField(),
                    mainActivityHandler.getZijVerbField()};
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
                mainController.onFieldFocusChange(conjugationIndex, answer);
                if (!answer.equals("")) { // If the text field was filled in...
                    // Hide the field and show a read-only text instead
                    mainActivityHandler.setVisibility(editTextList[conjugationIndex], GONE);
                    mainActivityHandler.setVisibility(textViewList[conjugationIndex], VISIBLE);
                    mainActivityHandler.setTextViewAnswer(textViewList[conjugationIndex], answer);

                    if (mainController.isAnswerCorrect()) {
                        mainActivityHandler.setTextViewColor(textViewList[conjugationIndex], "green");
                    } else {
                        mainActivityHandler.setTextViewColor(textViewList[conjugationIndex], "red");
                    }
                }
                if (mainActivityHandler.getNumberOfFilledEditTexts() == 5) {
                    mainActivityHandler.switchFocusThief(true);

                } else if (mainActivityHandler.getNumberOfFilledEditTexts() == 6) {
                    mainActivityHandler.setVisibility(mainActivityHandler.getSkip(), GONE);
                    mainActivityHandler.setVisibility(mainActivityHandler.getNext(), VISIBLE);
                    Utils.hideKeyboard(
                            mainActivityHandler.getContext(), editTextList[conjugationIndex]);
                    mainActivityHandler.switchFocusThief(false);
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
            int spinnerIndex = mainActivityHandler.getSpinner().getSelectedItemPosition();

            mainController.onSpinnerSelection(spinnerIndex);

            // Display the conjugations corresponding to the current tense selection
            conjugationIndex = 0;
            mainActivityHandler.resetConjugationSectionVisibility(
                    mainController.obtainReadOnlyPreference(), mainController.obtainShowTranslationPreference());
            mainActivityHandler.clearFields();
            mainActivityHandler.setTextViewValues(mainController.getVerb(), spinnerIndex);

            // Move focus again on the first layout element
            mainActivityHandler.resetFocus();
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
            mainController.onClickSkip();

            mainActivityHandler.clearFields();
            conjugationIndex = 0;

            // Obtain and display the next verb
            mainActivityHandler.setTextViewValues(mainController.getVerb(), mainController.obtainSpinnerIndex());
            mainActivityHandler.resetConjugationSectionVisibility(
                    mainController.obtainReadOnlyPreference(), mainController.obtainShowTranslationPreference());

            // Move focus again on the first layout element
            mainActivityHandler.resetFocus();
        }
    };

    /**
     * Actions to be taken when the user clicks on the 'Next' button
     */
    private final View.OnClickListener onClickNext = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mainController.onClickNext();

            mainActivityHandler.clearFields();
            conjugationIndex = 0;

            // Obtain and display the next verb
            mainActivityHandler.setTextViewValues(mainController.getVerb(), mainController.obtainSpinnerIndex());
            mainActivityHandler.resetConjugationSectionVisibility(
                    mainController.obtainReadOnlyPreference(), mainController.obtainShowTranslationPreference());

            // Move focus again on the first layout element
            mainActivityHandler.resetFocus();
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
            if (mainController.obtainReadOnlyPreference()) {
                if (conjugationIndex == 0) {
                    mainActivityHandler.getIkVerbText().setVisibility(VISIBLE);
                    mainActivityHandler.getJij().setVisibility(VISIBLE);
                } else if (conjugationIndex == 1) {
                    mainActivityHandler.getJijVerbText().setVisibility(VISIBLE);
                    mainActivityHandler.getHij().setVisibility(VISIBLE);
                } else if (conjugationIndex == 2) {
                    mainActivityHandler.getHijVerbText().setVisibility(VISIBLE);
                    mainActivityHandler.getWij().setVisibility(VISIBLE);
                } else if (conjugationIndex == 3) {
                    mainActivityHandler.getWijVerbText().setVisibility(VISIBLE);
                    mainActivityHandler.getJullie().setVisibility(VISIBLE);
                } else if (conjugationIndex == 4) {
                    mainActivityHandler.getJullieVerbText().setVisibility(VISIBLE);
                    mainActivityHandler.getZij().setVisibility(VISIBLE);
                } else if (conjugationIndex == 5) {
                    mainActivityHandler.getZijVerbText().setVisibility(VISIBLE);
                    mainActivityHandler.getSkip().setVisibility(GONE);
                    mainActivityHandler.getNext().setVisibility(VISIBLE);
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
        mainController.onMenuSelection();

        switch (id) {

            case R.id.settings:
                Intent displaySettings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(displaySettings);
                return true;
            case R.id.scores:
                Intent displayScores = new Intent(MainActivity.this, ScoreActivity.class);
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
        mainActivityHandler.getSpinner().setOnItemSelectedListener(onSpinnerSelection);
        mainActivityHandler.getSkip().setOnClickListener(onClickSkip);
        mainActivityHandler.getNext().setOnClickListener(onClickNext);
        mainActivityHandler.getTableLayout().setOnClickListener(onTapScreen);

        mainActivityHandler.getIkVerbField().setOnFocusChangeListener(onFocusChangeListener);
        mainActivityHandler.getJijVerbField().setOnFocusChangeListener(onFocusChangeListener);
        mainActivityHandler.getHijVerbField().setOnFocusChangeListener(onFocusChangeListener);
        mainActivityHandler.getWijVerbField().setOnFocusChangeListener(onFocusChangeListener);
        mainActivityHandler.getJullieVerbField().setOnFocusChangeListener(onFocusChangeListener);
        mainActivityHandler.getZijVerbField().setOnFocusChangeListener(onFocusChangeListener);
    }
}