package com.ftn.android.reimagined_tribble.io.swagger.client.model;

import com.ftn.android.reimagined_tribble.io.swagger.client.model.HeaderImage;

import java.util.*;
import java.util.Date;

import io.swagger.annotations.*;

import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class Location {

    @SerializedName("Id")
    private Integer id = null;
    @SerializedName("Lattitude")
    private Double lattitude = null;
    @SerializedName("Longittude")
    private Double longittude = null;
    @SerializedName("Name")
    private String name = null;
    @SerializedName("Description")
    private String description = null;
    @SerializedName("StartDate")
    private Date startDate = null;
    @SerializedName("EndDate")
    private Date endDate = null;
    @SerializedName("Type")
    private Boolean type = null;
    @SerializedName("Images")
    private List<HeaderImage> images = null;

    /**
     **/
    @ApiModelProperty(value = "")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    public Double getLattitude() {
        return lattitude;
    }

    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    public Double getLongittude() {
        return longittude;
    }

    public void setLongittude(Double longittude) {
        this.longittude = longittude;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    public List<HeaderImage> getImages() {
        return images;
    }

    public void setImages(List<HeaderImage> images) {
        this.images = images;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return (id == null ? location.id == null : id.equals(location.id)) &&
                (lattitude == null ? location.lattitude == null : lattitude.equals(location.lattitude)) &&
                (longittude == null ? location.longittude == null : longittude.equals(location.longittude)) &&
                (name == null ? location.name == null : name.equals(location.name)) &&
                (description == null ? location.description == null : description.equals(location.description)) &&
                (startDate == null ? location.startDate == null : startDate.equals(location.startDate)) &&
                (endDate == null ? location.endDate == null : endDate.equals(location.endDate)) &&
                (type == null ? location.type == null : type.equals(location.type)) &&
                (images == null ? location.images == null : images.equals(location.images));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id == null ? 0 : id.hashCode());
        result = 31 * result + (lattitude == null ? 0 : lattitude.hashCode());
        result = 31 * result + (longittude == null ? 0 : longittude.hashCode());
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 31 * result + (description == null ? 0 : description.hashCode());
        result = 31 * result + (startDate == null ? 0 : startDate.hashCode());
        result = 31 * result + (endDate == null ? 0 : endDate.hashCode());
        result = 31 * result + (type == null ? 0 : type.hashCode());
        result = 31 * result + (images == null ? 0 : images.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Location {\n");

        sb.append("  id: ").append(id).append("\n");
        sb.append("  lattitude: ").append(lattitude).append("\n");
        sb.append("  longittude: ").append(longittude).append("\n");
        sb.append("  name: ").append(name).append("\n");
        sb.append("  description: ").append(description).append("\n");
        sb.append("  startDate: ").append(startDate).append("\n");
        sb.append("  endDate: ").append(endDate).append("\n");
        sb.append("  type: ").append(type).append("\n");
        sb.append("  images: ").append(images).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
