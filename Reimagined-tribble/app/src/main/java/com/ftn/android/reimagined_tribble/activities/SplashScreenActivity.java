package com.ftn.android.reimagined_tribble.activities;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by szberko
 */

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginActivity_.intent(this).start();
        finish();
    }

}
