package com.ftn.android.reimagined_tribble.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ftn.android.reimagined_tribble.database.MySQLiteHelper;
import com.ftn.android.reimagined_tribble.model.GasStation;
import com.ftn.android.reimagined_tribble.model.Incident;
import com.ftn.android.reimagined_tribble.model.Location;
import com.ftn.android.reimagined_tribble.model.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FilipF on 17.6.2016.
 */
public class GasStationDao {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] allColumns = { MySQLiteHelper.GAS_NAME,
            MySQLiteHelper.GAS_DESC, MySQLiteHelper.GAS_DATE,
            MySQLiteHelper.GAS_IMAGE, MySQLiteHelper.LOCATION_ADDRESS, MySQLiteHelper.LOCATION_CITY,
            MySQLiteHelper.LOCATION_COUNTRY, MySQLiteHelper.USER_NAME};

    public GasStationDao(Context context) {
        dbHelper = new MySQLiteHelper(context); }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public GasStation createStation(String name, String description, String date, String image,
                                    Location location, String username) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.GAS_NAME, name);
        values.put(MySQLiteHelper.GAS_DESC, description);
        values.put(MySQLiteHelper.GAS_DATE , date);
        values.put(MySQLiteHelper.GAS_IMAGE, image);
        values.put(MySQLiteHelper.LOCATION_ADDRESS, location.getAddress());
        values.put(MySQLiteHelper.LOCATION_CITY, location.getCity());
        values.put(MySQLiteHelper.LOCATION_COUNTRY,location.getCountry());
        values.put(MySQLiteHelper.USER_NAME, username);

        database.insert(MySQLiteHelper.GAS_STATION, null, values);
        Cursor cursor = database.query(MySQLiteHelper.GAS_STATION,
                allColumns, MySQLiteHelper.GAS_NAME + " = " + name +
                        " AND "+ MySQLiteHelper.LOCATION_CITY + " = " + location.getCity() +
                        " AND "+ MySQLiteHelper.LOCATION_COUNTRY + " = " + location.getCountry() +
                        " AND "+ MySQLiteHelper.LOCATION_ADDRESS + " = " + location.getAddress() +
                        " AND "+ MySQLiteHelper.USER_NAME + " = " + username, null,
                null, null, null);
        cursor.moveToFirst();
        GasStation station = cursorToStation(cursor);
        cursor.close();
        return station;
    }


    public void deleteStation(GasStation station) {
        System.out.println("Station deleted with name: " + station.getName());
        database.delete(MySQLiteHelper.GAS_STATION, MySQLiteHelper.GAS_NAME + " = " + station.getName() +
                " AND "+ MySQLiteHelper.LOCATION_CITY + " = " + station.getLocation().getCity() +
                " AND "+ MySQLiteHelper.LOCATION_COUNTRY + " = " + station.getLocation().getCountry() +
                " AND "+ MySQLiteHelper.LOCATION_ADDRESS + " = " + station.getLocation().getAddress() +
                " AND "+ MySQLiteHelper.USER_NAME + " = " + station.getUser(), null);
    }

    public List<GasStation> getAllStations() {
        List<GasStation> stations = new ArrayList<GasStation>();

        Cursor cursor = database.query(MySQLiteHelper.GAS_STATION,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            GasStation station = cursorToStation(cursor);
            stations.add(station);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return stations;
    }

    private GasStation cursorToStation(Cursor cursor) {
        GasStation station = new GasStation();
        station.setName(cursor.getString(0));
        station.setDescription(cursor.getString(1));
        station.setDate(Date.valueOf(cursor.getString(2)));
        station.setImage(cursor.getString(3));
        station.setLocation(new Location(cursor.getString(4),cursor.getString(5),cursor.getString(6)));
        station.setUser(cursor.getString(7));

        return station;
    }
}
