package com.ftn.android.reimagined_tribble.dao;

import android.database.sqlite.SQLiteDatabase;
import com.ftn.android.reimagined_tribble.database.MySQLiteHelper;
import com.ftn.android.reimagined_tribble.model.User;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
/**
 * Created by FilipF on 14.6.2016.
 */
public class UserDao {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] allColumns = { MySQLiteHelper.USER_NAME,
            MySQLiteHelper.USER_PASS, MySQLiteHelper.USER_EMAIL};

    public UserDao(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public User createUser(String username,String password, String email) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.USER_NAME, username);
        values.put(MySQLiteHelper.USER_PASS,password);
        values.put(MySQLiteHelper.USER_EMAIL,email);
        database.insert(MySQLiteHelper.USER, null, values);
        Cursor cursor = database.query(MySQLiteHelper.USER,
                allColumns, MySQLiteHelper.USER_NAME + " = " + username, null,
                null, null, null);
        cursor.moveToFirst();
        User user = cursorToUser(cursor);
        cursor.close();
        return user;
    }

    public void deleteUser(User user) {
        String username = user.getUserName();
        System.out.println("User deleted with username: " + username);
        database.delete(MySQLiteHelper.USER, MySQLiteHelper.USER_NAME
                + " = " + username, null);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        Cursor cursor = database.query(MySQLiteHelper.USER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return users;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setUserName(cursor.getString(0));
        user.setPassword(cursor.getString(1));
        user.setEmail(cursor.getString(2));
        return user;
    }
}


