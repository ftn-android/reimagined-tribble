package com.ftn.android.reimagined_tribble.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.httpclient.IBackEnd;
import com.ftn.android.reimagined_tribble.httpclient.Synchroniser;
import com.ftn.android.reimagined_tribble.model.Incident;
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

@EActivity(R.layout.add_new_incident_activity)
@OptionsMenu(R.menu.add_new_menu)
public class AddNewIncidentActivity extends AppCompatActivity {

    @ViewById(R.id.incident_type)
    Spinner spinner;

    @StringRes(R.string.title_add_new_incident)
    String activityTitle;

    @ViewById(R.id.input_name_of_incident)
    EditText _name;

    @ViewById(R.id.input_description_of_incident)
    EditText _description;

    @ViewById(R.id.backdrop)
    ImageView image;

    @RestService
    IBackEnd serviceClient;

    @Extra
    LatLng location;

    private SharedPreferences loginPreferences;

    @AfterViews
    protected void init() {
        //Toolbar and back button setup
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(activityTitle);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        //Spinner setup
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.incident_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load("").error(R.drawable.ic_photo_placeholder).into(imageView);
    }

    @Click(R.id.fab_add_new_incident_details)
    protected void startTheCamera() {
        EasyImage.openCamera(AddNewIncidentActivity.this, 1);
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
                Glide.with(AddNewIncidentActivity.this).load(imageFile).centerCrop().into(headerPhoto);
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                //Cancel handling, you might wanna remove taken photo if it was canceled
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(AddNewIncidentActivity.this);
                    if (photoFile != null) photoFile.delete();
                }
            }
        });
    }

    @OptionsItem(R.id.add)
    protected void clickOnAddIncident() {
        Toast.makeText(this, "Add button", Toast.LENGTH_LONG).show();
        String incidentName = _name.getText().toString();
        String incidentDescription = _description.getText().toString();
        String incidentType = spinner.getSelectedItem().toString();
        String userName = loginPreferences.getString("username", "");

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        Incident incident = new Incident(incidentName,
                incidentDescription,
                true,
                formattedDate,
                new byte[]{0},
                location.longitude,
                location.latitude,
                incidentType,
                userName,
                "",
                true,
                java.util.UUID.randomUUID().toString());

        try {
            Drawable d = image.getDrawable();
            Bitmap bitmap = ((GlideBitmapDrawable) d).getBitmap();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] img = bos.toByteArray();
            incident.setImage(img);
            Log.d("Incident", img.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SaveIncident(incident);
        finish();
    }

    @Background
    void SaveIncident(Incident incident) {
        Synchroniser synchroniser = new Synchroniser(serviceClient);
        synchroniser.AddNewIncident(incident);
    }
}
