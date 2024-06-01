package com.neztech.serah.model;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;

public class User implements Serializable {
    private String uid;
    private String username;
    private String phoneNumber;
    private String location;
    private String full_name;
    private String email;
    private String created_time;

    public User() {

    }

    public User(String uid, String username, String phoneNumber, String location, String full_name, String email, String created_time) {
        this.uid = uid;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.full_name = full_name;
        this.email = email;
        this.created_time = created_time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public DocumentReference getDocumentReference() {
        // Construct the reference path
        String restaurantPath = "/User/" + getUid();
        return FirebaseFirestore.getInstance().document(restaurantPath);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", location='" + location + '\'' +
                ", full_name='" + full_name + '\'' +
                ", email='" + email + '\'' +
                ", created_time=" + created_time +
                '}';
    }
}
