package com.ftn.android.reimagined_tribble.model;

import com.orm.SugarRecord;

/**
 * Created by FilipF on 14.6.2016.
 */
public class User extends SugarRecord{

    private String password;
    private String email;
    private double longitude;
    private double lattitude;
    private String userName;

    public User() {

    }

    public User(String userName, String password, double longitude, double lattitude, String email) {
        this.userName = userName;
        this.password = password;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", longitude=" + longitude +
                ", lattitude=" + lattitude +
                ", userName='" + userName + '\'' +
                '}';
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }
}
