package com.ftn.android.reimagined_tribble.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.model.GasStation;
import com.ftn.android.reimagined_tribble.model.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by ftn/tim
*/
@EActivity(R.layout.activity_add_new_gasstation)
@OptionsMenu(R.menu.add_new_menu)
public class AddNewGasStationActivity extends AppCompatActivity{

    @StringRes(R.string.title_add_new_gas_station)
    String activityTitle;

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    @ViewById(R.id.input_name_of_gas_station)
    EditText _name;
    @ViewById(R.id.input_description_of_gas_station)
    EditText _description;
    @ViewById(R.id.backdrop)
    ImageView image;


    @AfterViews
    protected void init(){
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(activityTitle);

        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load("").error(R.drawable.ic_photo_placeholder).into(imageView);

        Double l1 = Double.parseDouble(loginPreferences.getString("lat",""));
        Double l2 = Double.parseDouble(loginPreferences.getString("long",""));

        if(l1==0) {
            String email = loginPreferences.getString("username", "");
            User user = User.find(User.class, "email = ?",email).get(0);
            l1 = user.getLattitude();
            l2 = user.getLongitude();
        }

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(l1,l2, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address="";
            if(addresses.get(0).getSubThoroughfare()!=null) {
                address = addresses.get(0).getThoroughfare() + addresses.get(0).getSubThoroughfare();// If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            }
            else
            {
                address = addresses.get(0).getThoroughfare();
            }
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();

        }
        catch (IOException e) {
            e.printStackTrace();

        }

    }

    @Click(R.id.fab_add_new_gas_station_details)
    protected void startTheCamera(){
        EasyImage.openCamera(AddNewGasStationActivity.this, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                //Handle the image
                ImageView headerPhoto = (ImageView) findViewById(R.id.backdrop);
                Glide.with(AddNewGasStationActivity.this).load(imageFile).centerCrop().into(headerPhoto);
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                //Cancel handling, you might wanna remove taken photo if it was canceled
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(AddNewGasStationActivity.this);
                    if (photoFile != null) photoFile.delete();
                }
            }
        });
    }

    @OptionsItem(R.id.add)
    protected void clickOnAddGasStation(){
        String stationName = _name.getText().toString();
        String stationDesc = _description.getText().toString();
        String userName = loginPreferences.getString("username","");

        Double l1 = Double.parseDouble(loginPreferences.getString("lat",""));
        Double l2 = Double.parseDouble(loginPreferences.getString("long",""));

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        GasStation gasStation = new GasStation();
        gasStation.setUser(userName);
        gasStation.setDate(formattedDate);
        gasStation.setLattitude(l1);
        gasStation.setLongittude(l2);
        gasStation.setDescription(stationDesc);
        gasStation.setName(stationName);

        gasStation.save();
        finish();
       /*


*/
    }

}
