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
                                    TextView WIJ_VERB, TextView JULLIE_VERB, TextView ZIJ_VERB) {

        IK_VERB.setText(     ikVerb      );
        JIJ_VERB.setText(    jijVerb     );
        HIJ_VERB.setText(    hijVerb     );
        WIJ_VERB.setText(    wijVerb     );
        JULLIE_VERB.setText( jullieVerb  );
        ZIJ_VERB.setText(    zijVerb     );
    }
}