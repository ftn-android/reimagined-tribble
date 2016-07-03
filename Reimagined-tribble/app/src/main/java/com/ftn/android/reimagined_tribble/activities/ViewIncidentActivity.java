package com.ftn.android.reimagined_tribble.activities;

import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.httpclient.IBackEnd;
import com.ftn.android.reimagined_tribble.httpclient.Synchroniser;
import com.ftn.android.reimagined_tribble.httpclient.model.Location;
import com.ftn.android.reimagined_tribble.model.Incident;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by ftn/tim
 */
@EActivity(R.layout.view_incident)
public class ViewIncidentActivity extends AppCompatActivity {

    @Extra
    Incident chosenIncident;

    @RestService
    IBackEnd serviceClient;

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

    @ViewById(R.id.view_confirmed_by)
    TextView incidentConfirmedBy;

    @ViewById(R.id.view_valid_until)
    TextView incidentValidUntil;

    private SharedPreferences loginPreferences;

    @AfterViews
    protected void init() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(chosenIncident.getName());

        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(chosenIncident.getImage()).error(R.drawable.ic_photo_placeholder).into(imageView);

        updateChosenIncident(false);
    }

    @Click(R.id.fab_view_gas_station)
    protected void clickRefresh() {

        updateIncident(chosenIncident);
        Snackbar.make(container, "Incident is still there..", Snackbar.LENGTH_SHORT).show();
    }

    @UiThread
    void updateChosenIncident(boolean fetchFromDB)
    {
        if (fetchFromDB)
        {
            List<Incident> incidentListDB = Incident.find(Incident.class, "uid = ?", chosenIncident.getUID());
            if (incidentListDB.size() > 0) {
                chosenIncident = incidentListDB.get(0);
            }
        }

        Geocoder geocoder;
        List<Address> addresses = new ArrayList<>();
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(chosenIncident.getLatitude(), chosenIncident.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = addresses.get(0).getAddressLine(0);
        String cityCountry = addresses.get(0).getLocality() + ", " + addresses.get(0).getCountryName();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy, HH:mm");

        incidentName.setText(chosenIncident.getName());
        incidentCity.setText(cityCountry);
        incidentStreet.setText(address);
        incidentCreatedOn.setText(chosenIncident.getStartDate());
        incidentDescription.setText(chosenIncident.getDescription());
        incidentType.setText(chosenIncident.getType());
        incidentConfirmedBy.setText(chosenIncident.getConfirmedFrom());
        incidentValidUntil.setText(df.format(new Date(chosenIncident.getEndDate())));
    }

    @Background
    void updateIncident(Incident incident) {
        try {
            incident.setEndDate(new Date().getTime() + TimeUnit.HOURS.toMillis(Synchroniser.PLUSHOUR));
            String userName = loginPreferences.getString("username", "");
            incident.setConfirmedFrom(userName);
            //save changes locally
            incident.save();

            // service wont look into this, saving bandwidth
            incident.setImage(new byte[] {0});
            Location location = Synchroniser.IncidentToLocation(incident);
            Log.d("View Incident",location.toString());
            Location location1 = serviceClient.updateLocation(incident.getUID(), location);

            if (location1 != null) {
                List<Incident> incidentListDB = Incident.find(Incident.class, "uid = ?", location1.getUid());

                // TODO picture will be downloaded anyways if we refresh like this but thats fine for now
                chosenIncident = Synchroniser.LocationToIncident(location1, incidentListDB);

                updateChosenIncident(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
