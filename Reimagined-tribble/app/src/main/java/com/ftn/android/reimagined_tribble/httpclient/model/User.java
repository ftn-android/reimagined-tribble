package com.ftn.android.reimagined_tribble.httpclient.model;
/**
 * Created by Jozef on 6/30/2016.
 */
public class User {


    private String email = null;

    private String userName = null;

    private String password = null;

    private Double longittude = null;

    private Double lattitude = null;

    private Integer id = null;

    /**
     **/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     **/

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     **/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     **/

    public Double getLongittude() {
        return longittude;
    }

    public void setLongittude(Double longittude) {
        this.longittude = longittude;
    }

    /**
     **/

    public Double getLattitude() {
        return lattitude;
    }

    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }

    /**
     **/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return (email == null ? user.email == null : email.equals(user.email)) &&
                (userName == null ? user.userName == null : userName.equals(user.userName)) &&
                (password == null ? user.password == null : password.equals(user.password)) &&
                (longittude == null ? user.longittude == null : longittude.equals(user.longittude)) &&
                (lattitude == null ? user.lattitude == null : lattitude.equals(user.lattitude)) &&
                (id == null ? user.id == null : id.equals(user.id));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (email == null ? 0 : email.hashCode());
        result = 31 * result + (userName == null ? 0 : userName.hashCode());
        result = 31 * result + (password == null ? 0 : password.hashCode());
        result = 31 * result + (longittude == null ? 0 : longittude.hashCode());
        result = 31 * result + (lattitude == null ? 0 : lattitude.hashCode());
        result = 31 * result + (id == null ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");

        sb.append("  email: ").append(email).append("\n");
        sb.append("  userName: ").append(userName).append("\n");
        sb.append("  password: ").append(password).append("\n");
        sb.append("  longittude: ").append(longittude).append("\n");
        sb.append("  lattitude: ").append(lattitude).append("\n");
        sb.append("  id: ").append(id).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
