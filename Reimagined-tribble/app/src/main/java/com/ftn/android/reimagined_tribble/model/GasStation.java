package com.ftn.android.reimagined_tribble.model;



import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by FilipF on 14.6.2016.
 */
public class GasStation extends SugarRecord implements Entity, Serializable{

    private String name;
    private String description;
    private String date;
    private byte[] image;
    private String user;
    private double latitude;
    private double longitude;

    private boolean synchronised;
    private String UID;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public GasStation(){

    }

    public GasStation(String name, String description, String date, byte[] image, String user, double latitude, double longitude, boolean synchronised, String UID) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.image = image;
        this.user = user;
        this.latitude = latitude;
        this.longitude = longitude;
        this.synchronised = synchronised;
        this.UID = UID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isSynchronised() {
        return synchronised;
    }

    public void setSynchronised(boolean synchronised) {
        this.synchronised = synchronised;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public  byte[] getImage() {
        return image;
    }

    public void setImage( byte[] image) {
        this.image = image;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getLattitude() {
        return latitude;
    }

    public void setLattitude(double lattitude) {
        this.latitude = lattitude;
    }

    public double getLongittude() {
        return longitude;
    }

    public void setLongittude(double longittude) {
        this.longitude = longittude;
    }

    @Override
    public String toString() {
        return "GasStation{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", image=" + Arrays.toString(image) +
                ", user='" + user + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", synchronised=" + synchronised +
                '}';
    }
}
