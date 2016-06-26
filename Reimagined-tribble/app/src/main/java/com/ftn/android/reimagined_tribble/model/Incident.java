package com.ftn.android.reimagined_tribble.model;

import com.orm.SugarRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by FilipF on 14.6.2016.
 */
public class Incident extends SugarRecord {

    private String name;
    private String description;
    private boolean active;
    private String date;
    private List<String> images;
    private double longitude;
    private double lattitude;
    private String type;
    private String author;
    private List<String> confirmedFrom;

    public Incident() {}

    public Incident(String name, String description, boolean active, String date, List<String> images, double longitude, double lattitude, String type, String author, List<String> confirmedFrom) {
        this.name = name;
        this.description = description;
        this.active = active;
        this.date = date;
        this.images = images;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.type = type;
        this.author = author;
        this.confirmedFrom = confirmedFrom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
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

    public List<String> getConfirmedFrom() {
        return confirmedFrom;
    }

    public void setConfirmedFrom(List<String> confirmedFrom) {
        this.confirmedFrom = confirmedFrom;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", date=" + date +
                ", images=" + images +
                ", longitude=" + longitude +
                ", lattitude=" + lattitude +
                ", type='" + type + '\'' +
                ", author='" + author + '\'' +
                ", confirmedFrom=" + confirmedFrom +
                '}';
    }
}
