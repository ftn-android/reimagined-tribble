package com.ftn.android.reimagined_tribble.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.httpclient.IBackEnd;
import com.ftn.android.reimagined_tribble.httpclient.Synchroniser;
import com.ftn.android.reimagined_tribble.model.GasStation;
import com.google.android.gms.maps.model.LatLng;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;
import org.androidannotations.rest.spring.annotations.RestService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by ftn/tim
 */
@EActivity(R.layout.activity_add_new_gasstation)
@OptionsMenu(R.menu.add_new_menu)
public class AddNewGasStationActivity extends AppCompatActivity {

    @StringRes(R.string.title_add_new_gas_station)
    String activityTitle;

    @RestService
    IBackEnd serviceClient;

    private SharedPreferences loginPreferences;

    @ViewById(R.id.input_name_of_gas_station)
    EditText _name;

    @ViewById(R.id.input_description_of_gas_station)
    EditText _description;

    @ViewById(R.id.backdrop)
    ImageView image;

    @Extra
    LatLng location;

    Bundle bundle;


    @AfterViews
    protected void init() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        bundle = new Bundle();

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(activityTitle);

        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load("").error(R.drawable.ic_photo_placeholder).into(imageView);
    }

    @Click(R.id.fab_add_new_gas_station_details)
    protected void startTheCamera() {
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
                bundle.putSerializable("picture", imageFile);
                Glide.with(AddNewGasStationActivity.this).load(imageFile).centerCrop().into(image);
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
    protected void clickOnAddGasStation() {
        String stationName = _name.getText().toString();
        String stationDesc = _description.getText().toString();
        String userName = loginPreferences.getString("username", "");


        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        GasStation gasStation = new GasStation(stationName, stationDesc, formattedDate, new byte[]{0},
                userName, location.latitude, location.longitude, false, java.util.UUID.randomUUID().toString());

        try {
            Drawable d = image.getDrawable();
            Bitmap bitmap = ((GlideBitmapDrawable) d).getBitmap();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] img = bos.toByteArray();
            gasStation.setImage(img);
            Log.d("GasStation", img.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SaveGasStation(gasStation);
        finish();
    }

    @Background
    void SaveGasStation(GasStation gasStation) {
        Synchroniser synchroniser = new Synchroniser(serviceClient, loginPreferences);
        synchroniser.AddNewGasStation(gasStation);
    }

    //function for showing image from byte array
    public static void setImageViewWithByteArray(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putAll(bundle);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        File picture = (File) savedInstanceState.getSerializable("picture");
        bundle.putSerializable("picture", picture);
        Glide.with(AddNewGasStationActivity.this).load(picture).centerCrop().into(image);
    }

}
