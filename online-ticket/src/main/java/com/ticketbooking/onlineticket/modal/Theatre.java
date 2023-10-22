package com.ticketbooking.onlineticket.modal;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "theatres")
@Component
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(name = "name",nullable = false)
    private  String name;

    @Column(name = "location",nullable = false)
    private String location;

    @Column(name = "seats",nullable = false)
    private int seats;

    @Column(name = "movie",nullable = false)
    private String movieName;

    public Theatre() {
    }

    public Theatre(String name, String location, int seats,String movieName) {
        this.name = name;
        this.location = location;
        this.seats = seats;
        this.movieName=movieName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
