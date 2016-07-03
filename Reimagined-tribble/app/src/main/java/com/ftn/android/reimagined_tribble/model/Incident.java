package com.ftn.android.reimagined_tribble.model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by FilipF on 14.6.2016.
 */
public class Incident extends SugarRecord implements Entity, Serializable {

    private String name;
    private String description;
    private boolean active;
    private String date;
    private byte[] image;
    private double longitude;
    private double latitude;
    private String type;
    private String author;
    private String confirmedFrom;
    private boolean synchronised;
    private String UID;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public Incident() {
        //this.confirmedFrom = new ArrayList<>();
    }

    public Incident(String name, String description, boolean active, String date, byte[] image, double longitude, double latitude, String type, String author, String confirmedFrom, boolean synchronised, String UID) {
        this.name = name;
        this.description = description;
        this.active = active;
        this.date = date;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
        this.type = type;
        this.author = author;
        this.confirmedFrom = confirmedFrom;
        this.synchronised = synchronised;
        this.UID = UID;
    }

    public void setConfirmedFrom(String confirmedFrom) {
        this.confirmedFrom = confirmedFrom;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getConfirmedFrom() {
        return confirmedFrom;
    }

    /*public void addConfirmedFrom(String confirmFrom){
        this.confirmedFrom.add(confirmFrom);
    }*/

    @Override
    public String toString() {
        return "Incident{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", date='" + date + '\'' +
                ", image=" + Arrays.toString(image) +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", type='" + type + '\'' +
                ", author='" + author + '\'' +
                ", confirmedFrom=" + confirmedFrom +
                ", synchronised=" + synchronised +
                '}';
    }
}
