package com.ardeleanlucian.dutchconjugationtrainer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.content.res.Resources.Theme;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private TenseConjugationResult tenseConjugationResult;
    private boolean showTranslationPref;
    private boolean readOnlyPref;
    private boolean firstTimeReadOnly;
    private int displayConjIndex = 0;
    private boolean correctVerbConjugation;
    private Scores scores;

    private String tenseOptions[] = {"Present", "Present Continuous", "Simple Past", "Past Perfect",
                                     "Condtional", "Conditional Perfect", "Future" };
    private String currentlySelectedTense;
    private String previouslySelectedTense;


    private TextView INFINITVE, TRANSLATION,
                     IK,      JIJ,      HIJ,      WIJ,      JULLIE,      ZIJ,
                     IK_VERB, JIJ_VERB, HIJ_VERB, WIJ_VERB, JULLIE_VERB, ZIJ_VERB;
    private EditText IK_VERB_FIELD,  JIJ_VERB_FIELD,    HIJ_VERB_FIELD,
                     WIJ_VERB_FIELD, JULLIE_VERB_FIELD, ZIJ_VERB_FIELD;
    private Button NEXT, SKIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        scores = new Scores(context);

        correctVerbConjugation = true;

        // Set preferences-menu default values when application is started for the first time
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        // Get preferences from phone storage
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        showTranslationPref = sharedPrefs.getBoolean(PreferencesActivity.KEY_SHOW_TRANS, true);
        readOnlyPref = sharedPrefs.getBoolean(PreferencesActivity.KEY_READ_ONLY, false);

        // Set up the action toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Set variables for textviews
        INFINITVE =  (TextView) findViewById(R.id.infinitive);
        TRANSLATION = (TextView) findViewById(R.id.translation);
        IK_VERB = (TextView) findViewById(R.id.ik_verb);
        JIJ_VERB = (TextView) findViewById(R.id.jij_verb);
        HIJ_VERB = (TextView) findViewById(R.id.hij_verb);
        WIJ_VERB = (TextView) findViewById(R.id.wij_verb);
        JULLIE_VERB = (TextView) findViewById(R.id.jullie_verb);
        ZIJ_VERB = (TextView) findViewById(R.id.zij_verb);
        IK = (TextView) findViewById(R.id.ik);
        JIJ = (TextView) findViewById(R.id.jij);
        HIJ = (TextView) findViewById(R.id.hij);
        WIJ = (TextView) findViewById(R.id.wij);
        JULLIE = (TextView) findViewById(R.id.jullie);
        ZIJ = (TextView) findViewById(R.id.zij);

        // Set variables for EditTexts
        IK_VERB_FIELD = (EditText) findViewById(R.id.ik_verb_field);
        JIJ_VERB_FIELD = (EditText) findViewById(R.id.jij_verb_field);
        HIJ_VERB_FIELD = (EditText) findViewById(R.id.hij_verb_field);
        WIJ_VERB_FIELD = (EditText) findViewById(R.id.wij_verb_field);
        JULLIE_VERB_FIELD = (EditText) findViewById(R.id.jullie_verb_field);
        ZIJ_VERB_FIELD = (EditText) findViewById(R.id.zij_verb_field);

        // Set variables for buttons
        NEXT = (Button) findViewById(R.id.next);
        SKIP = (Button) findViewById(R.id.skip);

        // Get other preferences from phone memory
        final SharedPreferences prefs = context.getSharedPreferences(
                "com.ardeleanlucian.dutchconjugationtrainer", Context.MODE_PRIVATE);
        final String selectedTenseKey
                = "com.ardeleanlucian.dutchconjugationtrainer.selected_tense";
        final String firstReadKey
                = "com.ardeleanlucian.dutchconjugationtrainer.first_read";
        // Get the last used tenseOptions
        currentlySelectedTense = prefs.getString(selectedTenseKey, "present");
        // Check if application was run before in read-only mode
        firstTimeReadOnly = prefs.getBoolean(firstReadKey, true);

        // Set up the spinner
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new MyAdapter(toolbar.getContext(), tenseOptions));

        // Find the index of the selected tense in tenseOptions array
        for (int i = 0; i < tenseOptions.length; i++) {
            if (currentlySelectedTense.equals(tenseOptions[i])) {
                // Put back in place the last tense used by the user
                spinner.setSelection(i);
            }
        }

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Save tenseOptions choice in phone memory.
                currentlySelectedTense = spinner.getSelectedItem().toString();
                prefs.edit().putString(selectedTenseKey, currentlySelectedTense).apply();

                /* If the user changes the tense after he
                 *   got one conjugation wrong then this
                 *   is registered as a wrong answer */
                if (!correctVerbConjugation) {
                    scores.updateScores(correctVerbConjugation, previouslySelectedTense);
                }

                /* Keep track of the previously selected tense.
                 *   This will proove to be usefull when registering
                 *   a wrong answer that was followed by a tense change */
                previouslySelectedTense = currentlySelectedTense;

                correctVerbConjugation = true;

                // When the given dropdown item is selected, show its contents in the
                // container view.
                VerbsFileReader verbsFileReader = new VerbsFileReader(context);

                // Read verbs.txt and set conjugations
                tenseConjugationResult = verbsFileReader.readFile(context, currentlySelectedTense, "none");
                tenseConjugationResult.setValuesTextView(IK_VERB,  JIJ_VERB,    HIJ_VERB,
                                                         WIJ_VERB, JULLIE_VERB, ZIJ_VERB);

                /* displayConjIndex is used in read-only mode to
                *      know what conjugation to show at a certain
                *      point (conjugation for ik, then for jij etc)*/
                displayConjIndex = 0;

                /* If the application is started for the first time
                 *   in read-only mode then instruct the user to tap
                 *   screen for conjugation */
                if ((firstTimeReadOnly) && (readOnlyPref)) {
                    String infoTapScreen = "Tap screen for conjugation";
                    final Snackbar snackBar = Snackbar.make(view, infoTapScreen,
                            Snackbar.LENGTH_INDEFINITE);

                    /* The snackbar message remains on the
                     *   screen until is dismissed */
                    snackBar.setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackBar.dismiss();
                            prefs.edit().putBoolean(firstReadKey, false).apply();
                            firstTimeReadOnly = false;
                        }
                    });
                    snackBar.show();
                }

                // Get default preferences values
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                showTranslationPref = sharedPrefs.getBoolean(PreferencesActivity.KEY_SHOW_TRANS, true);
                readOnlyPref = sharedPrefs.getBoolean(PreferencesActivity.KEY_READ_ONLY, false);

                // Display the infinitive and translation
                tenseConjugationResult.displayVerb(INFINITVE, TRANSLATION, showTranslationPref);

                if (readOnlyPref) {
                    // Display conjugations
                    tenseConjugationResult.displayConjugations(IK_VERB,  JIJ_VERB,    HIJ_VERB,
                                                               WIJ_VERB, JULLIE_VERB, ZIJ_VERB,
                                                               IK,   JIJ,    HIJ,
                                                               WIJ,  JULLIE, ZIJ,
                                                               displayConjIndex);
                } else {
                    // Display the input fields to write conjugation
                    tenseConjugationResult.displayInputFields(IK_VERB,  JIJ_VERB,    HIJ_VERB,
                                                              WIJ_VERB, JULLIE_VERB, ZIJ_VERB,
                                                              IK_VERB_FIELD,     JIJ_VERB_FIELD,
                                                              HIJ_VERB_FIELD,    WIJ_VERB_FIELD,
                                                              JULLIE_VERB_FIELD, ZIJ_VERB_FIELD);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        IK_VERB_FIELD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    tenseConjugationResult.handleInput(IK_VERB_FIELD, IK_VERB);
                    if (!correctVerbConjugation) {
                        // If one of the answers was already declared wrong do nothing
                    } else {
                        /* If the answer until now were correct but we got a wrong one,
                         *   set them all to false */
                        correctVerbConjugation = tenseConjugationResult.returnVerifiedResult();
                    }
                    correctVerbConjugation = tenseConjugationResult.returnVerifiedResult();
                    if     ( (IK_VERB_FIELD.    getVisibility() == View.GONE) &&
                             (JIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                             (HIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                             (WIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                             (JULLIE_VERB_FIELD.getVisibility() == View.GONE) &&
                             (ZIJ_VERB_FIELD.   getVisibility() == View.GONE) ) {
                        SKIP.setVisibility(View.GONE);
                        NEXT.setVisibility(View.VISIBLE);
                        /* If all the EditTexts are filled in
                         *   and if the answers are correct then
                         *   update the scores. If answers are not
                         *   correct then scores will be updated
                         *   when choosing a new verb */
                        if (correctVerbConjugation) {
                            scores.updateScores(correctVerbConjugation, currentlySelectedTense);
                        }
                    }
                }
            }
        });
        JIJ_VERB_FIELD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    tenseConjugationResult.handleInput(JIJ_VERB_FIELD, JIJ_VERB);
                    if (!correctVerbConjugation) {
                        // If one of the answers was already declared wrong do nothing
                    } else {
                        /* If the answer until now were correct but we got a wrong one,
                         *   set them all to false */
                        correctVerbConjugation = tenseConjugationResult.returnVerifiedResult();
                    }
                    if     ( (IK_VERB_FIELD.    getVisibility() == View.GONE) &&
                            (JIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                            (HIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                            (WIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                            (JULLIE_VERB_FIELD.getVisibility() == View.GONE) &&
                            (ZIJ_VERB_FIELD.   getVisibility() == View.GONE) ) {
                        SKIP.setVisibility(View.GONE);
                        NEXT.setVisibility(View.VISIBLE);
                        if (correctVerbConjugation) {
                            scores.updateScores(correctVerbConjugation, currentlySelectedTense);
                        }
                    }
                }
            }
        });
        HIJ_VERB_FIELD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    tenseConjugationResult.handleInput(HIJ_VERB_FIELD, HIJ_VERB);
                    if (!correctVerbConjugation) {
                        // If one of the answers was already declared wrong do nothing
                    } else {
                        /* If the answer until now were correct but we got a wrong one,
                         *   set them all to false */
                        correctVerbConjugation = tenseConjugationResult.returnVerifiedResult();
                    }
                    if     ( (IK_VERB_FIELD.    getVisibility() == View.GONE) &&
                            (JIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                            (HIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                            (WIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                            (JULLIE_VERB_FIELD.getVisibility() == View.GONE) &&
                            (ZIJ_VERB_FIELD.   getVisibility() == View.GONE) ) {
                        SKIP.setVisibility(View.GONE);
                        NEXT.setVisibility(View.VISIBLE);
                        if (correctVerbConjugation) {
                            scores.updateScores(correctVerbConjugation, currentlySelectedTense);
                        }
                    }
                }
            }
        });
        WIJ_VERB_FIELD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    tenseConjugationResult.handleInput(WIJ_VERB_FIELD, WIJ_VERB);
                    if (!correctVerbConjugation) {
                        // If one of the answers was already declared wrong do nothing
                    } else {
                        /* If the answer until now were correct but we got a wrong one,
                         *   set them all to false */
                        correctVerbConjugation = tenseConjugationResult.returnVerifiedResult();
                    }
                    if     ( (IK_VERB_FIELD.    getVisibility() == View.GONE) &&
                            (JIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                            (HIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                            (WIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                            (JULLIE_VERB_FIELD.getVisibility() == View.GONE) &&
                            (ZIJ_VERB_FIELD.   getVisibility() == View.GONE) ) {
                        SKIP.setVisibility(View.GONE);
                        NEXT.setVisibility(View.VISIBLE);
                        if (correctVerbConjugation) {
                            scores.updateScores(correctVerbConjugation, currentlySelectedTense);
                        }
                    }
                }
            }
        });
        JULLIE_VERB_FIELD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    tenseConjugationResult.handleInput(JULLIE_VERB_FIELD, JULLIE_VERB);
                    if (!correctVerbConjugation) {
                        // If one of the answers was already declared wrong do nothing
                    } else {
                        /* If the answer until now were correct but we got a wrong one,
                         *   set them all to false */
                        correctVerbConjugation = tenseConjugationResult.returnVerifiedResult();
                    }
                    if     ( (IK_VERB_FIELD.    getVisibility() == View.GONE) &&
                            (JIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                            (HIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                            (WIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                            (JULLIE_VERB_FIELD.getVisibility() == View.GONE) &&
                            (ZIJ_VERB_FIELD.   getVisibility() == View.GONE) ) {
                        SKIP.setVisibility(View.GONE);
                        NEXT.setVisibility(View.VISIBLE);
                        if (correctVerbConjugation) {
                            scores.updateScores(correctVerbConjugation, currentlySelectedTense);
                        }
                    }
                }
            }
        });
        ZIJ_VERB_FIELD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    tenseConjugationResult.handleInput(ZIJ_VERB_FIELD, ZIJ_VERB);
                    if (!correctVerbConjugation) {
                        // If one of the answers was already declared wrong do nothing
                    } else {
                        /* If the answer until now were correct but we got a wrong one,
                         *   set them all to false */
                        correctVerbConjugation = tenseConjugationResult.returnVerifiedResult();
                    }
                    if     ( (IK_VERB_FIELD.    getVisibility() == View.GONE) &&
                            (JIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                            (HIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                            (WIJ_VERB_FIELD.   getVisibility() == View.GONE) &&
                            (JULLIE_VERB_FIELD.getVisibility() == View.GONE) &&
                            (ZIJ_VERB_FIELD.   getVisibility() == View.GONE) ) {
                        SKIP.setVisibility(View.GONE);
                        NEXT.setVisibility(View.VISIBLE);
                        if (correctVerbConjugation) {
                            scores.updateScores(correctVerbConjugation, currentlySelectedTense);
                        }
                    }
                }
            }
        });

    }

    /** Define actions to be taken when clicking on the 'Skip' button */
    public void onClickSkip(View view) {
        // Show no conjugations in read-only mode
        displayConjIndex = 0;

        /* If the user clicks on 'Skip' after he
         *   got one conjugation wrong then this
         *   is registered as a wrong answer */
        if (!correctVerbConjugation) {
            scores.updateScores(correctVerbConjugation, currentlySelectedTense);
        }

        correctVerbConjugation = true;

        VerbsFileReader verbsFileReader = new VerbsFileReader(context);

        // Read file and set conjugations
        tenseConjugationResult = verbsFileReader.readFile(context, currentlySelectedTense, "next");
        tenseConjugationResult.setValuesTextView(IK_VERB,  JIJ_VERB,    HIJ_VERB,
                WIJ_VERB, JULLIE_VERB, ZIJ_VERB);

        // Get default values
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        showTranslationPref = sharedPrefs.getBoolean(PreferencesActivity.KEY_SHOW_TRANS, true);
        readOnlyPref = sharedPrefs.getBoolean(PreferencesActivity.KEY_READ_ONLY, false);

        // Display the infinitive and translation
        tenseConjugationResult.displayVerb(INFINITVE, TRANSLATION, showTranslationPref);

        if (readOnlyPref) {
            // Display conjugations
            tenseConjugationResult.displayConjugations(IK_VERB,  JIJ_VERB,    HIJ_VERB,
                    WIJ_VERB, JULLIE_VERB, ZIJ_VERB,
                    IK,   JIJ,    HIJ,
                    WIJ,  JULLIE, ZIJ,
                    displayConjIndex);
        } else {
            // Display the input fields to write conjugation
            tenseConjugationResult.displayInputFields(IK_VERB,  JIJ_VERB,    HIJ_VERB,
                    WIJ_VERB, JULLIE_VERB, ZIJ_VERB,
                    IK_VERB_FIELD,     JIJ_VERB_FIELD,
                    HIJ_VERB_FIELD,    WIJ_VERB_FIELD,
                    JULLIE_VERB_FIELD, ZIJ_VERB_FIELD);
        }
    }

    /** Define actions to be taken when clicking on the 'Next' button */
    public void onClickNext(View view) {
        // Set displayConjIndex to 0 so no conjugation will be shown until a screen tap
        displayConjIndex = 0;

        /* If the user clicks on 'Next' after he
         *   got one conjugation wrong then this
         *   is registered as a wrong answer */
        if (!correctVerbConjugation) {
            scores.updateScores(correctVerbConjugation, currentlySelectedTense);
        }

        correctVerbConjugation = true;

        SKIP.setVisibility(View.VISIBLE);
        NEXT.setVisibility(View.GONE);

        VerbsFileReader verbsFileReader = new VerbsFileReader(context);

        // Read file and set conjugations
        tenseConjugationResult = verbsFileReader.readFile(context, currentlySelectedTense, "next");
        tenseConjugationResult.setValuesTextView(IK_VERB,  JIJ_VERB,    HIJ_VERB,
                                                 WIJ_VERB, JULLIE_VERB, ZIJ_VERB);

        // Get default values
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        showTranslationPref = sharedPrefs.getBoolean(PreferencesActivity.KEY_SHOW_TRANS, true);
        readOnlyPref = sharedPrefs.getBoolean(PreferencesActivity.KEY_READ_ONLY, false);

        // Display the infinitive and translation
        tenseConjugationResult.displayVerb(INFINITVE, TRANSLATION, showTranslationPref);

        if (readOnlyPref) {
            // Display conjugations
            tenseConjugationResult.displayConjugations(IK_VERB,  JIJ_VERB,    HIJ_VERB,
                    WIJ_VERB, JULLIE_VERB, ZIJ_VERB,
                    IK,   JIJ,    HIJ,
                    WIJ,  JULLIE, ZIJ,
                    displayConjIndex);
        } else {
            // Display the input fields to write conjugation
            tenseConjugationResult.displayInputFields(IK_VERB,  JIJ_VERB,    HIJ_VERB,
                                                      WIJ_VERB, JULLIE_VERB, ZIJ_VERB,
                                                      IK_VERB_FIELD,     JIJ_VERB_FIELD,
                                                      HIJ_VERB_FIELD,    WIJ_VERB_FIELD,
                                                      JULLIE_VERB_FIELD, ZIJ_VERB_FIELD);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.preferences) {
            Intent displayPreferences = new Intent(MainActivity.this, PreferencesActivity.class);
            // Start new activity to display preferences
            startActivity(displayPreferences);
            return true;
        } else if (id == R.id.scores) {
            Intent displayScores = new Intent(MainActivity.this, ScoresActivity.class);
            // Start new activity to display the scores
            startActivity(displayScores);
            return true;
        } else if (id == R.id.about) {
            Intent displayAbout = new Intent(MainActivity.this, AboutActivity.class);
            // Start new activity to display the scores
            startActivity(displayAbout);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onTapScreen(View view) {
        /*  If the user opted for the read-only option
         *    then show the conjugation one by one for
         *    every person (ik, jij, hij...) when
         *    tapping the screen  */
        if (readOnlyPref) {
            if (displayConjIndex < 6) {
                displayConjIndex++;
                // Display conjugations
                tenseConjugationResult.displayConjugations(IK_VERB,  JIJ_VERB,    HIJ_VERB,
                                                           WIJ_VERB, JULLIE_VERB, ZIJ_VERB,
                                                           IK,       JIJ,         HIJ,
                                                           WIJ,      JULLIE,      ZIJ,
                                                           displayConjIndex);
                if (displayConjIndex == 6){
                    SKIP.setVisibility(View.GONE);
                    NEXT.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private static class MyAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
        private final ThemedSpinnerAdapter.Helper mDropDownHelper;

        public MyAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
            mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                // Inflate the drop down using the helper's LayoutInflater
                LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(getItem(position));

            return view;
        }

        @Override
        public Theme getDropDownViewTheme() {
            return mDropDownHelper.getDropDownViewTheme();
        }

        @Override
        public void setDropDownViewTheme(Theme theme) {
            mDropDownHelper.setDropDownViewTheme(theme);
        }
    }
}