package com.ftn.android.reimagined_tribble.model;

/**
 * Created by FilipF on 14.6.2016.
 */
public class User {

    private String userName;
    private String password;
    private String email;
    private Location location;

    public User() {

    }

    public User(String userName, String password, Location location, String email) {
        this.userName = userName;
        this.password = password;
        this.location = location;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", location=" + location +
                '}';
    }
}