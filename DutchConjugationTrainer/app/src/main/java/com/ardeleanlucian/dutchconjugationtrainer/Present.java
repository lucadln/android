package com.ardeleanlucian.dutchconjugationtrainer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ardelean Lucian on 7/30/2017.
 */

public class Present extends Fragment {

    public static Present newInstance() {
        Present frag = new Present();
        return frag;
    }

    public Present() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_present, container, false);
        return rootView;
    }
}
