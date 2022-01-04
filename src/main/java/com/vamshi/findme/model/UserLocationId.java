package com.vamshi.findme.model;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserLocationId implements Serializable {

    private String phone;
    private String location;
    private String saltKey;
    private String title;

    public UserLocationId(){

    }
    public UserLocationId(String phone, String location, String saltKey,String title) {
        this.phone = phone;
        this.location = location;
        this.saltKey = saltKey;
        this.title=title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLocationId that = (UserLocationId) o;
        return Objects.equals(phone, that.phone) && Objects.equals(location, that.location) && Objects.equals(saltKey, that.saltKey) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, location, saltKey, title);
    }
}
