package com.ardeleanlucian.dutchconjugationtrainer;

import android.view.View;
import android.widget.TextView;

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
    public void displayConjugations(TextView IK_VERB, TextView JIJ_VERB, TextView HIJ_VERB,
                                    TextView WIJ_VERB, TextView JULLIE_VERB, TextView ZIJ_VERB,
                                    int displayConjIndex) {

        IK_VERB.setText(     ikVerb      );
        JIJ_VERB.setText(    jijVerb     );
        HIJ_VERB.setText(    hijVerb     );
        WIJ_VERB.setText(    wijVerb     );
        JULLIE_VERB.setText( jullieVerb  );
        ZIJ_VERB.setText(    zijVerb     );

        decideVisibility(IK_VERB, JIJ_VERB, HIJ_VERB,
                WIJ_VERB, JULLIE_VERB, ZIJ_VERB,
                displayConjIndex);

    }

    // Decide which conjugations are visible considering the displayConjIndex
    public void decideVisibility(TextView IK_VERB, TextView JIJ_VERB, TextView HIJ_VERB,
                                 TextView WIJ_VERB, TextView JULLIE_VERB, TextView ZIJ_VERB,
                                 int displayConjIndex) {

        switch (displayConjIndex) {
            case 0:
                IK_VERB.setVisibility(View.INVISIBLE);
                JIJ_VERB.setVisibility(View.INVISIBLE);
                HIJ_VERB.setVisibility(View.INVISIBLE);
                WIJ_VERB.setVisibility(View.INVISIBLE);
                JULLIE_VERB.setVisibility(View.INVISIBLE);
                ZIJ_VERB.setVisibility(View.INVISIBLE);
                break;
            case 1:
                IK_VERB.setVisibility(View.VISIBLE);
                JIJ_VERB.setVisibility(View.INVISIBLE);
                HIJ_VERB.setVisibility(View.INVISIBLE);
                WIJ_VERB.setVisibility(View.INVISIBLE);
                JULLIE_VERB.setVisibility(View.INVISIBLE);
                ZIJ_VERB.setVisibility(View.INVISIBLE);
                break;
            case 2:
                IK_VERB.setVisibility(View.VISIBLE);
                JIJ_VERB.setVisibility(View.VISIBLE);
                HIJ_VERB.setVisibility(View.INVISIBLE);
                WIJ_VERB.setVisibility(View.INVISIBLE);
                JULLIE_VERB.setVisibility(View.INVISIBLE);
                ZIJ_VERB.setVisibility(View.INVISIBLE);
                break;
            case 3:
                IK_VERB.setVisibility(View.VISIBLE);
                JIJ_VERB.setVisibility(View.VISIBLE);
                HIJ_VERB.setVisibility(View.VISIBLE);
                WIJ_VERB.setVisibility(View.INVISIBLE);
                JULLIE_VERB.setVisibility(View.INVISIBLE);
                ZIJ_VERB.setVisibility(View.INVISIBLE);
                break;
            case 4:
                IK_VERB.setVisibility(View.VISIBLE);
                JIJ_VERB.setVisibility(View.VISIBLE);
                HIJ_VERB.setVisibility(View.VISIBLE);
                WIJ_VERB.setVisibility(View.VISIBLE);
                JULLIE_VERB.setVisibility(View.INVISIBLE);
                ZIJ_VERB.setVisibility(View.INVISIBLE);
                break;
            case 5:
                IK_VERB.setVisibility(View.VISIBLE);
                JIJ_VERB.setVisibility(View.VISIBLE);
                HIJ_VERB.setVisibility(View.VISIBLE);
                WIJ_VERB.setVisibility(View.VISIBLE);
                JULLIE_VERB.setVisibility(View.VISIBLE);
                ZIJ_VERB.setVisibility(View.INVISIBLE);
                break;
            case 6:
                IK_VERB.setVisibility(View.VISIBLE);
                JIJ_VERB.setVisibility(View.VISIBLE);
                HIJ_VERB.setVisibility(View.VISIBLE);
                WIJ_VERB.setVisibility(View.VISIBLE);
                JULLIE_VERB.setVisibility(View.VISIBLE);
                ZIJ_VERB.setVisibility(View.VISIBLE);
                break;
        }
    }
}