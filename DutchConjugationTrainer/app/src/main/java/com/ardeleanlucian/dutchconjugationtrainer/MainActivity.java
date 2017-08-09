package com.ardeleanlucian.dutchconjugationtrainer;

import android.content.Intent;
import android.content.SharedPreferences;
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

    Context context;
    TenseConjugationResult tenseConjugationResult;
    int spinnerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        // Define variables for textviews
        final TextView INFINITVE = (TextView) findViewById(R.id.infinitive);
        final TextView TRANSLATION = (TextView) findViewById(R.id.translation);
        final TextView IK_VERB = (TextView) findViewById(R.id.ik_present);
        final TextView JIJ_VERB = (TextView) findViewById(R.id.jij_present);
        final TextView HIJ_VERB = (TextView) findViewById(R.id.hij_present);
        final TextView WIJ_VERB = (TextView) findViewById(R.id.wij_present);
        final TextView JULLIE_VERB = (TextView) findViewById(R.id.jullie_present);
        final TextView ZIJ_VERB = (TextView) findViewById(R.id.zij_present);

        // Set up the action toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Define variables for reading last used tense
        final SharedPreferences prefs = context.getSharedPreferences(
                "com.ardeleanlucian.dutchconjugationtrainer", Context.MODE_PRIVATE);
        final String currentTenseKey = "com.ardeleanlucian.dutchconjugationtrainer.current_tense";
        // Get the last used tense
        spinnerPosition = prefs.getInt(currentTenseKey, 0);

        // Set up the spinner
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new MyAdapter(
                toolbar.getContext(),
                new String[]{
                        "Present",
                        "Present Continuous",
                        "Simple Past",
                        "Past Perfect",
                        "Condtional",
                        "Conditional Perfect",
                        "Future"
                }));

        spinner.setSelection(spinnerPosition);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // When the given dropdown item is selected, show its contents in the
                // container view.
                VerbsFileReader verbsFileReader = new VerbsFileReader(context);
                tenseConjugationResult = verbsFileReader.readFile(context, position, "none");

                // Display the new conjugations
                INFINITVE.setText(   tenseConjugationResult.getInfinitive()  );
                TRANSLATION.setText( tenseConjugationResult.getTranslation() );
                IK_VERB.setText(     tenseConjugationResult.getIkVerb()      );
                JIJ_VERB.setText(    tenseConjugationResult.getJijVerb()     );
                HIJ_VERB.setText(    tenseConjugationResult.getHijVerb()     );
                WIJ_VERB.setText(    tenseConjugationResult.getWijVerb()     );
                JULLIE_VERB.setText( tenseConjugationResult.getJullieVerb()  );
                ZIJ_VERB.setText(    tenseConjugationResult.getZijVerb()     );

                // Save tense choice in application memory.
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
        VerbsFileReader verbsFileReader = new VerbsFileReader(context);
        tenseConjugationResult = verbsFileReader.readFile(context, spinnerPosition, "next");

        // Define variables for textviews
        final TextView INFINITVE = (TextView) findViewById(R.id.infinitive);
        final TextView TRANSLATION = (TextView) findViewById(R.id.translation);
        final TextView IK_VERB = (TextView) findViewById(R.id.ik_present);
        final TextView JIJ_VERB = (TextView) findViewById(R.id.jij_present);
        final TextView HIJ_VERB = (TextView) findViewById(R.id.hij_present);
        final TextView WIJ_VERB = (TextView) findViewById(R.id.wij_present);
        final TextView JULLIE_VERB = (TextView) findViewById(R.id.jullie_present);
        final TextView ZIJ_VERB = (TextView) findViewById(R.id.zij_present);

        // Display the new conjugations
        INFINITVE.setText(   tenseConjugationResult.getInfinitive()  );
        TRANSLATION.setText( tenseConjugationResult.getTranslation() );
        IK_VERB.setText(     tenseConjugationResult.getIkVerb()      );
        JIJ_VERB.setText(    tenseConjugationResult.getJijVerb()     );
        HIJ_VERB.setText(    tenseConjugationResult.getHijVerb()     );
        WIJ_VERB.setText(    tenseConjugationResult.getWijVerb()     );
        JULLIE_VERB.setText( tenseConjugationResult.getJullieVerb()  );
        ZIJ_VERB.setText(    tenseConjugationResult.getZijVerb()     );
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
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, MyPreferencesActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
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