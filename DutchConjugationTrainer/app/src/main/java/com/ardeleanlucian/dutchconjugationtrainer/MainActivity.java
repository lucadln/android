package com.ardeleanlucian.dutchconjugationtrainer;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import java.util.logging.FileHandler;

public class MainActivity extends AppCompatActivity {

    // Define variables for storing verb conjugation values.
    private String infinitive = "unknown";
    private String translation = "unknown";
    private String ikVerb = "unknown";
    private String jijVerb = "unknown";
    private String hijVerb = "unknown";
    private String wijVerb = "unknown";
    private String jullieVerb = "unknown";
    private String zijVerb = "unknown";

    Context context;

    TenseConjugationResult tenseConjugationResult;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Setup spinner
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

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // When the given dropdown item is selected, show its contents in the
                // container view.
                VerbsFileReader verbsFileReader = new VerbsFileReader(context);
                if (position == 0) {
                    tenseConjugationResult = verbsFileReader.readFile(context, "present", "none");
                } else if (position == 1) {
                    tenseConjugationResult = verbsFileReader.readFile(context, "present_continuous", "none");
                } else if (position == 2) {
                    tenseConjugationResult = verbsFileReader.readFile(context, "simple_past", "none");
                } else if (position == 3) {
                    tenseConjugationResult = verbsFileReader.readFile(context, "past_perfect", "none");
                } else if (position == 4) {
                    tenseConjugationResult = verbsFileReader.readFile(context, "conditional", "none");
                } else if (position == 5) {
                    tenseConjugationResult = verbsFileReader.readFile(context, "conditional_perfect", "none");
                } else if (position == 6) {
                    tenseConjugationResult = verbsFileReader.readFile(context, "future", "none");
                }
                infinitive = tenseConjugationResult.getInfinitive();
                translation = tenseConjugationResult.getTranslation();
                ikVerb = tenseConjugationResult.getIkVerb();
                jijVerb = tenseConjugationResult.getJijVerb();
                hijVerb = tenseConjugationResult.getHijVerb();
                wijVerb = tenseConjugationResult.getWijVerb();
                jullieVerb = tenseConjugationResult.getJullieVerb();
                zijVerb = tenseConjugationResult.getZijVerb();

                INFINITVE.setText(infinitive);
                TRANSLATION.setText(translation);
                IK_VERB.setText(ikVerb);
                JIJ_VERB.setText(jijVerb);
                HIJ_VERB.setText(hijVerb);
                WIJ_VERB.setText(wijVerb);
                JULLIE_VERB.setText(jullieVerb);
                ZIJ_VERB.setText(zijVerb);
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
