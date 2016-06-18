package com.ftn.android.reimagined_tribble.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by FilipF on 11.6.2016.
 */
public class MySQLiteHelper  extends SQLiteOpenHelper {

    public static final String GAS_STATION = "gas_station";
    public static final String USER = "user";
    public static final String INCIDENT = "incident";
    public static final String LOCATION = "location";
    public static final String INCIDENT_TYPE = "incident_type";
    public static final String CONFIRMED = "confirmed";

    public static final String GAS_NAME = "name";
    public static final String GAS_DESC = "desciption";
    public static final String GAS_DATE = "dateAdded";
    public static final String GAS_IMAGE = "image";

    public static final String USER_NAME = "username";
    public static final String USER_PASS = "password";
    public static final String USER_EMAIL = "email";

    public static final String LOCATION_ADDRESS = "address";
    public static final String LOCATION_CITY = "city";
    public static final String LOCATION_COUNTRY = "country";

    public static final String TYPE_NAME= "name";
    public static final String TYPE_ICON = "icon";

    public static final String INCIDENT_NAME = "name";
    public static final String INCIDENT_DESC = "description";
    public static final String INCIDENT_ACTIVE = "active";
    public static final String INCIDENT_DATE = "dateCreated";
    public static final String INCIDENT_IMAGES = "images";

    private static final String DATABASE_NAME = "tribble.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + LOCATION + "(" + LOCATION_ADDRESS
            + " text not null, " + LOCATION_CITY
            + " text not null, " + LOCATION_COUNTRY
            + " text not null, primary key("+LOCATION_ADDRESS+","+LOCATION_CITY+","+LOCATION_COUNTRY+"));"
            + " create table "
            + GAS_STATION + "(" + GAS_NAME
            + " text primary key not null, " + GAS_DESC
            + " text not null, " + GAS_DATE
            + " date not null, " + GAS_IMAGE
            + " text, " + LOCATION_ADDRESS
            + " text primary key foreign key("+LOCATION_ADDRESS+") REFERENCES "+LOCATION+"("+LOCATION_ADDRESS+") not null, "
            +LOCATION_CITY+" integer primary key foreign key("+LOCATION_CITY+") REFERENCES "+LOCATION+"("+LOCATION_CITY+") not null, "
            +LOCATION_COUNTRY+" integer primary key foreign key("+LOCATION_COUNTRY+") REFERENCES "+LOCATION+"("+LOCATION_COUNTRY+") not null, "
            + USER_NAME + "text foreign key("+USER_NAME+") REFERENCES "+USER+"("+USER_NAME+"));"
            + " create table " + USER + "(" + USER_NAME
            + " text primary key not null, " + USER_PASS
            + " text not null, " + USER_EMAIL
            + " text not null, "+ LOCATION_ADDRESS
            + " text foreign key("+LOCATION_ADDRESS+") REFERENCES "+LOCATION+"("+LOCATION_ADDRESS+") not null, "
            +LOCATION_CITY+" integer foreign key("+LOCATION_CITY+") REFERENCES "+LOCATION+"("+LOCATION_CITY+") not null, "
            +LOCATION_COUNTRY+" integer foreign key("+LOCATION_COUNTRY+") REFERENCES "+LOCATION+"("+LOCATION_COUNTRY+") not null, "
            + " create table " + INCIDENT_TYPE + "(" + TYPE_NAME
            + " text primary key not null, " + TYPE_ICON
            + " text);" + " create table "
            + INCIDENT + "(" + INCIDENT_NAME
            + " string primary key, " + INCIDENT_DESC
            + " text not null, " + LOCATION_ADDRESS
            + " text primary key foreign key("+LOCATION_ADDRESS+") REFERENCES "+LOCATION+"("+LOCATION_ADDRESS+") not null, "
            +LOCATION_CITY+" integer primary key foreign key("+LOCATION_CITY+") REFERENCES "+LOCATION+"("+LOCATION_CITY+") not null, "
            +LOCATION_COUNTRY+" integer primary key foreign key("+LOCATION_COUNTRY+") REFERENCES "+LOCATION+"("+LOCATION_COUNTRY+") not null, "
            +TYPE_NAME+" integer primary key foreign key("+TYPE_NAME+") REFERENCES "+INCIDENT_TYPE+"("+TYPE_NAME+") not null, "
            + USER_NAME + "text primary key foreign key("+USER_NAME+") REFERENCES "+USER+"("+USER_NAME+") not null, "
            + INCIDENT_ACTIVE + " boolean not null, " + INCIDENT_DATE +
            " text not null, " + INCIDENT_IMAGES
            + " text);" + " create table "
            + CONFIRMED + "("
            + USER_NAME + " text primary key foreign key("+USER_NAME+") REFERENCES "+USER+"("+USER_NAME+") not null, "
            + INCIDENT_NAME + " text primary key foreign key("+INCIDENT_NAME+") REFERENCES "+INCIDENT+"("+INCIDENT_NAME+") not null);"
            ;


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + INCIDENT);
        onCreate(db);
    }

}
