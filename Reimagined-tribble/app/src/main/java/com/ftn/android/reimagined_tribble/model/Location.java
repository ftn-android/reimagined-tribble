package com.ftn.android.reimagined_tribble.model;

/**
 * Created by FilipF on 14.6.2016.
 */
public class Location {

    private String address;
    private String city;
    private String country;

    public Location() {

    }

    public Location(String city, String address, String country) {
        this.city = city;
        this.address = address;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Location{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
