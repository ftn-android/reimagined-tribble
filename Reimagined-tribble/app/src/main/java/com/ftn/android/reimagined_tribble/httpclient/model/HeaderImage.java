package com.ftn.android.reimagined_tribble.httpclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Created by Jozef on 7/2/2016.
 */
public class HeaderImage {
    @JsonProperty("Id")
    private int id;
    @JsonProperty("ImageData")
    private byte[] imageData;
    @JsonProperty("ImageName")
    private String imageName;
    @JsonProperty("IsActive")
    private boolean isActive;

    public HeaderImage() {
    }

    public HeaderImage(int id, byte[] imageData, String imageName, boolean isActive) {
        this.id = id;
        this.imageData = imageData;
        this.imageName = imageName;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "HeaderImage{" +
                "id=" + id +
                ", imageData=" + Arrays.toString(imageData) +
                ", imageName='" + imageName + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
