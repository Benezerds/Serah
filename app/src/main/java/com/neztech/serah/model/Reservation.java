package com.neztech.serah.model;

import java.io.Serializable;

public class Reservation implements Serializable {
    String ReservationId;
    Restaurant restaurant;
    String party_size;
    String reservationDate;
    String reservation_status;
    User user;

    public Reservation(String reservationId, Restaurant restaurant, String party_size, String reservationDate, String reservation_status, User user) {
        ReservationId = reservationId;
        this.restaurant = restaurant;
        this.party_size = party_size;
        this.reservationDate = reservationDate;
        this.reservation_status = reservation_status;
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

    public String getParty_size() {
        return party_size;
    }

    public void setParty_size(String party_size) {
        this.party_size = party_size;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservation_status() {
        return reservation_status;
    }

    public void setReservation_status(String reservation_status) {
        this.reservation_status = reservation_status;
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
                ", party_size='" + party_size + '\'' +
                ", reservationDate='" + reservationDate + '\'' +
                ", reservation_status='" + reservation_status + '\'' +
                ", user=" + user +
                '}';
    }
}
