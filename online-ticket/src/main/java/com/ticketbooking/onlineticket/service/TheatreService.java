package com.ticketbooking.onlineticket.service;

import com.ticketbooking.onlineticket.dto.TheatreRequest;
import com.ticketbooking.onlineticket.exception.ValidationException;
import com.ticketbooking.onlineticket.modal.Theatre;

public interface TheatreService {
    Theatre saveTheatre(TheatreRequest theatreRequest)throws ValidationException;

}
