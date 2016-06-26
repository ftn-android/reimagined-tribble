package com.ftn.android.reimagined_tribble.model;



import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by FilipF on 14.6.2016.
 */
public class GasStation extends SugarRecord {

    private String name;
    private String description;
    private String date;
    private String image;
    private String user;
    private double lattitude;
    private double longittude;

    public GasStation(){

    }

    public GasStation(String name, String description, String date, String image, String user, double lattitude, double longittude) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.image = image;
        this.user = user;
        this.lattitude = lattitude;
        this.longittude = longittude;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongittude() {
        return longittude;
    }

    public void setLongittude(double longittude) {
        this.longittude = longittude;
    }

    @Override
    public String toString() {
        return "GasStation{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", image='" + image + '\'' +
                ", user='" + user + '\'' +
                ", lattitude=" + lattitude +
                ", longittude=" + longittude +
                '}';
    }
}
