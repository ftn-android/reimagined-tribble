package com.ftn.android.reimagined_tribble.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ftn.android.reimagined_tribble.database.MySQLiteHelper;
import com.ftn.android.reimagined_tribble.model.Incident;
import com.ftn.android.reimagined_tribble.model.IncidentType;
import com.ftn.android.reimagined_tribble.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FilipF on 15.6.2016.
 */
public class IncidentTypeDao {

        private SQLiteDatabase database;
        private MySQLiteHelper dbHelper;

        private String[] allColumns = { MySQLiteHelper.TYPE_NAME,
                MySQLiteHelper.TYPE_ICON};

        public IncidentTypeDao(Context context) {
            dbHelper = new MySQLiteHelper(context);
        }

        public void open() throws SQLException {
            database = dbHelper.getWritableDatabase();
        }

        public void close() {
            dbHelper.close();
        }

        public IncidentType createType(String name,String icon) {
            ContentValues values = new ContentValues();
            values.put(MySQLiteHelper.TYPE_NAME, name);
            values.put(MySQLiteHelper.TYPE_ICON,icon);
            database.insert(MySQLiteHelper.INCIDENT_TYPE, null, values);
            Cursor cursor = database.query(MySQLiteHelper.USER,
                    allColumns, MySQLiteHelper.TYPE_NAME + " = " + name, null,
                    null, null, null);
            cursor.moveToFirst();
            IncidentType type = cursorToType(cursor);
            cursor.close();
            return type;
        }

        public void deleteType(IncidentType type) {
            String name = type.getName();
            System.out.println("Type deleted with name: " + name);
            database.delete(MySQLiteHelper.INCIDENT_TYPE, MySQLiteHelper.TYPE_NAME
                    + " = " + name, null);
        }

        public List<IncidentType> getAllTypes() {
            List<IncidentType> types = new ArrayList<IncidentType>();

            Cursor cursor = database.query(MySQLiteHelper.INCIDENT_TYPE,
                    allColumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                IncidentType type = cursorToType(cursor);
                types.add(type);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
            return types;
        }

        private IncidentType cursorToType(Cursor cursor) {
            IncidentType type = new IncidentType();
            type.setName(cursor.getString(0));
            type.setIcon(cursor.getString(1));
            return type;
        }
}