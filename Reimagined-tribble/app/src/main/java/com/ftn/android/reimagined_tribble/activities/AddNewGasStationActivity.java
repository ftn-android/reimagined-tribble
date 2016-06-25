package com.ftn.android.reimagined_tribble.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.ftn.android.reimagined_tribble.R;
import com.klinker.android.sliding.SlidingActivity;

import java.io.File;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * Created by szberko
 */

public class AddNewGasStationActivity extends SlidingActivity {

    public static final String ARG_USE_EXPANSION = "arg_use_expansion";
    public static final String ARG_EXPANSION_LEFT_OFFSET = "arg_left_offset";
    public static final String ARG_EXPANSION_TOP_OFFSET = "arg_top_offset";
    public static final String ARG_EXPANSION_VIEW_WIDTH = "arg_view_width";
    public static final String ARG_EXPANSION_VIEW_HEIGHT = "arg_view_height";

    private Bitmap bitmap;



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
                setHeaderImage(imageFile);

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

    private void setHeaderImage(File imageFile){
        bitmap = BitmapFactory.decodeFile(imageFile.getPath());
        setImage(bitmap);
    }



    @Override
    public void init(Bundle savedInstanceState) {

        setTitle("Activity Title");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setPrimaryColors(
                getResources().getColor(R.color.primary),
                getResources().getColor(R.color.primary_dark)
        );

        setContent(R.layout.activity_content);

        if(bitmap == null) {
            setImage(R.drawable.ic_photo_placeholder);
        }
        else {
            setImage(bitmap);
        }
        enableFullscreen();
        setFab(
                getResources().getColor(R.color.primary),
                R.drawable.ic_gas_station_fab_add_new,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        AboutActivity_.intent(AddNewGasStationActivity.this).start();
                        EasyImage.openCamera(AddNewGasStationActivity.this, 1);
                    }
                }
        );


//        Intent intent = getIntent();
//        if (intent.getBooleanExtra(ARG_USE_EXPANSION, false)) {
//            expandFromPoints(
//                    intent.getIntExtra(ARG_EXPANSION_LEFT_OFFSET, 0),
//                    intent.getIntExtra(ARG_EXPANSION_TOP_OFFSET, 0),
//                    intent.getIntExtra(ARG_EXPANSION_VIEW_WIDTH, 0),
//                    intent.getIntExtra(ARG_EXPANSION_VIEW_HEIGHT, 0)
//            );
//        }
    }
}
