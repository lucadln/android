package com.ardeleanlucian.dutchconjugationtrainer.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.ardeleanlucian.dutchconjugationtrainer.R;
import com.ardeleanlucian.dutchconjugationtrainer.controller.ConjugationCenterController;
import com.ardeleanlucian.dutchconjugationtrainer.model.CustomSpinnerAdapter;
import com.ardeleanlucian.dutchconjugationtrainer.model.CyclicTransitionDrawable;
import com.ardeleanlucian.dutchconjugationtrainer.model.Verb;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * Created by Ardelean Lucian on 11/10/2017.
 */

public class ConjugationCenterHandler {

    private View FOCUS_THIEF;
    private View CORRECT_CONJUGATION;

    private Context context;

    private TableLayout TABLE_LAYOUT;
    private RelativeLayout CONJUGATION_SECTION;
    private RelativeLayout UPPER_CONTENT;
    private RelativeLayout APPLICATION_CONTENT;
    private RelativeLayout CLOSE_CORRECT_CONJUGATION_VIEW;
    private RelativeLayout SHOW_CORRECT_ANSWER_BUTTON_WRAPPER;

    private Button NEXT;
    private Button SKIP;
    private Button SHOW_CORRECT_ANSWER;
    private Button CLOSE_BUTTON;

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
    private TextView IK_CORRECT_CONJUGATION;
    private TextView JIJ_CORRECT_CONJUGATION;
    private TextView HIJ_CORRECT_CONJUGATION;
    private TextView WIJ_CORRECT_CONJUGATION;
    private TextView JULLIE_CORRECT_CONJUGATION;
    private TextView ZIJ_CORRECT_CONJUGATION;

    private EditText IK_VERB_FIELD;
    private EditText JIJ_VERB_FIELD;
    private EditText HIJ_VERB_FIELD;
    private EditText WIJ_VERB_FIELD;
    private EditText JULLIE_VERB_FIELD;
    private EditText ZIJ_VERB_FIELD;

    private Spinner spinner;
    private Toolbar toolbar;

    /**
     * Constructor method
     * 
     * @param context
     */
    public ConjugationCenterHandler(Context context) {
        this.context = context;
    }

    /**
     * Initialize loading_background elements
     */
    public void initializeLayoutElements() {
        TABLE_LAYOUT = (TableLayout) ((Activity) context).findViewById(R.id.table_layout);
        APPLICATION_CONTENT = (RelativeLayout) ((Activity) context).findViewById(R.id.application_content);
        UPPER_CONTENT = (RelativeLayout) ((Activity) context).findViewById(R.id.upper_content);
        CONJUGATION_SECTION = (RelativeLayout) ((Activity) context).findViewById(R.id.conjugation_section);
        SHOW_CORRECT_ANSWER_BUTTON_WRAPPER = (RelativeLayout) ((Activity) context).findViewById(
                R.id.show_correct_answer_button_wrapper);

        NEXT = (Button) ((Activity) context).findViewById(R.id.next);
        SKIP = (Button) ((Activity) context).findViewById(R.id.skip);
        SHOW_CORRECT_ANSWER = (Button) ((Activity) context).findViewById(R.id.show_correct_answer);
        CLOSE_BUTTON = (Button) ((Activity) context).findViewById(R.id.close_button);
        CLOSE_CORRECT_CONJUGATION_VIEW =
                (RelativeLayout) ((Activity) context).findViewById(R.id.close_correct_conjugation_view);
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
        IK_CORRECT_CONJUGATION = (TextView) ((Activity) context).findViewById(R.id.ik_correct_conjugation);
        JIJ_CORRECT_CONJUGATION = (TextView) ((Activity) context).findViewById(R.id.jij_correct_conjugation);
        HIJ_CORRECT_CONJUGATION = (TextView) ((Activity) context).findViewById(R.id.hij_correct_conjugation);
        WIJ_CORRECT_CONJUGATION = (TextView) ((Activity) context).findViewById(R.id.wij_correct_conjugation);
        JULLIE_CORRECT_CONJUGATION = (TextView) ((Activity) context).findViewById(R.id.jullie_correct_conjugation);
        ZIJ_CORRECT_CONJUGATION = (TextView) ((Activity) context).findViewById(R.id.zij_correct_conjugation);

        IK_VERB_FIELD = (EditText) ((Activity) context).findViewById(R.id.ik_verb_field);
        JIJ_VERB_FIELD = (EditText) ((Activity) context).findViewById(R.id.jij_verb_field);
        HIJ_VERB_FIELD = (EditText) ((Activity) context).findViewById(R.id.hij_verb_field);
        WIJ_VERB_FIELD = (EditText) ((Activity) context).findViewById(R.id.wij_verb_field);
        JULLIE_VERB_FIELD = (EditText) ((Activity) context).findViewById(R.id.jullie_verb_field);
        ZIJ_VERB_FIELD = (EditText) ((Activity) context).findViewById(R.id.zij_verb_field);

        FOCUS_THIEF = (View) ((Activity) context).findViewById(R.id.focus_thief);
        CORRECT_CONJUGATION = (View) ((Activity) context).findViewById(R.id.correct_conjugation);

        toolbar = (Toolbar) ((Activity) context).findViewById(R.id.toolbar);

        spinner = (Spinner) ((Activity) context).findViewById(R.id.spinner);
        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(
                getContext(), R.layout.spinner_content, Verb.tenses);
        spinner.setAdapter(customSpinnerAdapter);
        spinner.setSelection((new ConjugationCenterController(context)).obtainSpinnerIndex());
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
        SHOW_CORRECT_ANSWER.setVisibility(GONE);
        SHOW_CORRECT_ANSWER_BUTTON_WRAPPER.setVisibility(GONE);
        CORRECT_CONJUGATION.setVisibility(GONE);

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

            IK_VERB_FIELD.setVisibility(INVISIBLE);
            JIJ_VERB_FIELD.setVisibility(INVISIBLE);
            HIJ_VERB_FIELD.setVisibility(INVISIBLE);
            WIJ_VERB_FIELD.setVisibility(INVISIBLE);
            JULLIE_VERB_FIELD.setVisibility(INVISIBLE);
            ZIJ_VERB_FIELD.setVisibility(INVISIBLE);
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
     * Method to clear all values from the loading_background fields
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
     * Method to set the values for the TextViews in the loading_background.
     *
     * @param verb
     * @param spinnerIndex
     */
    public void setTextViewValues(Verb verb, int spinnerIndex) {

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
     * Method to set the values for the correct conjugation screen.
     *
     * @param verb
     * @param spinnerIndex
     */
    public void setCorrectConjugationValues(Verb verb, int spinnerIndex) {

        IK_CORRECT_CONJUGATION.setText(verb.getVerbConjugation()[spinnerIndex][0]);
        JIJ_CORRECT_CONJUGATION.setText(verb.getVerbConjugation()[spinnerIndex][1]);
        HIJ_CORRECT_CONJUGATION.setText(verb.getVerbConjugation()[spinnerIndex][2]);
        WIJ_CORRECT_CONJUGATION.setText(verb.getVerbConjugation()[spinnerIndex][3]);
        JULLIE_CORRECT_CONJUGATION.setText(verb.getVerbConjugation()[spinnerIndex][4]);
        ZIJ_CORRECT_CONJUGATION.setText(verb.getVerbConjugation()[spinnerIndex][5]);
    }

    /**
     * @return the number of filled edit texts
     */
    public int getNumberOfFilledEditTexts() {
        int count = 0;
        if (IK_VERB_FIELD.getVisibility() == View.INVISIBLE) {
            count++;
        }
        if (JIJ_VERB_FIELD.getVisibility() == View.INVISIBLE) {
            count++;
        }
        if (HIJ_VERB_FIELD.getVisibility() == View.INVISIBLE) {
            count++;
        }
        if (WIJ_VERB_FIELD.getVisibility() == View.INVISIBLE) {
            count++;
        }
        if (JULLIE_VERB_FIELD.getVisibility() == View.INVISIBLE) {
            count++;
        }
        if (ZIJ_VERB_FIELD.getVisibility() == View.INVISIBLE) {
            count++;
        }
        return count;
    }

    public TableLayout getTableLayout() {
        return TABLE_LAYOUT;
    }

    public void setTableLayout(TableLayout TABLE_LAYOUT) {
        this.TABLE_LAYOUT = TABLE_LAYOUT;
    }

    public Button getNext() {
        return NEXT;
    }

    public void setNext(Button NEXT) {
        this.NEXT = NEXT;
    }

    public Button getSkip() {
        return SKIP;
    }

    public void setSkip(Button SKIP) {
        this.SKIP = SKIP;
    }

    public RelativeLayout getCloseCorrectConjugation() {return CLOSE_CORRECT_CONJUGATION_VIEW; }

    public TextView getInfinitive() {
        return INFINITIVE;
    }

    public void setInfinitive(TextView INFINITIVE) {
        this.INFINITIVE = INFINITIVE;
    }

    public TextView getTranslation() {
        return TRANSLATION;
    }

    public void setTranslation(TextView TRANSLATION) {
        this.TRANSLATION = TRANSLATION;
    }

    public TextView getIk() {
        return IK;
    }

    public void setIk(TextView IK) {
        this.IK = IK;
    }

    public TextView getJij() {
        return JIJ;
    }

    public void setJij(TextView JIJ) {
        this.JIJ = JIJ;
    }

    public TextView getHij() {
        return HIJ;
    }

    public void setHij(TextView HIJ) {
        this.HIJ = HIJ;
    }

    public TextView getWij() {
        return WIJ;
    }

    public void setWij(TextView WIJ) {
        this.WIJ = WIJ;
    }

    public TextView getJullie() {
        return JULLIE;
    }

    public void setJullie(TextView JULLIE) {
        this.JULLIE = JULLIE;
    }

    public TextView getZij() {
        return ZIJ;
    }

    public void setZij(TextView ZIJ) {
        this.ZIJ = ZIJ;
    }

    public TextView getIkVerbText() {
        return IK_VERB_TEXT;
    }

    public void setIkVerbText(TextView IK_VERB_TEXT) {
        this.IK_VERB_TEXT = IK_VERB_TEXT;
    }

    public TextView getJijVerbText() {
        return JIJ_VERB_TEXT;
    }

    public void setJijVerbText(TextView JIJ_VERB_TEXT) {
        this.JIJ_VERB_TEXT = JIJ_VERB_TEXT;
    }

    public TextView getHijVerbText() {
        return HIJ_VERB_TEXT;
    }

    public void setHijVerbText(TextView HIJ_VERB_TEXT) {
        this.HIJ_VERB_TEXT = HIJ_VERB_TEXT;
    }

    public TextView getWijVerbText() {
        return WIJ_VERB_TEXT;
    }

    public void setWijVerbText(TextView WIJ_VERB_TEXT) {
        this.WIJ_VERB_TEXT = WIJ_VERB_TEXT;
    }

    public TextView getJullieVerbText() {
        return JULLIE_VERB_TEXT;
    }

    public void setJullieVerbText(TextView JULLIE_VERB_TEXT) {
        this.JULLIE_VERB_TEXT = JULLIE_VERB_TEXT;
    }

    public TextView getZijVerbText() {
        return ZIJ_VERB_TEXT;
    }

    public void setZijVerbText(TextView ZIJ_VERB_TEXT) {
        this.ZIJ_VERB_TEXT = ZIJ_VERB_TEXT;
    }

    public EditText getIkVerbField() {
        return IK_VERB_FIELD;
    }

    public void setIkVerbField(EditText IK_VERB_FIELD) {
        this.IK_VERB_FIELD = IK_VERB_FIELD;
    }

    public EditText getJijVerbField() {
        return JIJ_VERB_FIELD;
    }

    public void setJijVerbField(EditText JIJ_VERB_FIELD) {
        this.JIJ_VERB_FIELD = JIJ_VERB_FIELD;
    }

    public EditText getHijVerbField() {
        return HIJ_VERB_FIELD;
    }

    public void setHijVerbField(EditText HIJ_VERB_FIELD) {
        this.HIJ_VERB_FIELD = HIJ_VERB_FIELD;
    }

    public EditText getWijVerbField() {
        return WIJ_VERB_FIELD;
    }

    public void setWijVerbField(EditText WIJ_VERB_FIELD) {
        this.WIJ_VERB_FIELD = WIJ_VERB_FIELD;
    }

    public EditText getJullieVerbField() {
        return JULLIE_VERB_FIELD;
    }

    public void setJullieVerbField(EditText JULLIE_VERB_FIELD) {
        this.JULLIE_VERB_FIELD = JULLIE_VERB_FIELD;
    }

    public EditText getZijVerbField() {
        return ZIJ_VERB_FIELD;
    }

    public void setZijVerbField(EditText ZIJ_VERB_FIELD) {
        this.ZIJ_VERB_FIELD = ZIJ_VERB_FIELD;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    public void setSpinner(Spinner spinner) {
        this.spinner = spinner;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public Button getCloseButton() { return CLOSE_BUTTON; }

    public View getFocusThief() { return FOCUS_THIEF; }

    public Context getContext() { return context; }

    public RelativeLayout getConjugationSection() { return CONJUGATION_SECTION; }

    public RelativeLayout getUpperContent() { return UPPER_CONTENT; }

    public Button getShowCorrectAnswer() { return SHOW_CORRECT_ANSWER; }

    public View getCorrectConjugation() { return CORRECT_CONJUGATION; }

    public View getShowCorrectAnswerButtonWrapper() { return SHOW_CORRECT_ANSWER_BUTTON_WRAPPER; }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public void setVisibility(View view, int visibility) {
        view.setVisibility(visibility);
    }

    public void setTextViewAnswer(TextView textView, String answer) {
        textView.setText(answer);
    }

    public void shake(TextView textView) {
        // Shake animation @TODO move in a special animation class
        final Animation animShake = AnimationUtils.loadAnimation(
                context, R.anim.wrong_answer_shake);
        textView.startAnimation(animShake);
    }

    public void setTextViewColor(TextView textView, String color) {
        if (color.equals("green")) {
            textView.setTextColor(Color.rgb( 0, 153, 0 ));
        } else if (color.equals("red")) {
            textView.setTextColor(Color.RED);
        } else if (color.equals("yellow")) {
            textView.setTextColor(Color.rgb( 255, 162, 25 ));
        }
    }

    public RelativeLayout getApplicationContent() {
        return APPLICATION_CONTENT;
    }

    /**
     * Method to turn on/off the focus thief
     * @param focusAbility
     */
    public void switchFocusThief(boolean focusAbility) {
        FOCUS_THIEF.setFocusable(focusAbility);
        FOCUS_THIEF.setFocusableInTouchMode(focusAbility);
    }

    /**
     * Reset focus to the first edittext in the loading_background (i.e. IK_VERB_FIELD)
     */
    public void resetFocus() {
        IK_VERB_FIELD.requestFocus();
    }

    public void pulseButtonColor(Button button) {
        final CyclicTransitionDrawable drawable = new CyclicTransitionDrawable( new Drawable[] {
                    new ColorDrawable(context.getResources().getColor(R.color.colorPrimary)),
                    new ColorDrawable(context.getResources().getColor(R.color.darkGray))
            });
        button.setBackground(drawable);
        drawable.startTransition(1000, 0);
    }
}
