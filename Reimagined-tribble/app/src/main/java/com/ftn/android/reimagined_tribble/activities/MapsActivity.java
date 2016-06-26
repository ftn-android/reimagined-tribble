package com.ftn.android.reimagined_tribble.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;
import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.adapters.AddInfoWindowAdapter;
import com.ftn.android.reimagined_tribble.model.User;
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
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@EActivity(R.layout.activity_maps)
@OptionsMenu(R.menu.maps_main)
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnInfoWindowClickListener, NavigationView.OnNavigationItemSelectedListener {

    GoogleMap googleMap;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private static final String TAG = "MapsActivity";

    @FragmentById(R.id.map)
    SupportMapFragment mapFragment;

    @StringRes(R.string.phone_number_police)
    String policePhoneNumber;

    @StringRes(R.string.phone_number_ambulance)
    String ambulancePhoneNumber;

    @StringRes(R.string.phone_number_firefighters)
    String firefightersPhoneNumber;

    //region NavigationDrawer
    @ViewById(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @ViewById(R.id.nav_view)
    NavigationView navigationView;

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        mDrawerLayout.closeDrawers();
        return true;
    }

    //Cannot access to android.R.id.home through AndroidAnnotations.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawers();
                }else{
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion

    @Click(R.id.fab_add_new_incident)
    protected void clickNewIncident(){
    }

    @Click(R.id.fab_add_new_gas_station)
    protected void clickNewGasStation(){
        AddNewGasStationActivity_.intent(this).start();
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

    @AfterViews
    protected void init(){
        mapFragment.getMapAsync(this);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);
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
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        this.googleMap = googleMap;

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

        com.ftn.android.reimagined_tribble.model.Location location = new com.ftn.android.reimagined_tribble.model.Location();

        if(loc != null) {

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
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

                location.setAddress(address);
                location.setCountry(country);
                location.setCity(city);

                String email = loginPreferences.getString("username", "");
                User user = User.find(User.class, "email = ?",email).get(0);
                user.setLocation(location);
                user.save();
                Log.d(TAG,address);

            }
            catch (IOException e) {
                e.printStackTrace();

            }

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

        final int ADD_NEW_GAS_STATION=0;
        final int ADD_NEW_INCIDENT=1;

        final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(this);
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .id(ADD_NEW_GAS_STATION)
                .content(R.string.add_new_gas_station_from_map)
                .icon(R.drawable.ic_gas_station_dialog_add_new)
                .backgroundColor(Color.WHITE)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .id(ADD_NEW_INCIDENT)
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
                        if(item.getId() == ADD_NEW_GAS_STATION){
                            AddNewGasStationActivity_.intent(MapsActivity.this).start();
                        }
                        if(item.getId() == ADD_NEW_INCIDENT){

                        }
                    }
                })
                .show();
    }
}
