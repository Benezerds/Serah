package com.neztech.serah.model;

import com.google.firebase.Timestamp;

public class User {
    private String username;
    private String phoneNumber;
    private String location;
    private String full_name;
    private String email;
    private Timestamp created_time;

    public User() {

    }

    public User(String username, String phoneNumber, String location, String full_name, String email, Timestamp created_time) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.full_name = full_name;
        this.email = email;
        this.created_time = created_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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
