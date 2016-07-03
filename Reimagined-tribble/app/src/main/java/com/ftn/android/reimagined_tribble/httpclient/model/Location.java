package com.ftn.android.reimagined_tribble.httpclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Created by Jozef on 7/2/2016.
 */
public class Location {

    @JsonProperty("Id")
    private int id;
    @JsonProperty("Latitude")
    private double latitude;
    @JsonProperty("Longitude")
    private double longitude;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("StartDate")
    private String startDate;
    @JsonProperty("EndDate")
    private long endDate;
    @JsonProperty("Type")
    private boolean type;
    @JsonProperty("IncidentType")
    private String incidentType;
    @JsonProperty("ImageData")
    private byte[] imageData;
    @JsonProperty("Author")
    private String author;
    @JsonProperty("ConfirmedFrom")
    private String confirmedFrom;
    @JsonProperty("UID")
    private String uid;

    public Location(int id, double latitude, double longitude, String name, String description, String startDate, long endDate, boolean type, String incidentType, byte[] imageData, String author, String confirmedFrom, String uid) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.incidentType = incidentType;
        this.imageData = imageData;
        this.author = author;
        this.confirmedFrom = confirmedFrom;
        this.uid = uid;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
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

    public void setConfirmedFrom(String confirmedFrom) {
        this.confirmedFrom = confirmedFrom;
    }

    public Location() {
    }

    public String getUid() {
        return uid;
    }

    public String isUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLattitude() {
        return latitude;
    }

    public void setLattitude(double lattitude) {
        this.latitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate=" + endDate +
                ", type=" + type +
                ", incidentType='" + incidentType + '\'' +
                ", imageData=" + Arrays.toString(imageData) +
                ", author='" + author + '\'' +
                ", confirmedFrom='" + confirmedFrom + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
