package com.ftn.android.reimagined_tribble.activities;

import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.model.GasStation;

import org.androidannotations.annotations.AfterViews;
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
@EActivity(R.layout.view_gasstation)
public class ViewGasStationActivity extends AppCompatActivity {

    @ViewById(R.id.view_gas_station_container)
    LinearLayout container;

    @Extra
    GasStation chosenGasStation;

    @ViewById(R.id.view_gas_station_name)
    TextView gasStationName;

    @ViewById(R.id.view_gas_station_street)
    TextView gasStationStreetName;

    @ViewById(R.id.view_gas_station_city)
    TextView gasStationCityName;

    @ViewById(R.id.view_gas_station_created_on)
    TextView gasStationCreatedOn;

    @ViewById(R.id.view_gas_station_description)
    TextView gasStationDescription;

    @AfterViews
    protected void init(){
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(chosenGasStation.getName());

        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(chosenGasStation.getImage()).error(R.drawable.ic_photo_placeholder).into(imageView);

        Geocoder geocoder;
        List<Address> addresses = new ArrayList<>();
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(chosenGasStation.getLattitude(), chosenGasStation.getLongittude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = addresses.get(0).getAddressLine(0);
        String cityCountry = addresses.get(0).getLocality() + ", " + addresses.get(0).getCountryName();

        gasStationName.setText(chosenGasStation.getName());
        gasStationCityName.setText(cityCountry);
        gasStationStreetName.setText(address);
        gasStationCreatedOn.setText(chosenGasStation.getDate());
        gasStationDescription.setText(chosenGasStation.getDescription());
    }

}
