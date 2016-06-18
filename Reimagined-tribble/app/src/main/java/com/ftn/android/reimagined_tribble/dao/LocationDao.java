package com.ftn.android.reimagined_tribble.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ftn.android.reimagined_tribble.database.MySQLiteHelper;
import com.ftn.android.reimagined_tribble.model.Location;
import com.ftn.android.reimagined_tribble.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FilipF on 14.6.2016.
 */
public class LocationDao {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] allColumns = { MySQLiteHelper.LOCATION_ADDRESS,
            MySQLiteHelper.LOCATION_CITY, MySQLiteHelper.LOCATION_COUNTRY};

    public LocationDao(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Location createLocation(String address, String city, String country) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.LOCATION_ADDRESS, address);
        values.put(MySQLiteHelper.LOCATION_CITY,city);
        values.put(MySQLiteHelper.LOCATION_COUNTRY,country);
        database.insert(MySQLiteHelper.LOCATION, null, values);
        Cursor cursor = database.query(MySQLiteHelper.LOCATION,
                allColumns, MySQLiteHelper.LOCATION_ADDRESS + " = " + address +
                        " AND "+ MySQLiteHelper.LOCATION_CITY + " = " + city +
                " AND "+ MySQLiteHelper.LOCATION_COUNTRY + " = " + country, null,
                null, null, null);
        cursor.moveToFirst();
        Location location = cursorToLocation(cursor);
        cursor.close();
        return location;
    }

    public void deleteLocation(Location location) {
        String address = location.getAddress();
        String city = location.getCity();
        String country = location.getCountry();
        System.out.println("Location deleted");
        database.delete(MySQLiteHelper.LOCATION, MySQLiteHelper.LOCATION_ADDRESS + " = " + address +
                " AND "+ MySQLiteHelper.LOCATION_CITY + " = " + city +
                " AND "+ MySQLiteHelper.LOCATION_COUNTRY + " = " + country, null);
    }

    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<Location>();

        Cursor cursor = database.query(MySQLiteHelper.LOCATION,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Location location = cursorToLocation(cursor);
            locations.add(location);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return locations;
    }

    private Location cursorToLocation(Cursor cursor) {
        Location location = new Location();
        location.setAddress(cursor.getString(0));
        location.setCity(cursor.getString(1));
        location.setCountry(cursor.getString(2));
        return location;
    }
}