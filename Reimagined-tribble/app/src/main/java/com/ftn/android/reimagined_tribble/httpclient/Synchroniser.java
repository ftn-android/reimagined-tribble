package com.ftn.android.reimagined_tribble.httpclient;

import android.content.SharedPreferences;
import android.util.Log;

import com.ftn.android.reimagined_tribble.httpclient.model.Location;
import com.ftn.android.reimagined_tribble.model.GasStation;
import com.ftn.android.reimagined_tribble.model.Incident;
import com.google.android.gms.maps.model.LatLng;

import java.util.Date;
import java.util.List;

/**
 * Created by Jozef on 7/3/2016.
 */
public class Synchroniser {

    IBackEnd serviceClient;
    private static final String TAG = "Synchroniser";
    public static final long PLUSHOUR = (long) 6;

    private SharedPreferences preferences;

    public Synchroniser(IBackEnd serviceClient, SharedPreferences preferences) {
        this.serviceClient = serviceClient;
        this.preferences = preferences;
    }

    void NewOrUpdateGasStation(com.ftn.android.reimagined_tribble.httpclient.model.Location loc) {
        List<GasStation> gasStationListDB = GasStation.find(GasStation.class, "uid = ?", loc.getUid());
        if (gasStationListDB.size() == 0) {
            GasStation gasStation = new GasStation(
                    loc.getName(),
                    loc.getDescription(),
                    loc.getStartDate(),
                    loc.getImageData(),
                    loc.getAuthor(),
                    loc.getLatitude(),
                    loc.getLongitude(),
                    true,
                    loc.getUid());
            gasStation.save();
            Log.d(TAG, "+1 GasStation");
        } else {
            GasStation gasStation = gasStationListDB.get(0);
            gasStation.setLongitude(loc.getLongitude());
            gasStation.setLatitude(loc.getLatitude());
            gasStation.setSynchronised(true);
            gasStation.setName(loc.getName());
            gasStation.setDescription(loc.getDescription());
            gasStation.setDate(loc.getStartDate());
            gasStation.setImage(loc.getImageData());
            gasStation.setUser(loc.getAuthor());

            gasStation.save();
            Log.d(TAG, "updated GasStation");
        }

    }

    void NewOrUpdateIncident(com.ftn.android.reimagined_tribble.httpclient.model.Location loc) {
        List<Incident> incidentListDB = Incident.find(Incident.class, "uid = ?", loc.getUid());
        if (incidentListDB.size() == 0) {
            Incident incident = new Incident(
                    loc.getName(),
                    loc.getDescription(),
                    true,
                    loc.getStartDate(),
                    loc.getEndDate(),
                    loc.getImageData(),
                    loc.getLongitude(),
                    loc.getLatitude(),
                    loc.getIncidentType(),
                    loc.getAuthor(),
                    loc.getConfirmedFrom(),
                    true
                    , loc.getUid());
            incident.save();
            Log.d(TAG, "+1 Incident");
        } else {
            LocationToIncident(loc,incidentListDB);
            Log.d(TAG, "updated Incident");
        }
    }

    public void FetchAllLocation(LatLng latLng, double radius) {
        try {
            com.ftn.android.reimagined_tribble.httpclient.model.Location[] locations =
                    serviceClient.getLocationsByRadius(
                            TypeFilter.ALL,
                            preferences.getBoolean("autoDownloadPictures", true),
                            latLng.longitude,
                            latLng.latitude,
                            radius);
            for (com.ftn.android.reimagined_tribble.httpclient.model.Location loc :
                    locations) {
                if (loc.isType()) { // Incident
                    NewOrUpdateIncident(loc);
                } else { // GasStation
                    NewOrUpdateGasStation(loc);
                }
            }

            Log.d(TAG, "Fetching new data from backend : succeeded");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "Fetching new data from backend : failed");
        }
    }

    public void UploadAllLocation() {
        for (Incident incident :
                Incident.find(Incident.class, "synchronised = ?", "0")) {
            Log.d(TAG, incident.getName() + " Incident uploading...");
            AddNewIncident(incident, false);
        }

        for (GasStation gasStation :
                GasStation.find(GasStation.class, "synchronised = ?", "0")) {
            Log.d(TAG, gasStation.getName() + " GasStation uploading...");
            AddNewGasStation(gasStation, false);
        }
    }

    public void DeleteExcessDataInDB() {
        try {
            List<Incident> incidentList = Incident.find(Incident.class, "end_date < ?", (new Date().getTime()) + "");
            for (Incident incident : incidentList) {
                incident.delete();
                incident.save();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddNewGasStation(GasStation gasStation) {
        AddNewGasStation(gasStation, true);
    }

    public void AddNewGasStation(GasStation gasStation, boolean saveFrist) {
        Date endDateInFuture = new Date(2020, 01, 01);
        Location location = new Location(0,
                gasStation.getLattitude(),
                gasStation.getLongittude(),
                gasStation.getName(),
                gasStation.getDescription(),
                gasStation.getDate(),
                endDateInFuture.getTime(),
                false,
                "",
                gasStation.getImage(),
                gasStation.getUser(),
                "",
                gasStation.getUID());
        try {
            if (saveFrist) {
                gasStation.save();
                Log.d("GasStation", location.toString());
            }
            Location locationService = serviceClient.addNewLocation(location);

            gasStation.setSynchronised(true);
            gasStation.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddNewIncident(Incident incident) {
        AddNewIncident(incident, true);
    }

    public void AddNewIncident(Incident incident, boolean saveFrist) {

        Location location = IncidentToLocation(incident);
        try {
            if (saveFrist) {
                incident.save();
                Log.d("SaveIncident", location.toString());
            }

            Location locationService = serviceClient.addNewLocation(location);

            incident.setSynchronised(true);
            incident.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Location IncidentToLocation(Incident incident)
    {
        if (incident == null)
            return null;

        return new Location(0,
                incident.getLatitude(),
                incident.getLongitude(),
                incident.getName(),
                incident.getDescription(),
                incident.getStartDate(),
                incident.getEndDate(),
                true,
                incident.getType(),
                incident.getImage(),
                incident.getAuthor(),
                incident.getConfirmedFrom(),
                incident.getUID());
    }

    public static Incident LocationToIncident(Location loc, List<Incident> incidentListDB)
    {
        Incident incident = incidentListDB.get(0);
        incident.setLongitude(loc.getLongitude());
        incident.setLatitude(loc.getLatitude());
        incident.setSynchronised(true);
        incident.setName(loc.getName());
        incident.setDescription(loc.getDescription());
        incident.setType(loc.getIncidentType());
        incident.setAuthor(loc.getAuthor());
        incident.setConfirmedFrom(loc.getConfirmedFrom());
        incident.setStartDate(loc.getStartDate());
        incident.setImage(loc.getImageData());
        incident.setEndDate(new Date(loc.getEndDate()).getTime());
        incident.save();

        return incident;
    }
}
