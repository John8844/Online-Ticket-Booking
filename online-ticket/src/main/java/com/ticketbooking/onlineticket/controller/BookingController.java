package com.ticketbooking.onlineticket.controller;

import com.ticketbooking.onlineticket.dto.BookingRequest;
import com.ticketbooking.onlineticket.exception.ValidationException;
import com.ticketbooking.onlineticket.modal.Booking;
import com.ticketbooking.onlineticket.modal.Theatre;
import com.ticketbooking.onlineticket.modal.response.ResponseMessage;
import com.ticketbooking.onlineticket.modal.response.ResponseStatus;
import com.ticketbooking.onlineticket.service.BookingService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Ticket Booking API using bookTicket method
     * @param bookingRequest is given by user
     * @return Booking Details
     */
    @PostMapping
    public ResponseEntity<?> bookTicket(@RequestBody @Valid BookingRequest bookingRequest){
        logger.info(" Post Request to create Theatre, Theatre: {}",bookingRequest.getMovieName());
        try{
            bookingService.bookTicket(bookingRequest);
            return new ResponseEntity<>(new ResponseMessage(201, ResponseStatus.Successful,"Ticket Booked Successfully..."), HttpStatus.CREATED);
        }catch (ValidationException e){
            return new ResponseEntity<>(new ResponseMessage(403,ResponseStatus.Failure,e.getExceptionMessage()),HttpStatus.FORBIDDEN);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(500,ResponseStatus.Failure,"Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * View all Tickets API using getAllBooking method
     * @return List of Bookings
     */
    @GetMapping
    public ResponseEntity<?> getAllBooking(){
        try{
            logger.info(" GET Request to get All Booking");
            List<Booking> bookings = bookingService.getAllBooking();
            return new ResponseEntity<>(bookings,HttpStatus.OK);
        }catch (ValidationException e){
            return new ResponseEntity<>(new ResponseMessage(403,ResponseStatus.Failure,e.getExceptionMessage()),HttpStatus.FORBIDDEN);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(500,ResponseStatus.Failure,"Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Cancel Booking API using cancelBooking() method
     * @param bookid for delete booking
     * @param theatreid for adding those cancelled seats are added this theatre for rebooking
     * @return Cancel Confirmation
     */
    @DeleteMapping("/{bookid}/{theatreid}")
    public ResponseEntity<String> cancelBooking(@PathVariable("bookid") int bookid,@PathVariable("theatreid") int theatreid){
        try{
            logger.info(" DELETE Request to cancel Booking Details, Theatre: {}",bookid);
            bookingService.cancelBooking(bookid,theatreid);
            return new ResponseEntity<>("Booking Cancelled successfully...",HttpStatus.OK);
        }catch(ValidationException e){
            String errorMessage=e.getExceptionMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}
