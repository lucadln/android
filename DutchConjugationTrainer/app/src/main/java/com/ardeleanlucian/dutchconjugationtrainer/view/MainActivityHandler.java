package com.ardeleanlucian.dutchconjugationtrainer.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;
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

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * Created by Ardelean Lucian on 11/10/2017.
 */

public class MainActivityHandler {

    private Context context;

    public TableLayout TABLE_LAYOUT;

    public Button NEXT;
    public Button SKIP;

    public TextView INFINITIVE;
    public TextView TRANSLATION;
    public TextView IK;
    public TextView JIJ;
    public TextView HIJ;
    public TextView WIJ;
    public TextView JULLIE;
    public TextView ZIJ;
    public TextView IK_VERB_TEXT;
    public TextView JIJ_VERB_TEXT;
    public TextView HIJ_VERB_TEXT;
    public TextView WIJ_VERB_TEXT;
    public TextView JULLIE_VERB_TEXT;
    public TextView ZIJ_VERB_TEXT;

    public EditText IK_VERB_FIELD;
    public EditText JIJ_VERB_FIELD;
    public EditText HIJ_VERB_FIELD;
    public EditText WIJ_VERB_FIELD;
    public EditText JULLIE_VERB_FIELD;
    public EditText ZIJ_VERB_FIELD;

    public Spinner spinner;
    public Toolbar toolbar;

    TextView textViewList[] = { IK_VERB_TEXT,  JIJ_VERB_TEXT,    HIJ_VERB_TEXT,
                                WIJ_VERB_TEXT, JULLIE_VERB_TEXT, ZIJ_VERB_TEXT };
    EditText editTextList[] = { IK_VERB_FIELD,  JIJ_VERB_FIELD,    HIJ_VERB_FIELD,
                                WIJ_VERB_FIELD, JULLIE_VERB_FIELD, ZIJ_VERB_FIELD };

    /**
     * Constructor method
     * 
     * @param context
     */
    public MainActivityHandler(Context context) {
        this.context = context;
        initializeLayoutElements();
    }

    /**
     * Initialize layout elements
     */
    public void initializeLayoutElements() {
        TABLE_LAYOUT = (TableLayout) ((Activity) context).findViewById(R.id.table_layout);

        NEXT = (Button) ((Activity) context).findViewById(R.id.next);
        SKIP = (Button) ((Activity) context).findViewById(R.id.skip);

        INFINITIVE = (TextView) ((Activity) context).findViewById(R.id.infinitive);
        TRANSLATION = (TextView) ((Activity) context).findViewById(R.id.translation);
        IK = (TextView) ((Activity) context).findViewById(R.id.ik);
        JIJ = (TextView) ((Activity) context).findViewById(R.id.jij);
        HIJ = (TextView) ((Activity) context).findViewById(R.id.hij);
        WIJ = (TextView) ((Activity) context).findViewById(R.id.wij);
        JULLIE = (TextView) ((Activity) context).findViewById(R.id.jullie);
        ZIJ = (TextView) ((Activity) context).findViewById(R.id.zij);
        IK_VERB_TEXT = (TextView) ((Activity) context).findViewById(R.id.ik_verb_text);
        JIJ_VERB_TEXT = (TextView) ((Activity) context).findViewById(R.id.jij_verb_text);
        HIJ_VERB_TEXT = (TextView) ((Activity) context).findViewById(R.id.hij_verb_text);
        WIJ_VERB_TEXT = (TextView) ((Activity) context).findViewById(R.id.wij_verb_text);
        JULLIE_VERB_TEXT = (TextView) ((Activity) context).findViewById(R.id.jullie_verb_text);
        ZIJ_VERB_TEXT = (TextView) ((Activity) context).findViewById(R.id.zij_verb_text);

        IK_VERB_FIELD = (EditText) ((Activity) context).findViewById(R.id.ik_verb_field);
        JIJ_VERB_FIELD = (EditText) ((Activity) context).findViewById(R.id.jij_verb_field);
        HIJ_VERB_FIELD = (EditText) ((Activity) context).findViewById(R.id.hij_verb_field);
        WIJ_VERB_FIELD = (EditText) ((Activity) context).findViewById(R.id.wij_verb_field);
        JULLIE_VERB_FIELD = (EditText) ((Activity) context).findViewById(R.id.jullie_verb_field);
        ZIJ_VERB_FIELD = (EditText) ((Activity) context).findViewById(R.id.zij_verb_field);

        toolbar = (Toolbar) ((Activity) context).findViewById(R.id.toolbar);

        spinner = (Spinner) ((Activity) context).findViewById(R.id.spinner);
        spinner.setAdapter(new SpinnerAdapter(toolbar.getContext(), Score.tenses));
        spinner.setSelection((new MainController(context)).obtainSpinnerIndex());
    }

    /**
     * Method to reset the conjugation visibility depending
     *   on the play mode (read-only or write).
     *
     * @param readOnly
     */
    public void resetConjugationSectionVisibility(boolean readOnly, boolean showTranslation) {
        SKIP.setVisibility(VISIBLE);
        NEXT.setVisibility(GONE);

        if (showTranslation) {
            TRANSLATION.setVisibility(VISIBLE);
        } else {
            TRANSLATION.setVisibility(INVISIBLE);
        }

         if (readOnly == true) {
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

    /**
     * Method to clear all values from the layout fields
     */
    public void clearFields() {
        IK_VERB_FIELD.setText( "" );
        JIJ_VERB_FIELD.setText( "" );
        HIJ_VERB_FIELD.setText( "" );
        WIJ_VERB_FIELD.setText( "" );
        JULLIE_VERB_FIELD.setText( "" );
        ZIJ_VERB_FIELD.setText( "" );
    }

    /**
     * Method to set the values for the TextViews in the layout.
     *
     * @param verb
     * @param spinnerIndex
     */
    public void setTextViewValues(Verb verb, int spinnerIndex, boolean readOnly) {

        INFINITIVE.setText(verb.getVerbInfinitive());
        TRANSLATION.setText(verb.getVerbTranslation());

        if (readOnly) {
            IK_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][0]);
            JIJ_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][1]);
            HIJ_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][2]);
            WIJ_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][3]);
            JULLIE_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][4]);
            ZIJ_VERB_TEXT.setText(verb.getVerbConjugation()[spinnerIndex][5]);
        }
    }

    /**
     * @return the current number of filled edit texts
     */
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
}
