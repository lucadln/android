package com.ardeleanlucian.dutchconjugationtrainer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ardelean Lucian on 7/30/2017.
 */

public class Future extends Fragment {

    public static Future newInstance() {
        Future frag = new Future();
        return frag;
    }

    public Future() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab7_future, container, false);
        return rootView;
    }
}