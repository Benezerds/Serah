package com.neztech.serah.model;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
    String ReviewId;
    String comment;
    Date date;
    int rating;
    User user;

    public Review(String reviewId, String comment, Date date, int rating, User user) {
        ReviewId = reviewId;
        this.comment = comment;
        this.date = date;
        this.rating = rating;
        this.user = user;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    @Override
    public String toString() {
        return "Review{" +
                "ReviewId='" + ReviewId + '\'' +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", rating=" + rating +
                ", user=" + user +
                '}';
    }
}
