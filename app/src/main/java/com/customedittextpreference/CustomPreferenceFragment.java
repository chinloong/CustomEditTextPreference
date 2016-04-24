package com.customedittextpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.util.Log;

/**
 * Created by cyong on 23/04/16.
 */
public class CustomPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_list);

        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }



    @Override
    public void onResume() {
        super.onResume();
        Log.v(Settings.APP_NAME,"PreferenceCount="+getPreferenceScreen().getPreferenceCount());
        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); ++i) {
            Preference preference = getPreferenceScreen().getPreference(i);
            if (preference instanceof PreferenceGroup) {
                PreferenceGroup preferenceGroup = (PreferenceGroup) preference;
                for (int j = 0; j < preferenceGroup.getPreferenceCount(); ++j) {
                    Preference singlePref = preferenceGroup.getPreference(j);
                    updatePreference(singlePref, singlePref.getKey());
                }
            } else {
                updatePreference(preference, preference.getKey());
            }
        }

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        updatePreference(findPreference(key), key);
    }

    private void updatePreference(Preference preference, String key) {
        Log.v(Settings.APP_NAME, "updatePreference() key="+key);
        if (preference == null) return;

    }

}
