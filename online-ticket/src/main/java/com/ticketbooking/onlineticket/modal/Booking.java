package com.ticketbooking.onlineticket.modal;

import jakarta.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "Theatre Name",nullable = false)
    String theatreName;
    @Column(name = "Movie Name",nullable = false)
    String movieName;
    @Column(name = "Ticket",nullable = false)
    int quantity;

    public Booking() {
    }

    public Booking(String theatreName, String movieName, int quantity) {
        this.theatreName = theatreName;
        this.movieName = movieName;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
