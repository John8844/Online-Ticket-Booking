package com.ticketbooking.onlineticket.repository;

import com.ticketbooking.onlineticket.modal.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre,Integer> {
    Theatre findTheatreByLocation(String location);

    Theatre findByName(String name);

    Theatre findById(int id);

    Theatre deleteTheatreByName(String name);
}
