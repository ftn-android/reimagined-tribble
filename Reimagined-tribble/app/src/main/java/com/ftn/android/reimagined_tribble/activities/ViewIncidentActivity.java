package com.ftn.android.reimagined_tribble.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.ftn.android.reimagined_tribble.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by ftn/tim
 */
@EActivity(R.layout.view_incident)
public class ViewIncidentActivity extends AppCompatActivity {

    @ViewById(R.id.view_incident_container)
    LinearLayout container;

    @AfterViews
    protected void init(){
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("placeholder");

        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load("").error(R.drawable.ic_photo_placeholder).into(imageView);
    }

    @Click(R.id.fab_view_gas_station)
    protected void clickRefresh(){
        Snackbar.make(container, "snackbar", Snackbar.LENGTH_SHORT).show();
    }
}
