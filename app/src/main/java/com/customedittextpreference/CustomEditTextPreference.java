package com.customedittextpreference;

import android.content.Context;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by cyong on 23/04/16.
 */
public class CustomEditTextPreference extends EditTextPreference {

    private ImageButton clearButton;
    private TextView valueTextView;

    public CustomEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupChangeListener();
    }

    public CustomEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupChangeListener();
    }

    public CustomEditTextPreference(Context context) {
        super(context);
        setupChangeListener();
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);

        valueTextView = (TextView) view.findViewById(R.id.value_textview);
        clearButton = (ImageButton) view.findViewById(R.id.clear_button);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setText("");
                notifyChanged();

            }
        });

        String valueString = getText();
        Log.v(Settings.APP_NAME, "refreshValue(): valueString=" + valueString);
        valueTextView.setText(valueString);

        toggleClearButton(valueString);

    }


    private void toggleClearButton(String value)
    {
        if ((value==null) || (value.length()==0))
        {
            clearButton.setVisibility(View.GONE);
        }
        else
        {
            clearButton.setVisibility(View.VISIBLE);
        }


    }

    private void setupChangeListener()
    {
        setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                String newStringValue = (String) newValue;
                valueTextView.setText(newStringValue);

                toggleClearButton(newStringValue);
                notifyChanged();
                return true;
            }
        });
    }

}
