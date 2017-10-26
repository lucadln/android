package com.ardeleanlucian.dutchconjugationtrainer.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

    private MainController controller;

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
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new SpinnerAdapter(toolbar.getContext(), Score.tenses));
        spinner.setSelection(controller.getSpinnerPosition());

        SKIP.setOnClickListener(onClickSkip);
        NEXT.setOnClickListener(onClickNext);
        TABLE_LAYOUT.setOnClickListener(onTapScreen);

        displayConjugationSection(controller.obtainNextVerb());
    }

    /**
     * Actions to be taken when the user clicks on the 'Skip' button
     */
    private final View.OnClickListener onClickSkip = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //@TODO
        }
    };

    /**
     * Actions to be taken when the user clicks on the 'Next' button
     */
    private final View.OnClickListener onClickNext = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //@TODO
        }
    };

    /**
     * Actions to be taken when the user taps the screen
     */
    private final View.OnClickListener onTapScreen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          //@TODO
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
     * @param nextVerb
     */
    private void displayConjugationSection(Verb nextVerb) {
        //@TODO
        // if read-only ...
        // else if not read-only ...

        // temp. just display the first verb conjugated in the present tense

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