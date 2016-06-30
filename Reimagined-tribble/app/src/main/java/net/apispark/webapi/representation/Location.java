package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Location implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.Integer Id;

    private java.lang.Double Lattitude;

    private java.lang.Double Longittude;

    private java.lang.String Name;

    private java.lang.String Description;

    private java.lang.String StartDate;

    private java.lang.String EndDate;

    private java.lang.Boolean Type;

    private java.util.List<net.apispark.webapi.representation.HeaderImage> Images = new java.util.ArrayList<net.apispark.webapi.representation.HeaderImage>();

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
     * Returns the value of property "Lattitude". 
     * 
     */
    public java.lang.Double getLattitude() {
        return Lattitude;
    }

    /**
     * Updates the value of property "Lattitude". 
     */
    public void setLattitude(java.lang.Double Lattitude) {
        this.Lattitude = Lattitude;
    }

    /**
     * Returns the value of property "Longittude". 
     * 
     */
    public java.lang.Double getLongittude() {
        return Longittude;
    }

    /**
     * Updates the value of property "Longittude". 
     */
    public void setLongittude(java.lang.Double Longittude) {
        this.Longittude = Longittude;
    }

    /**
     * Returns the value of property "Name". 
     * 
     */
    public java.lang.String getName() {
        return Name;
    }

    /**
     * Updates the value of property "Name". 
     */
    public void setName(java.lang.String Name) {
        this.Name = Name;
    }

    /**
     * Returns the value of property "Description". 
     * 
     */
    public java.lang.String getDescription() {
        return Description;
    }

    /**
     * Updates the value of property "Description". 
     */
    public void setDescription(java.lang.String Description) {
        this.Description = Description;
    }

    /**
     * Returns the value of property "StartDate". 
     * 
     */
    public java.lang.String getStartDate() {
        return StartDate;
    }

    /**
     * Updates the value of property "StartDate". 
     */
    public void setStartDate(java.lang.String StartDate) {
        this.StartDate = StartDate;
    }

    /**
     * Returns the value of property "EndDate". 
     * 
     */
    public java.lang.String getEndDate() {
        return EndDate;
    }

    /**
     * Updates the value of property "EndDate". 
     */
    public void setEndDate(java.lang.String EndDate) {
        this.EndDate = EndDate;
    }

    /**
     * Returns the value of property "Type". 
     * 
     */
    public java.lang.Boolean getType() {
        return Type;
    }

    /**
     * Updates the value of property "Type". 
     */
    public void setType(java.lang.Boolean Type) {
        this.Type = Type;
    }

    /**
     * Returns the value of property "Images". 
     * 
     */
    public java.util.List<net.apispark.webapi.representation.HeaderImage> getImages() {
        return Images;
    }

    /**
     * Updates the value of property "Images". 
     */
    public void setImages(java.util.List<net.apispark.webapi.representation.HeaderImage> Images) {
        this.Images = Images;
    }

}
