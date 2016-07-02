package com.ftn.android.reimagined_tribble.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.model.GasStation;
import com.google.android.gms.maps.model.LatLng;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

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
public class AddNewGasStationActivity extends AppCompatActivity{

    @StringRes(R.string.title_add_new_gas_station)
    String activityTitle;

    private SharedPreferences loginPreferences;

    @ViewById(R.id.input_name_of_gas_station)
    EditText _name;

    @ViewById(R.id.input_description_of_gas_station)
    EditText _description;

    @ViewById(R.id.backdrop)
    ImageView image;

    @Extra
    LatLng location;


    @AfterViews
    protected void init(){
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(activityTitle);

        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load("").error(R.drawable.ic_photo_placeholder).into(imageView);
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
    protected void clickOnAddGasStation() {
        String stationName = _name.getText().toString();
        String stationDesc = _description.getText().toString();
        String userName = loginPreferences.getString("username", "");

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        GasStation gasStation = new GasStation();
        gasStation.setUser(userName);
        gasStation.setDate(formattedDate);
        gasStation.setLattitude(location.latitude);
        gasStation.setLongittude(location.longitude);
        gasStation.setDescription(stationDesc);
        gasStation.setName(stationName);


        try {
            Drawable d = image.getDrawable();
            Bitmap bitmap = ((GlideBitmapDrawable) d).getBitmap();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] img = bos.toByteArray();
            gasStation.setImage(img);
            Log.d("GasStation", img.toString());
        }catch (Exception e)
        {
            e.printStackTrace();
            gasStation.setImage(new byte[] {0});
        }
        gasStation.save();


        finish();
    }

}
