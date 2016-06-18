package com.ftn.android.reimagined_tribble.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ftn.android.reimagined_tribble.database.MySQLiteHelper;
import com.ftn.android.reimagined_tribble.model.Incident;
import com.ftn.android.reimagined_tribble.model.IncidentType;
import com.ftn.android.reimagined_tribble.model.Location;
import com.ftn.android.reimagined_tribble.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by FilipF on 15.6.2016.
 */
public class IncidentDao {

        private SQLiteDatabase database;
        private MySQLiteHelper dbHelper;

        private String[] allColumns = { MySQLiteHelper.INCIDENT_NAME,
                MySQLiteHelper.INCIDENT_DESC, MySQLiteHelper.LOCATION_ADDRESS,
        MySQLiteHelper.LOCATION_CITY, MySQLiteHelper.LOCATION_COUNTRY, MySQLiteHelper.TYPE_NAME,
        MySQLiteHelper.USER_NAME, MySQLiteHelper.INCIDENT_ACTIVE,MySQLiteHelper.INCIDENT_DATE,
        MySQLiteHelper.INCIDENT_IMAGES};

        public IncidentDao(Context context) {
        dbHelper = new MySQLiteHelper(context); }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Incident createIncident(String name, String description, Location location, String type,
                                   String username, Boolean active, String date, List<String> images) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.INCIDENT_NAME, name);
        values.put(MySQLiteHelper.INCIDENT_DESC, description);
        values.put(MySQLiteHelper.LOCATION_ADDRESS , location.getAddress());
        values.put(MySQLiteHelper.LOCATION_CITY, location.getCity());
        values.put(MySQLiteHelper.LOCATION_COUNTRY, location.getCountry());
        values.put(MySQLiteHelper.TYPE_NAME,type);
        values.put(MySQLiteHelper.USER_NAME,username);
        values.put(MySQLiteHelper.INCIDENT_ACTIVE, active);
        values.put(MySQLiteHelper.INCIDENT_DATE, date);

        String src = "";

        for(String s:images) {
            src += s + "/n";
        }
        values.put(MySQLiteHelper.INCIDENT_IMAGES, src);

        database.insert(MySQLiteHelper.INCIDENT, null, values);
        Cursor cursor = database.query(MySQLiteHelper.INCIDENT,
                allColumns, MySQLiteHelper.INCIDENT_NAME + " = " + name +
                        " AND "+ MySQLiteHelper.LOCATION_CITY + " = " + location.getCity() +
                        " AND "+ MySQLiteHelper.LOCATION_COUNTRY + " = " + location.getCountry() +
                        " AND "+ MySQLiteHelper.LOCATION_ADDRESS + " = " + location.getAddress() +
                        " AND "+ MySQLiteHelper.TYPE_NAME + " = " + type +
                        " AND "+ MySQLiteHelper.USER_NAME + " = " + username, null,
                null, null, null);
        cursor.moveToFirst();
        Incident incident= cursorToIncident(cursor);
        cursor.close();
        return incident;
    }

    public void confirmIncident(User user, Incident incident){

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.INCIDENT_NAME, incident.getName());
        values.put(MySQLiteHelper.USER_NAME, user.getUserName());

        database.insert(MySQLiteHelper.CONFIRMED, null, values);

    }

    public void unConfirmIncident(User user, Incident incident) {

        database.delete(MySQLiteHelper.CONFIRMED, MySQLiteHelper.INCIDENT_NAME + " = " + incident.getName() +
                " AND "+ MySQLiteHelper.USER_NAME + " = " + user.getUserName(), null);
    }



    public void deleteIncident(Incident incident) {
        System.out.println("Incident deleted with name: " + incident.getName());
        database.delete(MySQLiteHelper.INCIDENT, MySQLiteHelper.INCIDENT_NAME + " = " + incident.getName() +
                " AND "+ MySQLiteHelper.LOCATION_CITY + " = " + incident.getLocation().getCity() +
                " AND "+ MySQLiteHelper.LOCATION_COUNTRY + " = " + incident.getLocation().getCountry() +
                " AND "+ MySQLiteHelper.LOCATION_ADDRESS + " = " + incident.getLocation().getAddress() +
                " AND "+ MySQLiteHelper.TYPE_NAME + " = " + incident.getType()+
                " AND "+ MySQLiteHelper.USER_NAME + " = " + incident.getAuthor(), null);
    }

    public List<Incident> getAllIncidents() {
        List<Incident> incidents = new ArrayList<Incident>();

        Cursor cursor = database.query(MySQLiteHelper.INCIDENT,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Incident incident = cursorToIncident(cursor);
            incidents.add(incident);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return incidents;
    }

    private Incident cursorToIncident(Cursor cursor) {
        Incident incident = new Incident();
        incident.setName(cursor.getString(0));
        incident.setDescription(cursor.getString(1));
        incident.setLocation(new Location(cursor.getString(2),cursor.getString(3),cursor.getString(4)));
        incident.setType(cursor.getString(5));
        incident.setAuthor(cursor.getString(6));
        incident.setActive(Boolean.valueOf(cursor.getString(7)));

        String[] images = cursor.getString(8).split("/n");
        List<String> imagesList = new ArrayList<>();

        for (int i=0;i<images.length;i++)
        {
            imagesList.add(images[i]);
        }
        incident.setImages(imagesList);

        return incident;
    }
}
