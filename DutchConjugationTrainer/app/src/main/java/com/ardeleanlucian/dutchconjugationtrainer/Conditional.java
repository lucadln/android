package com.ardeleanlucian.dutchconjugationtrainer;

import android.service.notification.Condition;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ardelean Lucian on 7/30/2017.
 */

public class Conditional extends Fragment {

    public static Conditional newInstance() {
        Conditional frag = new Conditional();
        return frag;
    }

    public Conditional() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab5_conditional, container, false);
        return rootView;
    }
}
