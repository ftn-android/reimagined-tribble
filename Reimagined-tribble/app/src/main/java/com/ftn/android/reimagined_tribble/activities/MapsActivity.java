package com.ftn.android.reimagined_tribble.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Criteria;
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
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;
import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.adapters.AddInfoWindowAdapter;
import com.ftn.android.reimagined_tribble.adapters.ViewInfoWindowAdapter;
import com.ftn.android.reimagined_tribble.httpclient.IBackEnd;
import com.ftn.android.reimagined_tribble.httpclient.Synchroniser;
import com.ftn.android.reimagined_tribble.model.Entity;
import com.ftn.android.reimagined_tribble.model.GasStation;
import com.ftn.android.reimagined_tribble.model.Incident;
import com.ftn.android.reimagined_tribble.model.User;
import com.ftn.android.reimagined_tribble.utils.ColorChangesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ftn/tim
 */
@EActivity(R.layout.activity_maps)
@OptionsMenu(R.menu.maps_main)
public class MapsActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnInfoWindowClickListener,
        NavigationView.OnNavigationItemSelectedListener,
        GoogleMap.OnMarkerClickListener {

    GoogleMap googleMap;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private static final String TAG = "MapsActivity";
    private Marker addNewMarker;
    private HashMap<String, Entity> markers;
    private MaterialDialog addNewDialog;
    private LatLng tappedLocation;
    private LocationManager locMan;

    @RestService
    IBackEnd serviceClient;

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

    //Unfortunately there is no other way to handle this.
    //Try to use android annotations but it can't handle navigation drawer
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mDrawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.show_just_incident_menu_drawer:
                googleMap.clear();
                loginPrefsEditor.putInt("showEntity", 1);
                loginPrefsEditor.apply();
                addMarkers();
                break;
            case R.id.show_just_gasstation_menu_drawer:
                googleMap.clear();
                loginPrefsEditor.putInt("showEntity", 2);
                loginPrefsEditor.apply();
                addMarkers();
                break;
            case R.id.show_gasstation_and_incident_menu_drawer:
                googleMap.clear();
                loginPrefsEditor.putInt("showEntity", 0);
                loginPrefsEditor.apply();
                addMarkers();
                break;
            case R.id.call_the_police_menu_drawer:
                launchPhoneActivity(policePhoneNumber);
                break;
            case R.id.call_the_ambulance_menu_drawer:
                launchPhoneActivity(ambulancePhoneNumber);
                break;
            case R.id.call_the_firefighters_menu_drawer:
                launchPhoneActivity(firefightersPhoneNumber);
                break;
            case R.id.refresh:
                refresh();
                break;
            case R.id.settings_menu_drawer:
                settings();
                break;
            case R.id.about_menu_drawer:
                about();
                break;
            case R.id.logout_menu_drawer:
                logout();
                break;
            case R.id.exit_menu_drawer:
                exit();
                break;
            default:
                return true;
        }
        return true;
    }

    @OptionsItem(R.id.refresh)
    void refresh() {
        Log.d(TAG, "Start fetching new data from backend");
        FetchNewInfoFromBackend();
    }

    @Background
    void FetchNewInfoFromBackend() {
        LatLng latLng;
        Location location = locMan.getLastKnownLocation(locMan.getBestProvider(new Criteria(), false));
        if (location != null) {
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
        } else {
            //TODO hard coded, until we find a solution how to force location
            latLng = new LatLng(45.23, 19.83);
            Log.d("FetchNewInfoFromBackend", "Last known location is null");
        }

        double radius = loginPreferences.getFloat("radius", 55);
        Log.d("radius km: ",radius+"");
        Synchroniser sync = new Synchroniser(serviceClient, loginPreferences);
        sync.FetchAllLocation(latLng, radius);
        sync.UploadAllLocation();
        sync.DeleteExcessDataInDB();

        addMarkers();
    }


    //Cannot access to android.R.id.home through AndroidAnnotations.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawers();
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion

    @Click(R.id.fab_add_new_incident)
    protected void clickNewIncident() {
        Location location = locMan.getLastKnownLocation(locMan.getBestProvider(new Criteria(), false));
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            AddNewIncidentActivity_.intent(this).location(latLng).start();
        } else {
            Log.d("clickNewIncident", "Last known location is null");
        }
    }

    @Click(R.id.fab_add_new_gas_station)
    protected void clickNewGasStation() {
        Location location = locMan.getLastKnownLocation(locMan.getBestProvider(new Criteria(), false));
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            AddNewGasStationActivity_.intent(this).location(latLng).start();
        } else {
            Log.d("clickNewGasStation", "Last known location is null");
        }
    }

    @OptionsItem(R.id.settings)
    void settings() {
        SettingsActivity_.intent(this).start();
    }

    private void launchPhoneActivity(String url) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + url));
        startActivity(intent);
    }

    @OptionsItem(R.id.about)
    void about() {
        AboutActivity_.intent(this).start();
    }

    @OptionsItem(R.id.logout)
    void logout() {

        loginPrefsEditor.clear();
        loginPrefsEditor.commit();
        LoginActivity_.intent(this).start();

    }

    @OptionsItem(R.id.exit)
    void exit() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @AfterViews
    protected void init() {
        markers = new HashMap<>();
        mapFragment.getMapAsync(this);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        locMan = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView usernameTextView = (TextView) header.findViewById(R.id.menu_drawer_name);

        usernameTextView.setText(loginPreferences.getString("username", "Username"));

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
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);

        googleMap.setOnInfoWindowClickListener(this);
        googleMap.setOnMarkerClickListener(this);

        Criteria crit = new Criteria();
        Location loc = locMan.getLastKnownLocation(locMan.getBestProvider(crit, false));

        if (loc != null) {

            String email = loginPreferences.getString("username", "");
            User user = User.find(User.class, "email = ?", email).get(0);

            user.setLattitude(loc.getLatitude());
            user.setLongitude(loc.getLongitude());
            user.save();

            CameraPosition camPos = new CameraPosition.Builder()
                    .target(new LatLng(loc.getLatitude(), loc.getLongitude()))
                    .zoom(12.8f)
                    .build();

            CameraUpdate camUpdate = CameraUpdateFactory.newCameraPosition(camPos);
            googleMap.moveCamera(camUpdate);

        }

        googleMap.setOnMapClickListener(this);

        addMarkers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int showEntity = loginPreferences.getInt("showEntity", 0);
        if (showEntity == 1) {
            navigationView.setCheckedItem(R.id.show_just_incident_menu_drawer);
        } else if (showEntity == 2) {
            navigationView.setCheckedItem(R.id.show_just_gasstation_menu_drawer);
        } else if (showEntity == 0) {
            navigationView.setCheckedItem(R.id.show_gasstation_and_incident_menu_drawer);
        }

        if (googleMap != null) {
            addMarkers();
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        // Creating a marker
        if (addNewMarker != null) {
            addNewMarker.remove();
        }

        MarkerOptions markerOptions = new MarkerOptions();

        googleMap.setInfoWindowAdapter(new AddInfoWindowAdapter(getLayoutInflater()));
        // Setting the position for the marker
        markerOptions.position(latLng);

        // Clears the previously touched position
//        googleMap.clear();

        // Animating to the touched position
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        // Placing a marker on the touched position
//        googleMap.addMarker(markerOptions);

        addNewMarker = googleMap.addMarker(markerOptions);

        tappedLocation = latLng;
        addNewMarker.showInfoWindow();
    }

    @UiThread
    void addMarkers() {
        int showEntity = loginPreferences.getInt("showEntity", 0);
        if (showEntity == 1) {
            addIncidentMarkers();
        } else if (showEntity == 2) {
            addGasStationMarkers();
        } else if (showEntity == 0) {
            addGasStationMarkers();
            addIncidentMarkers();
        }
    }

    private void addGasStationMarkers() {
        Iterator<GasStation> gasStationIterator = User.findAll(GasStation.class);
        while (gasStationIterator.hasNext()) {
            GasStation gs = gasStationIterator.next();

            Bitmap gasStationBitmap = ColorChangesUtil.changeColor(getResources(), "#33cc33", R.drawable.ic_local_gas_station_black_36dp);
            Marker gasstation = googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(gs.getLattitude(), gs.getLongittude()))
                    .title(gs.getName())
                    .snippet(gs.getDescription())
                    .icon(BitmapDescriptorFactory.fromBitmap(gasStationBitmap))
            );

            markers.put(gasstation.getId(), gs);
        }
    }

    private void addIncidentMarkers() {
        List<Incident> incidentList = Incident.find(Incident.class, "end_date >= ?", (new Date().getTime()) + "");
        for (Incident incident : incidentList) {

            Bitmap incidentBitmap = ColorChangesUtil.changeColor(getResources(), "#cc0000", R.drawable.ic_sms_failed_black_36dp);
            Marker incidentMarker = googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(incident.getLatitude(), incident.getLongitude()))
                    .title(incident.getName())
                    .snippet(incident.getDescription())
                    .icon(BitmapDescriptorFactory.fromBitmap(incidentBitmap))
            );

            markers.put(incidentMarker.getId(), incident);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (addNewMarker != null) {
            addNewMarker.remove();
        }
        googleMap.setInfoWindowAdapter(new ViewInfoWindowAdapter(getLayoutInflater(), markers.get(marker.getId())));
        googleMap.setOnInfoWindowClickListener(this);
        return false;
    }

    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (addNewMarker != null) {
            addNewMarker.remove();
        }

        Entity entity = markers.get(marker.getId());
        if (entity != null) {
            if (entity instanceof GasStation) {
                ViewGasStationActivity_.intent(this).chosenGasStation((GasStation) entity).start();
            } else if (entity instanceof Incident) {
                ViewIncidentActivity_.intent(this).chosenIncident((Incident) entity).start();
            }
        } else {

            final int ADD_NEW_GAS_STATION = 0;
            final int ADD_NEW_INCIDENT = 1;

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

            MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                    .title(R.string.add_new_dialog_title)
                    .adapter(adapter, new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                            MaterialSimpleListItem item = adapter.getItem(which);
                            if (item.getId() == ADD_NEW_GAS_STATION) {
                                AddNewGasStationActivity_.intent(MapsActivity.this).location(tappedLocation).start();
                            }
                            if (item.getId() == ADD_NEW_INCIDENT) {
                                AddNewIncidentActivity_.intent(MapsActivity.this).location(tappedLocation).start();
                            }
                            addNewDialog.dismiss();
                        }
                    });
            addNewDialog = builder.build();
            addNewDialog.show();
        }
    }
}
