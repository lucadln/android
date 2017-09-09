package com.ardeleanlucian.dutchconjugationtrainer;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by ardelean on 9/9/17.
 */

public class FeedbackDisplay {

    public static void dislaySnackbarFeedback(View view) {

        Snackbar snackbar = Snackbar.make(view, "This is a demo snackbar!", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
