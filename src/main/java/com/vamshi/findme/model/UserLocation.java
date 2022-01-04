package com.vamshi.findme.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@IdClass(UserLocationId.class)
public class UserLocation implements Serializable {
    @Id
    private String phone;
    @Id
    private String location;
    @Id
    private String saltKey;
    @Id
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSaltKey() {
        return saltKey;
    }

    public void setSaltKey(String saltKey) {
        this.saltKey = saltKey;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserLocation(){

    }

    public UserLocation(String phone, String location, String saltKey,String title) {
        this.phone = phone;
        this.location = location;
        this.saltKey = saltKey;
        this.title=title;
    }
}
