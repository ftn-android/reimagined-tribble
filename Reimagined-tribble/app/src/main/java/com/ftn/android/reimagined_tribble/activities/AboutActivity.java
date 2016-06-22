package com.ftn.android.reimagined_tribble.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ftn.android.reimagined_tribble.R;

import org.androidannotations.annotations.EActivity;

/**
 * Created by szberko
 */
@EActivity
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
