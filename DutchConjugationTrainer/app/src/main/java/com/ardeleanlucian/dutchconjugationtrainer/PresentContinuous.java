package com.ardeleanlucian.dutchconjugationtrainer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ardelean Lucian on 7/30/2017.
 */

public class PresentContinuous extends Fragment {

    public static PresentContinuous newInstance() {
        PresentContinuous frag = new PresentContinuous();
        return frag;
    }

    public PresentContinuous() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_present_continuous, container, false);
        return rootView;
    }
}