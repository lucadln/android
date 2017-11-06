package com.ardeleanlucian.dutchconjugationtrainer.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.ardeleanlucian.dutchconjugationtrainer.R;
import com.ardeleanlucian.dutchconjugationtrainer.controller.MainController;
import com.ardeleanlucian.dutchconjugationtrainer.model.Score;
import com.ardeleanlucian.dutchconjugationtrainer.model.SpinnerAdapter;
import com.ardeleanlucian.dutchconjugationtrainer.model.Verb;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private TableLayout TABLE_LAYOUT;

    private Button NEXT;
    private Button SKIP;

    private TextView INFINITIVE;
    private TextView TRANSLATION;
    private TextView IK;
    private TextView JIJ;
    private TextView HIJ;
    private TextView WIJ;
    private TextView JULLIE;
    private TextView ZIJ;
    private TextView IK_VERB_TEXT;
    private TextView JIJ_VERB_TEXT;
    private TextView HIJ_VERB_TEXT;
    private TextView WIJ_VERB_TEXT;
    private TextView JULLIE_VERB_TEXT;
    private TextView ZIJ_VERB_TEXT;

    private EditText IK_VERB_FIELD;
    private EditText JIJ_VERB_FIELD;
    private EditText HIJ_VERB_FIELD;
    private EditText WIJ_VERB_FIELD;
    private EditText JULLIE_VERB_FIELD;
    private EditText ZIJ_VERB_FIELD;

    private Spinner spinner;

    private Verb currentVerb;
    private MainController controller;
    private boolean readOnlyMode;

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
        initializeLayoutElements();

        controller = new MainController(this);

        // Set up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Set up the spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new SpinnerAdapter(toolbar.getContext(), Score.tenses));
        spinner.setSelection(controller.obtainSpinnerIndex());
        spinner.setOnItemSelectedListener(onSpinnerSelection);

        SKIP.setOnClickListener(onClickSkip);
        NEXT.setOnClickListener(onClickNext);
        TABLE_LAYOUT.setOnClickListener(onTapScreen);

        currentVerb = controller.obtainNextVerb();
        setTextViewValues(currentVerb, controller.obtainSpinnerIndex());
        resetConjugationSectionVisibility(
                readOnlyMode = controller.obtainReadOnlyPreference(),
                controller.obtainShowTranslationPreference());
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
            TextView textViewList[] = { IK_VERB_TEXT,
                    JIJ_VERB_TEXT,
                    HIJ_VERB_TEXT,
                    WIJ_VERB_TEXT,
                    JULLIE_VERB_TEXT,
                    ZIJ_VERB_TEXT };
            EditText editTextList[] = { IK_VERB_FIELD,
                    JIJ_VERB_FIELD,
                    HIJ_VERB_FIELD,
                    WIJ_VERB_FIELD,
                    JULLIE_VERB_FIELD,
                    ZIJ_VERB_FIELD };
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

            answer = editTextList[conjugationIndex].getText().toString();
            // Only continue processing stuff if the input field was filled in
            if (!answer.equals( "" )) {
                // Hide the field and show a read-only text instead
                editTextList[conjugationIndex].setVisibility(GONE);
                textViewList[conjugationIndex].setVisibility(VISIBLE);
                textViewList[conjugationIndex].setText(answer);

                if (controller.checkIfAnswerCorrect(conjugationIndex, answer, currentVerb)) {
                    textViewList[conjugationIndex].setTextColor(Color.rgb( 0, 153, 0 ));
                } else {
                    textViewList[conjugationIndex].setTextColor(Color.RED);
                }

                if (getNumberOfFilledEditTexts() == 6) {
                    SKIP.setVisibility(View.GONE);
                    NEXT.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    /**
     * Actions to be taken when the user selects a new tense
     *   through the spinner menu
     */
    private final Spinner.OnItemSelectedListener onSpinnerSelection
            = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int newSpinnerIndex = spinner.getSelectedItemPosition();

            // Save the new position in android's SharedPreferences
            controller.updateSpinnerPosition(newSpinnerIndex);

            // Display the conjugations corresponding to the current tense selection
            conjugationIndex = 0;
            resetConjugationSectionVisibility(readOnlyMode, controller.obtainShowTranslationPreference());
            clearFields();
            setTextViewValues(currentVerb, newSpinnerIndex);
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
            clearFields();
            conjugationIndex = 0;

            // Obtain and display the next verb
            currentVerb = controller.obtainNextVerb();
            setTextViewValues(currentVerb, controller.obtainSpinnerIndex());

            resetConjugationSectionVisibility(
                    readOnlyMode, controller.obtainShowTranslationPreference());
        }
    };

    /**
     * Actions to be taken when the user clicks on the 'Next' button
     */
    private final View.OnClickListener onClickNext = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clearFields();
            conjugationIndex = 0;

            // Obtain and display the next verb
            currentVerb = controller.obtainNextVerb();
            setTextViewValues(currentVerb, controller.obtainSpinnerIndex());

            resetConjugationSectionVisibility(
                    readOnlyMode, controller.obtainShowTranslationPreference());

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
                    IK_VERB_TEXT.setVisibility(VISIBLE);
                    JIJ.setVisibility(VISIBLE);
                } else if (conjugationIndex == 1) {
                    JIJ_VERB_TEXT.setVisibility(VISIBLE);
                    HIJ.setVisibility(VISIBLE);
                } else if (conjugationIndex == 2) {
                    HIJ_VERB_TEXT.setVisibility(VISIBLE);
                    WIJ.setVisibility(VISIBLE);
                } else if (conjugationIndex == 3) {
                    WIJ_VERB_TEXT.setVisibility(VISIBLE);
                    JULLIE.setVisibility(VISIBLE);
                } else if (conjugationIndex == 4) {
                    JULLIE_VERB_TEXT.setVisibility(VISIBLE);
                    ZIJ.setVisibility(VISIBLE);
                } else if (conjugationIndex == 5) {
                    ZIJ_VERB_TEXT.setVisibility(VISIBLE);
                    SKIP.setVisibility(GONE);
                    NEXT.setVisibility(VISIBLE);
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
     * Initialize layout elements
     */
    private void initializeLayoutElements() {
        TABLE_LAYOUT = (TableLayout) findViewById(R.id.table_layout);

        NEXT = (Button) findViewById(R.id.next);
        SKIP = (Button) findViewById(R.id.skip);

        INFINITIVE = (TextView) findViewById(R.id.infinitive);
        TRANSLATION = (TextView) findViewById(R.id.translation);
        IK = (TextView) findViewById(R.id.ik);
        JIJ = (TextView) findViewById(R.id.jij);
        HIJ = (TextView) findViewById(R.id.hij);
        WIJ = (TextView) findViewById(R.id.wij);
        JULLIE = (TextView) findViewById(R.id.jullie);
        ZIJ = (TextView) findViewById(R.id.zij);
        IK_VERB_TEXT = (TextView) findViewById(R.id.ik_verb_text);
        JIJ_VERB_TEXT = (TextView) findViewById(R.id.jij_verb_text);
        HIJ_VERB_TEXT = (TextView) findViewById(R.id.hij_verb_text);
        WIJ_VERB_TEXT = (TextView) findViewById(R.id.wij_verb_text);
        JULLIE_VERB_TEXT = (TextView) findViewById(R.id.jullie_verb_text);
        ZIJ_VERB_TEXT = (TextView) findViewById(R.id.zij_verb_text);

        IK_VERB_FIELD = (EditText) findViewById(R.id.ik_verb_field);
        JIJ_VERB_FIELD = (EditText) findViewById(R.id.jij_verb_field);
        HIJ_VERB_FIELD = (EditText) findViewById(R.id.hij_verb_field);
        WIJ_VERB_FIELD = (EditText) findViewById(R.id.wij_verb_field);
        JULLIE_VERB_FIELD = (EditText) findViewById(R.id.jullie_verb_field);
        ZIJ_VERB_FIELD = (EditText) findViewById(R.id.zij_verb_field);

        IK_VERB_FIELD.setOnFocusChangeListener(onFocusChangeListener);
        JIJ_VERB_FIELD.setOnFocusChangeListener(onFocusChangeListener);
        HIJ_VERB_FIELD.setOnFocusChangeListener(onFocusChangeListener);
        WIJ_VERB_FIELD.setOnFocusChangeListener(onFocusChangeListener);
        JULLIE_VERB_FIELD.setOnFocusChangeListener(onFocusChangeListener);
        ZIJ_VERB_FIELD.setOnFocusChangeListener(onFocusChangeListener);
    }

    /**
     * Method to clear all values from the layout fields
     */
    private void clearFields() {
        IK_VERB_FIELD.setText( "" );
        JIJ_VERB_FIELD.setText( "" );
        HIJ_VERB_FIELD.setText( "" );
        WIJ_VERB_FIELD.setText( "" );
        JULLIE_VERB_FIELD.setText( "" );
        ZIJ_VERB_FIELD.setText( "" );
    }

    public int getNumberOfFilledEditTexts() {
        int count = 0;
        if (IK_VERB_FIELD.getVisibility() == View.GONE) {
            count++;
        }
        if (JIJ_VERB_FIELD.getVisibility() == View.GONE) {
            count++;
        }
        if (HIJ_VERB_FIELD.getVisibility() == View.GONE) {
            count++;
        }
        if (WIJ_VERB_FIELD.getVisibility() == View.GONE) {
            count++;
        }
        if (JULLIE_VERB_FIELD.getVisibility() == View.GONE) {
            count++;
        }
        if (ZIJ_VERB_FIELD.getVisibility() == View.GONE) {
            count++;
        }
        return count;
    }

    /**
     * Method to set the values for the TextViews in the layout.
     *   Only important in the read-only mode.
     *
     * @param verb
     * @param spinnerIndex
     */
    private void setTextViewValues(Verb verb, int spinnerIndex) {
        INFINITIVE.setText(verb.getVerbInfinitive());
        TRANSLATION.setText(verb.getVerbTranslation());

        if (controller.obtainReadOnlyPreference()) {

            IK_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][0]);
            JIJ_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][1]);
            HIJ_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][2]);
            WIJ_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][3]);
            JULLIE_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][4]);
            ZIJ_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][5]);
        }
    }

    /**
     * Method to reset the conjugation visibility depending
     *   on the play mode (read-only or write).
     *
     * @param readOnly
     */
    private void resetConjugationSectionVisibility(boolean readOnly, boolean showTranslation) {
        SKIP.setVisibility(VISIBLE);
        NEXT.setVisibility(GONE);
        if (showTranslation) {
            TRANSLATION.setVisibility(VISIBLE);
        } else {
            TRANSLATION.setVisibility(INVISIBLE);
        }
        if (readOnly) {
            IK.setVisibility(VISIBLE);
            JIJ.setVisibility(INVISIBLE);
            HIJ.setVisibility(INVISIBLE);
            WIJ.setVisibility(INVISIBLE);
            JULLIE.setVisibility(INVISIBLE);
            ZIJ.setVisibility(INVISIBLE);

            IK_VERB_TEXT.setVisibility(INVISIBLE);
            JIJ_VERB_TEXT.setVisibility(INVISIBLE);
            HIJ_VERB_TEXT.setVisibility(INVISIBLE);
            WIJ_VERB_TEXT.setVisibility(INVISIBLE);
            JULLIE_VERB_TEXT.setVisibility(INVISIBLE);
            ZIJ_VERB_TEXT.setVisibility(INVISIBLE);

            IK_VERB_FIELD.setVisibility(GONE);
            JIJ_VERB_FIELD.setVisibility(GONE);
            HIJ_VERB_FIELD.setVisibility(GONE);
            WIJ_VERB_FIELD.setVisibility(GONE);
            JULLIE_VERB_FIELD.setVisibility(GONE);
            ZIJ_VERB_FIELD.setVisibility(GONE);
        } else {
            IK.setVisibility(VISIBLE);
            JIJ.setVisibility(VISIBLE);
            HIJ.setVisibility(VISIBLE);
            WIJ.setVisibility(VISIBLE);
            JULLIE.setVisibility(VISIBLE);
            ZIJ.setVisibility(VISIBLE);

            IK_VERB_TEXT.setVisibility(GONE);
            JIJ_VERB_TEXT.setVisibility(GONE);
            HIJ_VERB_TEXT.setVisibility(GONE);
            WIJ_VERB_TEXT.setVisibility(GONE);
            JULLIE_VERB_TEXT.setVisibility(GONE);
            ZIJ_VERB_TEXT.setVisibility(GONE);

            IK_VERB_FIELD.setVisibility(VISIBLE);
            JIJ_VERB_FIELD.setVisibility(VISIBLE);
            HIJ_VERB_FIELD.setVisibility(VISIBLE);
            WIJ_VERB_FIELD.setVisibility(VISIBLE);
            JULLIE_VERB_FIELD.setVisibility(VISIBLE);
            ZIJ_VERB_FIELD.setVisibility(VISIBLE);
        }
    }
}