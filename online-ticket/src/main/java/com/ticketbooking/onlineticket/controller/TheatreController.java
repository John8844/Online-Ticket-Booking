package com.ticketbooking.onlineticket.controller;


import com.ticketbooking.onlineticket.dto.TheatreRequest;
import com.ticketbooking.onlineticket.exception.ValidationException;
import com.ticketbooking.onlineticket.modal.response.ResponseMessage;
import com.ticketbooking.onlineticket.modal.response.ResponseStatus;
import com.ticketbooking.onlineticket.service.TheatreService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/theatre")
public class TheatreController {
    private TheatreService theatreService;

    private static final Logger logger = LoggerFactory.getLogger(TheatreController.class);

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @PostMapping
    public ResponseEntity<?> saveTheatre(@RequestBody @Valid TheatreRequest theatreRequest){
        logger.info("{}: Post Request to create Theatre, Theatre: {}",theatreRequest.getName());
        try{
            theatreService.saveTheatre(theatreRequest);
            return new ResponseEntity<>(new ResponseMessage(201, ResponseStatus.Successful,"Theatre Added Successfully..."), HttpStatus.CREATED);
        }catch (ValidationException e){
            return new ResponseEntity<>(new ResponseMessage(403,ResponseStatus.Failure,e.getExceptionMessage()),HttpStatus.FORBIDDEN);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(500,ResponseStatus.Failure,"Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
