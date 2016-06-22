package com.ftn.android.reimagined_tribble.activities;

import android.os.Bundle;

import com.ftn.android.reimagined_tribble.R;

import org.androidannotations.annotations.EActivity;

/**
 * Created by szberko
 */

@EActivity
public class SettingsActivity extends AppCompatPreferenceActivity {

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
