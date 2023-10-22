package com.ticketbooking.onlineticket.service;

import com.ticketbooking.onlineticket.dto.BookingRequest;
import com.ticketbooking.onlineticket.exception.ValidationException;
import com.ticketbooking.onlineticket.modal.Booking;
import com.ticketbooking.onlineticket.modal.Theatre;

import java.util.List;

public interface BookingService {
    Booking bookTicket(BookingRequest bookingRequest)throws ValidationException;

    List<Booking> getAllBooking() throws ValidationException;

    void cancelBooking(int bookid, int theatreid)throws ValidationException;
}
