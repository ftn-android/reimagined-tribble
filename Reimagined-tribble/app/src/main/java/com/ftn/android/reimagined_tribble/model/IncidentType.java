package com.ftn.android.reimagined_tribble.model;

import com.orm.SugarRecord;

/**
 * Created by FilipF on 14.6.2016.
 */
public class IncidentType extends SugarRecord {

    private String name;
    private String icon;

    public IncidentType(){}

    public IncidentType(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "IncidentType{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
