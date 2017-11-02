package com.ardeleanlucian.dutchconjugationtrainer.view;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private Verb currentVerb;

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

    private MainController controller;

    private Spinner spinner;

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
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        displayConjugationSection(currentVerb, controller.obtainSpinnerIndex());
    }

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
            displayConjugationSection(currentVerb, newSpinnerIndex);
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
            conjugationIndex = 0;

            // Obtain and display the next verb
            currentVerb = controller.obtainNextVerb();
            displayConjugationSection(currentVerb, controller.obtainSpinnerIndex());
        }
    };

    /**
     * Actions to be taken when the user clicks on the 'Next' button
     */
    private final View.OnClickListener onClickNext = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            conjugationIndex = 0;

            // Obtain and display the next verb
            currentVerb = controller.obtainNextVerb();
            displayConjugationSection(currentVerb, controller.obtainSpinnerIndex());
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
             *   every person (ik, jij, hij...) on screen tap */
            if (readOnlyMode = controller.obtainReadOnlyPreference()) {
                if (conjugationIndex == 0) {
                    IK_VERB_TEXT.setVisibility(View.VISIBLE);
                    JIJ.setVisibility(View.VISIBLE);
                } else if (conjugationIndex == 1) {
                    JIJ_VERB_TEXT.setVisibility(View.VISIBLE);
                    HIJ.setVisibility(View.VISIBLE);
                } else if (conjugationIndex == 2) {
                    HIJ_VERB_TEXT.setVisibility(View.VISIBLE);
                    WIJ.setVisibility(View.VISIBLE);
                } else if (conjugationIndex == 3) {
                    WIJ_VERB_TEXT.setVisibility(View.VISIBLE);
                    JULLIE.setVisibility(View.VISIBLE);
                } else if (conjugationIndex == 4) {
                    JULLIE_VERB_TEXT.setVisibility(View.VISIBLE);
                    ZIJ.setVisibility(View.VISIBLE);
                } else if (conjugationIndex == 5) {
                    ZIJ_VERB_TEXT.setVisibility(View.VISIBLE);
                    SKIP.setVisibility(GONE);
                    NEXT.setVisibility(View.VISIBLE);
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
     * Method to display the conjugation section. This is
     *   conditioned by the application mode: 'read-only'
     *   or 'write'.
     *
     * @param verb
     */
    private void displayConjugationSection(Verb verb, int spinnerIndex) {
        //@TODO
        // if read-only...
        // else if not read-only...

        // read only case...
        // Hide conjugations for the newly displayed word
        //@TODO find logic to work with both read-only and write-mode

        IK_VERB_TEXT.setVisibility(GONE);
        JIJ_VERB_TEXT.setVisibility(GONE);
        HIJ_VERB_TEXT.setVisibility(GONE);
        WIJ_VERB_TEXT.setVisibility(GONE);
        JULLIE_VERB_TEXT.setVisibility(GONE);
        ZIJ_VERB_TEXT.setVisibility(GONE);
        JIJ.setVisibility(View.INVISIBLE);
        HIJ.setVisibility(View.INVISIBLE);
        WIJ.setVisibility(View.INVISIBLE);
        JULLIE.setVisibility(View.INVISIBLE);
        ZIJ.setVisibility(View.INVISIBLE);

        INFINITIVE.setText(verb.getVerbInfinitive());
        TRANSLATION.setText(verb.getVerbTranslation());
        IK_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][0]);
        JIJ_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][1]);
        HIJ_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][2]);
        WIJ_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][3]);
        JULLIE_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][4]);
        ZIJ_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][5]);
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
    }
}