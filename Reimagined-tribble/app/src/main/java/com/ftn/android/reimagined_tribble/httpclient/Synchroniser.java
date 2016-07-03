package com.ftn.android.reimagined_tribble.httpclient;

import android.util.Log;

import com.ftn.android.reimagined_tribble.httpclient.model.Location;
import com.ftn.android.reimagined_tribble.model.GasStation;
import com.ftn.android.reimagined_tribble.model.Incident;
import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jozef on 7/3/2016.
 */
public class Synchroniser {

    IBackEnd serviceClient;
    private final String TAG = "Synchroniser";
    private final long PLUSHOUR = (long)6;

    public Synchroniser(IBackEnd serviceClient) {
        this.serviceClient = serviceClient;
    }

    void ConvertToGasStation(com.ftn.android.reimagined_tribble.httpclient.model.Location loc) {
        List<GasStation> gasStationListDB = GasStation.find(GasStation.class, "uid = ?", loc.getUid());
        if (gasStationListDB.size() == 0) {
            GasStation gasStation = new GasStation(
                    loc.getName(),
                    loc.getDescription(),
                    loc.getStartDate(),
                    loc.getImageData(),
                    "author ?",
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

            gasStation.save();
            Log.d(TAG, "updated GasStation");
        }

    }

    void ConvertToIncident(com.ftn.android.reimagined_tribble.httpclient.model.Location loc) {
        List<Incident> incidentListDB = Incident.find(Incident.class, "uid = ?", loc.getUid());
        if (incidentListDB.size() == 0) {
            Incident incident = new Incident(
                    loc.getName(),
                    loc.getDescription(),
                    true,
                    loc.getStartDate(),
                    loc.getImageData(),
                    loc.getLongitude(),
                    loc.getLatitude(),
                    loc.getAdditionalInfo(),
                    "author ?",
                    "confirmed ?",
                    true
                    , loc.getUid());
            incident.save();
            Log.d(TAG, "+1 Incident");
        } else {
            Incident incident = incidentListDB.get(0);
            incident.setLongitude(loc.getLongitude());
            incident.setLatitude(loc.getLatitude());
            incident.setSynchronised(true);
            incident.setName(loc.getName());
            incident.setDescription(loc.getDescription());
            incident.setType(loc.getAdditionalInfo());
            incident.setAuthor("author ??");
            incident.setConfirmedFrom("list of user ?");
            incident.setDate(loc.getStartDate());
            incident.setImage(loc.getImageData());

            incident.save();
            Log.d(TAG, "updated Incident");
        }
    }

    public void FetchAllLocation(LatLng latLng, double radius) {
        try {
            com.ftn.android.reimagined_tribble.httpclient.model.Location[] locations =
                    serviceClient.getLocationsByRadius(
                            TypeFilter.ALL,
                            latLng.longitude,
                            latLng.latitude,
                            radius);
            for (com.ftn.android.reimagined_tribble.httpclient.model.Location loc :
                    locations) {
                if (loc.isType()) { // Incident
                    ConvertToIncident(loc);
                } else { // GasStation
                    ConvertToGasStation(loc);
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

    public void AddNewGasStation(GasStation gasStation) {
        AddNewGasStation(gasStation, true);
    }

    public void AddNewGasStation(GasStation gasStation, boolean saveFrist) {
        Location location = new Location(0,
                gasStation.getLattitude(),
                gasStation.getLongittude(),
                gasStation.getName(),
                gasStation.getDescription(),
                gasStation.getDate(),
                "2020-01-01",
                false,
                "",
                gasStation.getImage(),
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

        Date oldDate = new Date(); // oldDate == current time
        Date endDate = new Date(oldDate.getTime() + TimeUnit.HOURS.toMillis(PLUSHOUR));

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedEndDate = df.format(endDate.getTime());

        Location location = new Location(0,
                incident.getLatitude(),
                incident.getLongitude(),
                incident.getName(),
                incident.getDescription(),
                incident.getDate(),
                formattedEndDate,
                true,
                incident.getType(),
                incident.getImage(),
                incident.getUID());
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
}
