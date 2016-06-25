package com.ftn.android.reimagined_tribble.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;
import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.adapters.AddInfoWindowAdapter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.res.StringRes;

@EActivity(R.layout.activity_maps)
@OptionsMenu(R.menu.main)
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnInfoWindowClickListener {

    GoogleMap googleMap;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    @FragmentById(R.id.map)
    SupportMapFragment mapFragment;

    @StringRes(R.string.phone_number_police)
    String policePhoneNumber;

    @StringRes(R.string.phone_number_ambulance)
    String ambulancePhoneNumber;

    @StringRes(R.string.phone_number_firefighters)
    String firefightersPhoneNumber;

    @AfterViews
    protected void init(){
        mapFragment.getMapAsync(this);
    }

    @OptionsItem(R.id.settings)
    void settings(){
        SettingsActivity_.intent(this).start();
    }

    @OptionsItem(R.id.call_the_police)
    void callThePolice(){
        launchPhoneActivity(policePhoneNumber);
    }

    @OptionsItem(R.id.call_the_ambulance)
    void callTheAmbulance(){
        launchPhoneActivity(ambulancePhoneNumber);
    }

    @OptionsItem(R.id.call_the_firefighters)
    void callTheFireFighters(){
        launchPhoneActivity(firefightersPhoneNumber);
    }

    private void launchPhoneActivity(String url){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + url));
        startActivity(intent);
    }

    @OptionsItem(R.id.about)
    void about(){
        AboutActivity_.intent(this).start();
    }

    @OptionsItem(R.id.logout)
    void logout() {
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        loginPrefsEditor.clear();
        loginPrefsEditor.commit();
        LoginActivity_.intent(this).start();

    }

    @OptionsItem(R.id.exit)
    void exit(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        this.googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Place dot on current location
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        googleMap.setMyLocationEnabled(true);
        // Show Zoom buttons
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        LocationManager locMan = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria crit = new Criteria();
        Location loc = locMan.getLastKnownLocation(locMan.getBestProvider(crit, false));

        if(loc != null) {
            CameraPosition camPos = new CameraPosition.Builder()
                    .target(new LatLng(loc.getLatitude(), loc.getLongitude()))
                    .zoom(12.8f)
                    .build();

            CameraUpdate camUpdate = CameraUpdateFactory.newCameraPosition(camPos);
            googleMap.moveCamera(camUpdate);
        }

        googleMap.setOnMapClickListener(this);

    }


    @Override
    public void onMapClick(LatLng latLng) {
        // Creating a marker
        MarkerOptions markerOptions = new MarkerOptions();

        // Setting the position for the marker
        markerOptions.position(latLng);

        // Clears the previously touched position
        googleMap.clear();

        // Animating to the touched position
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        // Placing a marker on the touched position
//        googleMap.addMarker(markerOptions);

        googleMap.setInfoWindowAdapter(new AddInfoWindowAdapter(getLayoutInflater()));
        googleMap.setOnInfoWindowClickListener(this);

        Marker marker = googleMap.addMarker(markerOptions);
        marker.showInfoWindow();
    }

    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        googleMap.clear();

        final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(this);
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content(R.string.add_new_gas_station_from_map)
                .icon(R.drawable.ic_gas_station_dialog_add_new)
                .backgroundColor(Color.WHITE)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content(R.string.add_new_incident_from_map)
                .icon(R.drawable.ic_incident_dialog_add_new)
                .backgroundColor(Color.WHITE)
                .build());

        new MaterialDialog.Builder(this)
                .title(R.string.add_new_dialog_title)
                .adapter(adapter, new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        MaterialSimpleListItem item = adapter.getItem(which);
                        showToast(item.getContent().toString());
                    }
                })
                .show();
    }

    private Toast mToast;
    private void showToast(String message) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }

}
