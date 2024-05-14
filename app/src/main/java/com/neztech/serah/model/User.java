package com.neztech.serah.model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.GeoPoint;

public class User {
    private String username;
    private String phone_number;
    private GeoPoint location;
    private String last_name;
    private String first_name;
    private String email;
    private Timestamp created_time;

    public User(String username, String phone_number, GeoPoint location, String last_name, String first_name, String email, Timestamp created_time) {
        this.username = username;
        this.phone_number = phone_number;
        this.location = location;
        this.last_name = last_name;
        this.first_name = first_name;
        this.email = email;
        this.created_time = created_time;
    }

    //  Getter and Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Timestamp created_time) {
        this.created_time = created_time;
    }

}
