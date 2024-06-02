package com.neztech.serah.model;

import java.io.Serializable;

public class Review implements Serializable {

    Restaurant restaurant;
    String ReviewId;
    String comment;
    String date;
    int rating;
    User user;

    public Review(Restaurant restaurant, String reviewId, String comment, String date, int rating, User user) {
        this.restaurant = restaurant;
        ReviewId = reviewId;
        this.comment = comment;
        this.date = date;
        this.rating = rating;
        this.user = user;
    }

    public Review(String reviewId, String comment, String date, int rating, User user) {
        ReviewId = reviewId;
        this.comment = comment;
        this.date = date;
        this.rating = rating;
        this.user = user;
    }

    public Review() {

    }

    public String getReviewId() {
        return ReviewId;
    }

    public void setReviewId(String reviewId) {
        ReviewId = reviewId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Review{" +
                "restaurant=" + restaurant.toString() +
                ", ReviewId='" + ReviewId + '\'' +
                ", comment='" + comment + '\'' +
                ", date='" + date + '\'' +
                ", rating=" + rating +
                ", user=" + user.toString() +
                '}';
    }
}
