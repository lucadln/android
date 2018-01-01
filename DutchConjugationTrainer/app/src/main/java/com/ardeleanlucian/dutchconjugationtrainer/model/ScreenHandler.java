package com.ardeleanlucian.dutchconjugationtrainer.model;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by ardelean on 1/1/18.
 */

public class ScreenHandler {

    /**
     * @return the screen diagonal size in inches
     */
    public static double getScreenSize() {

        int widthPx;
        int heightPx;
        float ydpi;
        float xdpi;

        // Get numbers
        widthPx = Resources.getSystem().getDisplayMetrics().widthPixels;
        heightPx = Resources.getSystem().getDisplayMetrics().heightPixels;
        ydpi = Resources.getSystem().getDisplayMetrics().ydpi;
        xdpi = Resources.getSystem().getDisplayMetrics().xdpi;

        // Calculate the height and width in inches
        double height = (double) heightPx / (double) ydpi;
        double width = (double) widthPx / (double) xdpi;

        // The diagonal size can now be calculated as
        //   the square root of (height^2 + width^2)
        double x = Math.pow(width, 2);
        double y = Math.pow(height, 2);
        double screenInches = Math.sqrt(x + y);

        return screenInches;
    }
}
