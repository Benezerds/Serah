package com.neztech.serah.model;

import com.google.firebase.firestore.GeoPoint;

public class Restaurant {
    private int restaurantId;
    private int categoryId;
    private String name;
    private String description;
    private double rating;
    private GeoPoint location;
    private String openingHours;
    private String contactNumber;

    public Restaurant(int restaurantId, int categoryId, String name, String description, double rating, GeoPoint location, String openingHours, String contactNumber) {
        this.restaurantId = restaurantId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.location = location;
        this.openingHours = openingHours;
        this.contactNumber = contactNumber;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


}
