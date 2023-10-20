package com.ticketbooking.onlineticket.controller;


import com.ticketbooking.onlineticket.dto.TheatreRequest;
import com.ticketbooking.onlineticket.exception.ValidationException;
import com.ticketbooking.onlineticket.modal.Theatre;
import com.ticketbooking.onlineticket.modal.response.ResponseMessage;
import com.ticketbooking.onlineticket.modal.response.ResponseStatus;
import com.ticketbooking.onlineticket.service.TheatreService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatre")
public class TheatreController {

    @Autowired
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

    @GetMapping
    public ResponseEntity<?> getAllTheatres(){
        try{
            logger.info("{}: GET Request to get All Theatres");
            List<Theatre> theatres = theatreService.getAllTheatres();
            return new ResponseEntity<>(theatres,HttpStatus.OK);
        }catch (ValidationException e){
            return new ResponseEntity<>(new ResponseMessage(403,ResponseStatus.Failure,e.getExceptionMessage()),HttpStatus.FORBIDDEN);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage(500,ResponseStatus.Failure,"Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{location}")
    public ResponseEntity<?> getTheatreByLocation(@PathVariable("location") String location){
        try{
            logger.info("{}: GET Request to get Theatre by Location, Theatre: {}");
            Theatre theatre=theatreService.getTheatreByLocation(location);
            return new ResponseEntity<>(theatre,HttpStatus.OK);
        }catch(ValidationException e){
            String errorMessage=e.getExceptionMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<?> updateTheatre(@PathVariable("name") String name,@RequestBody Theatre theatre){
        try{
            logger.info("{}: PUT Request to update Theatre Details, Theatre: {}");
            Theatre updatedTheatre = theatreService.updateTheatre(theatre,name);
            return new ResponseEntity<>(updatedTheatre,HttpStatus.OK);
        }catch(ValidationException e){
            String errorMessage=e.getExceptionMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTheatre(@PathVariable("id") int id){
        try{
            logger.info("{}: DELETE Request to delete Theatre Details, Theatre: {}");
            theatreService.deleteTheatre(id);
            return new ResponseEntity<>("Theatre deleted successfully...",HttpStatus.OK);
        }catch(ValidationException e){
            String errorMessage=e.getExceptionMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

}
