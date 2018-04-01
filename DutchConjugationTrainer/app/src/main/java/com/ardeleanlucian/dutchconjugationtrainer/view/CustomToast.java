package com.ardeleanlucian.dutchconjugationtrainer.view;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ardeleanlucian.dutchconjugationtrainer.R;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by Ardelean Lucian on 3/5/2018.
 */

public class CustomToast {

    Toast toast;
    View layout;
    TextView messageText;

    /**
     * Constructor method
     * @param context
     * @param toastLayout
     * @param toastTextView
     */
    public CustomToast(Context context, int toastLayout, View toastTextView) {

        // Create a toast using the constructor paramters
        LayoutInflater inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(toastLayout, (ViewGroup) toastTextView);

        messageText = (TextView) layout.findViewById(R.id.message);
        messageText.setText("Dummy text");

        toast = new Toast(context);
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_LONG);
    }

    /**
     * Method to display the toast
     */
    public void show() {
        toast.show();
    }

    /**
     * Method to overwrite the default toast duration
     * @param duration
     */
    public void setToastDuration(int duration) {
        toast.setDuration(duration);
    }

    /**
     * Method to set the toast message
     * @param toastText
     */
    public void setToastMessage(String toastText) {
        messageText.setText(toastText);
    }
}
