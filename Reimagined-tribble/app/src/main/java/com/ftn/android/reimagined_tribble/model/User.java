package com.ftn.android.reimagined_tribble.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orm.SugarRecord;

/**
 * Created by FilipF on 14.6.2016.
 */
public class User extends SugarRecord{

    @JsonProperty("Email")
    private String email;

    @JsonProperty("UserName")
    private String userName;

    @JsonProperty("Password")
    private String password;

    @JsonProperty("Longitude")
    private Double longitude;

    @JsonProperty("Lattitude")
    private Double lattitude;

    @JsonProperty("Id")
    private Integer id;

    public User() {
        this.userName = "";
        this.password = "";
        this.longitude = new Double(0);
        this.lattitude = new Double(0);
        this.email = "";
        this.id = 0;
    }

    public User(String userName, String password, double longitude, double lattitude, String email) {
        this.userName = userName;
        this.password = password;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.email = email;
        this.id = 0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
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
