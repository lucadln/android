package com.ardeleanlucian.dutchconjugationtrainer;

import android.graphics.Color;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Ardelean Lucian on 8/6/2017.
 */

public class TenseConjugationResult {

    private String infinitive;
    private String translation;
    private String ikVerb;
    private String jijVerb;
    private String hijVerb;
    private String wijVerb;
    private String jullieVerb;
    private String zijVerb;

    public TenseConjugationResult(String infinitive, String translation,
                                  String ikVerb, String jijVerb, String hijVerb,
                                  String wijVerb, String jullieVerb, String zijVerb) {
        this.infinitive = infinitive;
        this.translation = translation;
        this.ikVerb = ikVerb;
        this.jijVerb = jijVerb;
        this.hijVerb = hijVerb;
        this.wijVerb = wijVerb;
        this.jullieVerb = jullieVerb;
        this.zijVerb = zijVerb;
    }

    // Method to set TextViews values
    public void setValuesTextView(TextView IK_VERB,  TextView JIJ_VERB,    TextView HIJ_VERB,
                                  TextView WIJ_VERB, TextView JULLIE_VERB, TextView ZIJ_VERB) {
        IK_VERB.setText( ikVerb );
        JIJ_VERB.setText( jijVerb );
        HIJ_VERB.setText( hijVerb );
        WIJ_VERB.setText( wijVerb );
        JULLIE_VERB.setText( jullieVerb );
        ZIJ_VERB.setText( zijVerb );
    }

    // Method to display infinitive and translation
    public void displayVerb(TextView INFINITIVE, TextView TRANSLATION, boolean showTranslation) {
        INFINITIVE.setText( infinitive );
        if (showTranslation) {
            TRANSLATION.setText( translation );
            TRANSLATION.setVisibility(View.VISIBLE);
        } else {
            TRANSLATION.setVisibility(View.GONE);
        }
    }

    // Method to display verb conjugations
    public void displayConjugations(TextView IK_VERB,  TextView JIJ_VERB,    TextView HIJ_VERB,
                                    TextView WIJ_VERB, TextView JULLIE_VERB, TextView ZIJ_VERB,
                                    TextView IK,       TextView JIJ,         TextView HIJ,
                                    TextView WIJ,      TextView JULLIE,      TextView ZIJ,
                                    int displayConjIndex) {

        // Fade in animation
        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(800);

        switch (displayConjIndex) {
            case 0:
                IK_VERB.setText( "..." );

                IK_VERB.setVisibility(View.VISIBLE);
                JIJ_VERB.setVisibility(View.INVISIBLE);
                HIJ_VERB.setVisibility(View.INVISIBLE);
                WIJ_VERB.setVisibility(View.INVISIBLE);
                JULLIE_VERB.setVisibility(View.INVISIBLE);
                ZIJ_VERB.setVisibility(View.INVISIBLE);

                IK.setVisibility(View.VISIBLE);
                JIJ.setVisibility(View.INVISIBLE);
                HIJ.setVisibility(View.INVISIBLE);
                WIJ.setVisibility(View.INVISIBLE);
                JULLIE.setVisibility(View.INVISIBLE);
                ZIJ.setVisibility(View.INVISIBLE);

                // Fade in the first key-value pair
                IK.startAnimation(in);
                IK_VERB.startAnimation(in);
                break;
            case 1:
                IK_VERB.setText( ikVerb );
                JIJ_VERB.setText( "..." );

                IK_VERB.setVisibility(View.VISIBLE);
                JIJ_VERB.setVisibility(View.VISIBLE);
                HIJ_VERB.setVisibility(View.INVISIBLE);
                WIJ_VERB.setVisibility(View.INVISIBLE);
                JULLIE_VERB.setVisibility(View.INVISIBLE);
                ZIJ_VERB.setVisibility(View.INVISIBLE);

                IK.setVisibility(View.VISIBLE);
                JIJ.setVisibility(View.VISIBLE);
                HIJ.setVisibility(View.INVISIBLE);
                WIJ.setVisibility(View.INVISIBLE);
                JULLIE.setVisibility(View.INVISIBLE);
                ZIJ.setVisibility(View.INVISIBLE);

                // Fade in the next key-value pair
                JIJ.startAnimation(in);
                JIJ_VERB.startAnimation(in);
                break;
            case 2:
                JIJ_VERB.setText( jijVerb );
                HIJ_VERB.setText( "..." );

                IK_VERB.setVisibility(View.VISIBLE);
                JIJ_VERB.setVisibility(View.VISIBLE);
                HIJ_VERB.setVisibility(View.VISIBLE);
                WIJ_VERB.setVisibility(View.INVISIBLE);
                JULLIE_VERB.setVisibility(View.INVISIBLE);
                ZIJ_VERB.setVisibility(View.INVISIBLE);

                IK.setVisibility(View.VISIBLE);
                JIJ.setVisibility(View.VISIBLE);
                HIJ.setVisibility(View.VISIBLE);
                WIJ.setVisibility(View.INVISIBLE);
                JULLIE.setVisibility(View.INVISIBLE);
                ZIJ.setVisibility(View.INVISIBLE);

                // Fade in the next key-value pair
                HIJ.startAnimation(in);
                HIJ_VERB.startAnimation(in);
                break;
            case 3:
                HIJ_VERB.setText( hijVerb );
                WIJ_VERB.setText( "..." );

                IK_VERB.setVisibility(View.VISIBLE);
                JIJ_VERB.setVisibility(View.VISIBLE);
                HIJ_VERB.setVisibility(View.VISIBLE);
                WIJ_VERB.setVisibility(View.VISIBLE);
                JULLIE_VERB.setVisibility(View.INVISIBLE);
                ZIJ_VERB.setVisibility(View.INVISIBLE);

                IK.setVisibility(View.VISIBLE);
                JIJ.setVisibility(View.VISIBLE);
                HIJ.setVisibility(View.VISIBLE);
                WIJ.setVisibility(View.VISIBLE);
                JULLIE.setVisibility(View.INVISIBLE);
                ZIJ.setVisibility(View.INVISIBLE);

                // Fade in the next key-value pair
                WIJ.startAnimation(in);
                WIJ_VERB.startAnimation(in);
                break;
            case 4:
                WIJ_VERB.setText( wijVerb );
                JULLIE_VERB.setText( "..." );

                IK_VERB.setVisibility(View.VISIBLE);
                JIJ_VERB.setVisibility(View.VISIBLE);
                HIJ_VERB.setVisibility(View.VISIBLE);
                WIJ_VERB.setVisibility(View.VISIBLE);
                JULLIE_VERB.setVisibility(View.VISIBLE);
                ZIJ_VERB.setVisibility(View.INVISIBLE);

                IK.setVisibility(View.VISIBLE);
                JIJ.setVisibility(View.VISIBLE);
                HIJ.setVisibility(View.VISIBLE);
                WIJ.setVisibility(View.VISIBLE);
                JULLIE.setVisibility(View.VISIBLE);
                ZIJ.setVisibility(View.INVISIBLE);

                // Fade in the next key-value pair
                JULLIE.startAnimation(in);
                JULLIE_VERB.startAnimation(in);
                break;
            case 5:
                JULLIE_VERB.setText( jullieVerb );
                ZIJ_VERB.setText( "..." );

                IK_VERB.setVisibility(View.VISIBLE);
                JIJ_VERB.setVisibility(View.VISIBLE);
                HIJ_VERB.setVisibility(View.VISIBLE);
                WIJ_VERB.setVisibility(View.VISIBLE);
                JULLIE_VERB.setVisibility(View.VISIBLE);
                ZIJ_VERB.setVisibility(View.VISIBLE);

                IK.setVisibility(View.VISIBLE);
                JIJ.setVisibility(View.VISIBLE);
                HIJ.setVisibility(View.VISIBLE);
                WIJ.setVisibility(View.VISIBLE);
                JULLIE.setVisibility(View.VISIBLE);
                ZIJ.setVisibility(View.VISIBLE);

                // Fade in the next key-value pair
                ZIJ.startAnimation(in);
                ZIJ_VERB.startAnimation(in);
                break;
            case 6:
                ZIJ_VERB.setText( zijVerb );

                IK_VERB.setVisibility(View.VISIBLE);
                JIJ_VERB.setVisibility(View.VISIBLE);
                HIJ_VERB.setVisibility(View.VISIBLE);
                WIJ_VERB.setVisibility(View.VISIBLE);
                JULLIE_VERB.setVisibility(View.VISIBLE);
                ZIJ_VERB.setVisibility(View.VISIBLE);

                IK.setVisibility(View.VISIBLE);
                JIJ.setVisibility(View.VISIBLE);
                HIJ.setVisibility(View.VISIBLE);
                WIJ.setVisibility(View.VISIBLE);
                JULLIE.setVisibility(View.VISIBLE);
                ZIJ.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void displayInputFields(TextView IK_VERB,           TextView JIJ_VERB,
                                   TextView HIJ_VERB,          TextView WIJ_VERB,
                                   TextView JULLIE_VERB,       TextView ZIJ_VERB,
                                   TextView IK_VERB_FIELD,     TextView JIJ_VERB_FIELD,
                                   TextView HIJ_VERB_FIELD,    TextView WIJ_VERB_FIELD,
                                   TextView JULLIE_VERB_FIELD, TextView ZIJ_VERB_FIELD) {

        IK_VERB.setVisibility(View.GONE);
        JIJ_VERB.setVisibility(View.GONE);
        HIJ_VERB.setVisibility(View.GONE);
        WIJ_VERB.setVisibility(View.GONE);
        JULLIE_VERB.setVisibility(View.GONE);
        ZIJ_VERB.setVisibility(View.GONE);

        IK_VERB_FIELD.setVisibility(View.VISIBLE);
        JIJ_VERB_FIELD.setVisibility(View.VISIBLE);
        HIJ_VERB_FIELD.setVisibility(View.VISIBLE);
        WIJ_VERB_FIELD.setVisibility(View.VISIBLE);
        JULLIE_VERB_FIELD.setVisibility(View.VISIBLE);
        ZIJ_VERB_FIELD.setVisibility(View.VISIBLE);

        IK_VERB_FIELD.setText( "" );
        JIJ_VERB_FIELD.setText( "" );
        HIJ_VERB_FIELD.setText( "" );
        WIJ_VERB_FIELD.setText( "" );
        JULLIE_VERB_FIELD.setText( "" );
        ZIJ_VERB_FIELD.setText( "" );
    }

    public void handleInput(EditText INPUT_FIELD, TextView CONJUGATION) {

        String userInput;

        if ( (userInput = INPUT_FIELD.getText().toString()).matches( "" ) ) {
            // If the user didn't enter anything yet do nothing
        } else {
            /* If the user entered something then
            *      hide the EditText and display
            *      the input as a TextView */
            INPUT_FIELD.setVisibility(View.GONE);
            CONJUGATION.setVisibility(View.VISIBLE);
            if ( userInput.equalsIgnoreCase( CONJUGATION.getText().toString() ) ) {
                // Set color to green
                CONJUGATION.setTextColor( Color.rgb( 0, 153, 0 ) );
            } else {
                CONJUGATION.setText(userInput);
                CONJUGATION.setTextColor(Color.RED);
            }
        }
    }
}