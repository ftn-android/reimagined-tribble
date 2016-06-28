package com.ftn.android.reimagined_tribble.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ftn.android.reimagined_tribble.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by ftn/tim
 */

@EActivity(R.layout.add_new_incident_activity)
public class AddNewIncidentActivity extends AppCompatActivity{

    @AfterViews
    protected void init(){
        Spinner spinner = (Spinner) findViewById(R.id.incident_type);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.incident_types, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}
