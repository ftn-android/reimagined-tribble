package com.ftn.android.reimagined_tribble.io.swagger.client.model;


import io.swagger.annotations.*;

import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class HeaderImage {

    @SerializedName("Id")
    private Integer id = null;
    @SerializedName("ImageData")
    private byte[] imageData = null;
    @SerializedName("ImageName")
    private String imageName = null;
    @SerializedName("IsActive")
    private Boolean isActive = null;

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
    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HeaderImage headerImage = (HeaderImage) o;
        return (id == null ? headerImage.id == null : id.equals(headerImage.id)) &&
                (imageData == null ? headerImage.imageData == null : imageData.equals(headerImage.imageData)) &&
                (imageName == null ? headerImage.imageName == null : imageName.equals(headerImage.imageName)) &&
                (isActive == null ? headerImage.isActive == null : isActive.equals(headerImage.isActive));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id == null ? 0 : id.hashCode());
        result = 31 * result + (imageData == null ? 0 : imageData.hashCode());
        result = 31 * result + (imageName == null ? 0 : imageName.hashCode());
        result = 31 * result + (isActive == null ? 0 : isActive.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class HeaderImage {\n");

        sb.append("  id: ").append(id).append("\n");
        sb.append("  imageData: ").append(imageData).append("\n");
        sb.append("  imageName: ").append(imageName).append("\n");
        sb.append("  isActive: ").append(isActive).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
