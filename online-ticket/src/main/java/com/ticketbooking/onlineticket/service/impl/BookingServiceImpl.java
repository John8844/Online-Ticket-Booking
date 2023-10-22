package com.ticketbooking.onlineticket.service.impl;

import com.ticketbooking.onlineticket.dto.BookingRequest;
import com.ticketbooking.onlineticket.exception.ValidationException;
import com.ticketbooking.onlineticket.modal.Booking;
import com.ticketbooking.onlineticket.modal.Theatre;
import com.ticketbooking.onlineticket.repository.BookingRepository;
import com.ticketbooking.onlineticket.repository.TheatreRepository;
import com.ticketbooking.onlineticket.service.BookingService;
import com.ticketbooking.onlineticket.service.TheatreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TheatreServiceImpl theatreServiceImpl;

    @Autowired
    private TheatreRepository theatreRepository;

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Override
    public Booking bookTicket(BookingRequest bookingRequest) throws ValidationException {
        logger.info("{}: Function start: BookingServiceImpl.bookTicket()",bookingRequest.getMovieName());
        Theatre theatreInDB = theatreServiceImpl.findByName(bookingRequest.getTheatreName());
        int availableSeats = theatreInDB.getSeats();
        if (bookingRequest.getQuantity() > availableSeats)throw new ValidationException("Available Seats:"+theatreInDB.getSeats());

        if (bookingRequest.getQuantity() <= availableSeats){
            availableSeats = availableSeats-bookingRequest.getQuantity();
            Theatre oldTheatre = theatreInDB;

            oldTheatre.setSeats(availableSeats);
            theatreRepository.save(oldTheatre);
        }
        Booking booking=new Booking(bookingRequest.getTheatreName(),bookingRequest.getMovieName(),bookingRequest.getQuantity());
        Booking newBooking = bookingRepository.save(booking);
        logger.info("{}: Function end: BookingServiceImpl.bookTicket(), Available Seats : "+availableSeats,bookingRequest.getMovieName());
        return newBooking;
    }

    @Override
    public List<Booking> getAllBooking() throws ValidationException {
        logger.info("Function start: BookingServiceImpl.getAllBooking()");
        List<Booking> bookings=bookingRepository.findAll();
        logger.info("Function end: BookingServiceImpl.getAllBooking()");
        return bookings;
    }

    @Override
    public void cancelBooking(int bookid,int theatreid) throws ValidationException {
        Booking bookingInDB = findById(bookid);
        Theatre theatreInDB = theatreServiceImpl.findById(theatreid);
        logger.info("{}: Function start: BookingServiceImpl.cancelBooking()",bookid);
        int availableSeats = theatreInDB.getSeats();
        int cancelSeats = bookingInDB.getQuantity();
        if (bookingInDB==null) throw new ValidationException("Can't Cancel. Because, Booking doesn't exist.");
        if (bookingInDB!=null){
            Theatre oldTheatre = theatreInDB;
            availableSeats = availableSeats +cancelSeats;

            oldTheatre.setSeats(availableSeats);
            theatreRepository.save(oldTheatre);
        }
        bookingRepository.deleteById(bookid);
        logger.info("{}: Function end: BookingServiceImpl.cancelBooking()",bookid);
    }


    public Booking findById(int id){
        return bookingRepository.findById(id);
    }
}
