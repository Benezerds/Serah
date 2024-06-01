package com.neztech.serah.model;

import java.io.Serializable;

public class Reservation implements Serializable {
    String ReservationId;
    Restaurant restaurant;
    String partySize;
    String reservationDate;
    String reservationStatus;
    User user;

    public Reservation(String reservationId, Restaurant restaurant, String partySize, String reservationDate, String reservationStatus, User user) {
        ReservationId = reservationId;
        this.restaurant = restaurant;
        this.partySize = partySize;
        this.reservationDate = reservationDate;
        this.reservationStatus = reservationStatus;
        this.user = user;
    }

    public String getReservationId() {
        return ReservationId;
    }

    public void setReservationId(String reservationId) {
        ReservationId = reservationId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getPartySize() {
        return partySize;
    }

    public void setPartySize(String partySize) {
        this.partySize = partySize;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "ReservationId='" + ReservationId + '\'' +
                ", restaurant=" + restaurant +
                ", party_size='" + partySize + '\'' +
                ", reservationDate='" + reservationDate + '\'' +
                ", reservation_status='" + reservationStatus + '\'' +
                ", user=" + user +
                '}';
    }
}
