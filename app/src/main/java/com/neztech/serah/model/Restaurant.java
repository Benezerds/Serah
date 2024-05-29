package com.neztech.serah.model;

import com.google.firebase.firestore.GeoPoint;

public class Restaurant {
    private String restaurantId;
//    private String categoryId; To be implemented next update
    private String restoName;
    private String restoImageUrl;
    private String description;
    private double rating;
    private GeoPoint location;

    private String openingHours;
    private String contactNumber;

    public Restaurant(){

    }

    public Restaurant(String restaurantId, int categoryId, String restoName, String restoImageUrl, String description, double rating, GeoPoint location, String openingHours, String contactNumber) {
        this.restaurantId = restaurantId;
        this.restoName = restoName;
        this.restoImageUrl = restoImageUrl;
        this.description = description;
        this.rating = rating;
        this.location = location;
        this.openingHours = openingHours;
        this.contactNumber = contactNumber;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestoName() {
        return restoName;
    }

    public void setRestoName(String restoName) {
        this.restoName = restoName;
    }

    public String getRestoImageUrl() {
        return restoImageUrl;
    }

    public void setRestoImageUrl(String restoImageUrl) {
        this.restoImageUrl = restoImageUrl;
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

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", restoName='" + restoName + '\'' +
                ", restoImageUrl='" + restoImageUrl + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", location=" + location +
                ", openingHours='" + openingHours + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
