package com.ardeleanlucian.dutchconjugationtrainer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ardelean Lucian on 8/9/2017.
 */

public class PreferencesActivity extends AppCompatActivity {

    public static final String KEY_SHOW_TRANS = "show_translation";
    public static final String KEY_READ_ONLY = "read_only";
    public static final String KEY_SHOW_PERF = "give_feedback";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add an action bar with navigation.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Preferences");

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource.
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
