package com.ardeleanlucian.dutchconjugationtrainer;

import android.widget.TextView;

/**
 * Created by Ardelean Lucian on 8/6/2017.
 */

public class TenseConjugationResult {

    private String infinitive = "unknown";
    private String translation = "unknown";
    private String ikVerb = "unknown";
    private String jijVerb = "unknown";
    private String hijVerb = "unknown";
    private String wijVerb = "unknown";
    private String jullieVerb = "unknown";
    private String zijVerb = "unknown";

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

    public void displayConjugations(TextView IK_VERB, TextView JIJ_VERB, TextView HIJ_VERB,
                                    TextView WIJ_VERB, TextView JULLIE_VERB, TextView ZIJ_VERB,
                                    Boolean showConjugation[]) {
        IK_VERB.setText(     getIkVerb()      );
        JIJ_VERB.setText(    getJijVerb()     );
        HIJ_VERB.setText(    getHijVerb()     );
        WIJ_VERB.setText(    getWijVerb()     );
        JULLIE_VERB.setText( getJullieVerb()  );
        ZIJ_VERB.setText(    getZijVerb()     );
    }

    public String getInfinitive() { return infinitive; }

    public String getTranslation() { return translation; }

    public String getIkVerb() {
        return ikVerb;
    }

    public String getJijVerb() {
        return jijVerb;
    }

    public String getHijVerb() {
        return hijVerb;
    }

    public String getWijVerb() {
        return wijVerb;
    }

    public String getJullieVerb() {
        return jullieVerb;
    }

    public String getZijVerb() {
        return zijVerb;
    }
}
