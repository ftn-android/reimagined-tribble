package com.ftn.android.reimagined_tribble.activities;

import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.model.Incident;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ftn/tim
 */
@EActivity(R.layout.view_incident)
public class ViewIncidentActivity extends AppCompatActivity {

    @Extra
    Incident chosenIncident;

    @ViewById(R.id.view_incident_container)
    LinearLayout container;

    @ViewById(R.id.view_incident_name)
    TextView incidentName;

    @ViewById(R.id.view_incident_type)
    TextView incidentType;

    @ViewById(R.id.view_incident_street)
    TextView incidentStreet;

    @ViewById(R.id.view_incident_city)
    TextView incidentCity;

    @ViewById(R.id.view_incident_created_on)
    TextView incidentCreatedOn;

    @ViewById(R.id.view_incident_description)
    TextView incidentDescription;

    @AfterViews
    protected void init(){
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(chosenIncident.getName());

        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(chosenIncident.getImage()).error(R.drawable.ic_photo_placeholder).into(imageView);

        Geocoder geocoder;
        List<Address> addresses = new ArrayList<>();
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(chosenIncident.getLattitude(), chosenIncident.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = addresses.get(0).getAddressLine(0);
        String cityCountry = addresses.get(0).getLocality() + ", " + addresses.get(0).getCountryName();

        incidentName.setText(chosenIncident.getName());
        incidentCity.setText(cityCountry);
        incidentStreet.setText(address);
        incidentCreatedOn.setText(chosenIncident.getDate());
        incidentDescription.setText(chosenIncident.getDescription());
        incidentType.setText(chosenIncident.getType());
    }

    @Click(R.id.fab_view_gas_station)
    protected void clickRefresh(){
        //TODO Need to implement business logic here
        Snackbar.make(container, "snackbar", Snackbar.LENGTH_SHORT).show();
    }
}
