package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class HeaderImage implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.Integer Id;

    private java.lang.Byte ImageData;

    private java.lang.String ImageName;

    private java.lang.Boolean IsActive;

    /**
     * Returns the value of property "Id". 
     * 
     */
    public java.lang.Integer getId() {
        return Id;
    }

    /**
     * Updates the value of property "Id". 
     */
    public void setId(java.lang.Integer Id) {
        this.Id = Id;
    }

    /**
     * Returns the value of property "ImageData". 
     * 
     */
    public java.lang.Byte getImageData() {
        return ImageData;
    }

    /**
     * Updates the value of property "ImageData". 
     */
    public void setImageData(java.lang.Byte ImageData) {
        this.ImageData = ImageData;
    }

    /**
     * Returns the value of property "ImageName". 
     * 
     */
    public java.lang.String getImageName() {
        return ImageName;
    }

    /**
     * Updates the value of property "ImageName". 
     */
    public void setImageName(java.lang.String ImageName) {
        this.ImageName = ImageName;
    }

    /**
     * Returns the value of property "IsActive". 
     * 
     */
    public java.lang.Boolean getIsActive() {
        return IsActive;
    }

    /**
     * Updates the value of property "IsActive". 
     */
    public void setIsActive(java.lang.Boolean IsActive) {
        this.IsActive = IsActive;
    }

}
