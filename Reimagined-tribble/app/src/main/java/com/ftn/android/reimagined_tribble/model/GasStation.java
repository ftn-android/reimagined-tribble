package com.ftn.android.reimagined_tribble.model;



import java.util.Date;

/**
 * Created by FilipF on 14.6.2016.
 */
public class GasStation {

    private String name;
    private String description;
    private Date date;
    private String image;
    private Location location;
    private String user;

    public GasStation(){

    }

    public GasStation(String name, String description, Date date, String image, Location location,String user) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.image = image;
        this.location = location;
        this.user = user;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "GasStation{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", image='" + image + '\'' +
                ", location=" + location +
                ", user=" + user +
                '}';
    }
}
