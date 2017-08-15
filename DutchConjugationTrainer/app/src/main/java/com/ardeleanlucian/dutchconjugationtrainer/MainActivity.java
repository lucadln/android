package com.ardeleanlucian.dutchconjugationtrainer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Spinner;
import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.content.res.Resources.Theme;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private TenseConjugationResult tenseConjugationResult;
    private int spinnerPosition;
    private boolean showTranslationPref;
    private boolean readOnlyPref;
    private int displayConjIndex = 0;
    private String tense[] = {"Present", "Present Continuous", "Simple Past", "Past Perfect",
                              "Condtional", "Conditional Perfect", "Future" };

    private TextView INFINITVE, TRANSLATION,
                     IK,      JIJ,      HIJ,      WIJ,      JULLIE,      ZIJ,
                     IK_VERB, JIJ_VERB, HIJ_VERB, WIJ_VERB, JULLIE_VERB, ZIJ_VERB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        // Set preferences-menu default values when application is started for the first time
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        // Get preferences from phone storage
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        showTranslationPref = sharedPrefs.getBoolean(SettingsActivity.KEY_SHOW_TRANS, true);
        readOnlyPref = sharedPrefs.getBoolean(SettingsActivity.KEY_READ_ONLY, false);

        // Set up the action toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Set variables for textviews
        INFINITVE =  (TextView) findViewById(R.id.infinitive);
        TRANSLATION = (TextView) findViewById(R.id.translation);
        IK_VERB = (TextView) findViewById(R.id.ik_present);
        JIJ_VERB = (TextView) findViewById(R.id.jij_present);
        HIJ_VERB = (TextView) findViewById(R.id.hij_present);
        WIJ_VERB = (TextView) findViewById(R.id.wij_present);
        JULLIE_VERB = (TextView) findViewById(R.id.jullie_present);
        ZIJ_VERB = (TextView) findViewById(R.id.zij_present);
        IK = (TextView) findViewById(R.id.ik);
        JIJ = (TextView) findViewById(R.id.jij);
        HIJ = (TextView) findViewById(R.id.hij);
        WIJ = (TextView) findViewById(R.id.wij);
        JULLIE = (TextView) findViewById(R.id.jullie);
        ZIJ = (TextView) findViewById(R.id.zij);

        // Define variables for reading last used tense
        final SharedPreferences prefs = context.getSharedPreferences(
                "com.ardeleanlucian.dutchconjugationtrainer", Context.MODE_PRIVATE);
        final String currentTenseKey = "com.ardeleanlucian.dutchconjugationtrainer.current_tense";
        // Get the last used tense
        spinnerPosition = prefs.getInt(currentTenseKey, 0);

        // Set up the spinner
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new MyAdapter(
                toolbar.getContext(), tense));

        spinner.setSelection(spinnerPosition);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // When the given dropdown item is selected, show its contents in the
                // container view.
                VerbsFileReader verbsFileReader = new VerbsFileReader(context);
                tenseConjugationResult = verbsFileReader.readFile(context, position, "none");

                // Get default preferences values
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                showTranslationPref = sharedPrefs.getBoolean(SettingsActivity.KEY_SHOW_TRANS, true);
                readOnlyPref = sharedPrefs.getBoolean(SettingsActivity.KEY_READ_ONLY, false);

                // Display the infinitive and translation
                tenseConjugationResult.displayVerb(INFINITVE, TRANSLATION, showTranslationPref);
                // Display conjugations
                tenseConjugationResult.displayConjugations(IK_VERB,  JIJ_VERB,    HIJ_VERB,
                                                           WIJ_VERB, JULLIE_VERB, ZIJ_VERB,
                                                           IK,       JIJ,         HIJ,
                                                           WIJ,      JULLIE,      ZIJ,
                                                           displayConjIndex);

                // Save tense choice in phone memory.
                spinnerPosition = position;
                prefs.edit().putInt(currentTenseKey, position).apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /** Define actions to be taken when clicking on the 'Next' button */
    public void onClickNext(View view) {
        // Set displayConjIndex to 0 so no conjugation will be shown until a screen tap
        displayConjIndex = 0;

        VerbsFileReader verbsFileReader = new VerbsFileReader(context);
        tenseConjugationResult = verbsFileReader.readFile(context, spinnerPosition, "next");

        // Get default values
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        showTranslationPref = sharedPrefs.getBoolean(SettingsActivity.KEY_SHOW_TRANS, true);
        readOnlyPref = sharedPrefs.getBoolean(SettingsActivity.KEY_READ_ONLY, false);

        // Display the infinitive and translation
        tenseConjugationResult.displayVerb(INFINITVE, TRANSLATION, showTranslationPref);
        // Display conjugations
        tenseConjugationResult.displayConjugations(IK_VERB,  JIJ_VERB,    HIJ_VERB,
                                                   WIJ_VERB, JULLIE_VERB, ZIJ_VERB,
                                                   IK,       JIJ,         HIJ,
                                                   WIJ,      JULLIE,      ZIJ,
                                                   displayConjIndex);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_preferences) {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            // Start new activity to display preferences
            startActivity(i);
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