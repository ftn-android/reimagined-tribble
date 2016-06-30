package net.apispark.webapi.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class User implements java.io.Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private java.lang.String Email;

    private java.lang.String UserName;

    private java.lang.String Password;

    private java.lang.Double Longittude;

    private java.lang.Double Lattitude;

    private java.lang.Integer Id;

    /**
     * Returns the value of property "Email". 
     * 
     */
    public java.lang.String getEmail() {
        return Email;
    }

    /**
     * Updates the value of property "Email". 
     */
    public void setEmail(java.lang.String Email) {
        this.Email = Email;
    }

    /**
     * Returns the value of property "UserName". 
     * 
     */
    public java.lang.String getUserName() {
        return UserName;
    }

    /**
     * Updates the value of property "UserName". 
     */
    public void setUserName(java.lang.String UserName) {
        this.UserName = UserName;
    }

    /**
     * Returns the value of property "Password". 
     * 
     */
    public java.lang.String getPassword() {
        return Password;
    }

    /**
     * Updates the value of property "Password". 
     */
    public void setPassword(java.lang.String Password) {
        this.Password = Password;
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

}
