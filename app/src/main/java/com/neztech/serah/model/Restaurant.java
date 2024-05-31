package com.neztech.serah.model;

public class Restaurant {
    private String RestaurantId;
    private String category;
    private String restoName;
    private String restoImageUrl;
    private String description;
    private double rating;
    private String location;

    private String openingHours;
    private String contactNumber;

    public Restaurant(){

    }

    public Restaurant(String restaurantId, String category, String restoName, String restoImageUrl, String description, double rating, String location, String openingHours, String contactNumber) {
        RestaurantId = restaurantId;
        this.category = category;
        this.restoName = restoName;
        this.restoImageUrl = restoImageUrl;
        this.description = description;
        this.rating = rating;
        this.location = location;
        this.openingHours = openingHours;
        this.contactNumber = contactNumber;
    }

    public String getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.RestaurantId = restaurantId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
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
                "restaurantId=" + RestaurantId +
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
