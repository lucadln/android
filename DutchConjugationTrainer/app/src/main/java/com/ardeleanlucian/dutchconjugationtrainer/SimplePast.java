package com.ardeleanlucian.dutchconjugationtrainer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ardelean Lucian on 7/30/2017.
 */

public class SimplePast extends Fragment {

    public static SimplePast newInstance() {
        SimplePast frag = new SimplePast();
        return frag;
    }

    public SimplePast() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3_simple_past, container, false);
        return rootView;
    }
}
