package com.ardeleanlucian.dutchconjugationtrainer.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;

import com.ardeleanlucian.dutchconjugationtrainer.R;

/**
 * Created by ardelean on 10/19/17.
 */

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }

    @Override
    public boolean onSupportNavigateUp() {
        View view = findViewById(R.id.load_bar);
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                -600,                 // fromXDelta
                0,                 // toXDelta
                0,  // fromYDelta
                0);                // toYDelta
        animate.setDuration(400);
        animate.setFillAfter(true);
        view.startAnimation(animate);

        return super.onSupportNavigateUp();
    }
}
