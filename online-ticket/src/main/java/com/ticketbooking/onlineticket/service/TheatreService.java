package com.ticketbooking.onlineticket.service;

import com.ticketbooking.onlineticket.dto.TheatreRequest;
import com.ticketbooking.onlineticket.exception.ValidationException;
import com.ticketbooking.onlineticket.modal.Theatre;

import java.util.List;

public interface TheatreService {
    Theatre saveTheatre(TheatreRequest theatreRequest)throws ValidationException;

    List<Theatre> getAllTheatres() throws ValidationException;

    Theatre getTheatreByLocation(String location)throws ValidationException;

    Theatre updateTheatre(Theatre theatre,String name)throws ValidationException;

    void deleteTheatre(int id)throws ValidationException;

}
