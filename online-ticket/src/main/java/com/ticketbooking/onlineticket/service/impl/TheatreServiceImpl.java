package com.ticketbooking.onlineticket.service.impl;

import com.ticketbooking.onlineticket.dto.TheatreRequest;
import com.ticketbooking.onlineticket.exception.ValidationException;
import com.ticketbooking.onlineticket.modal.Theatre;
import com.ticketbooking.onlineticket.repository.TheatreRepository;
import com.ticketbooking.onlineticket.service.TheatreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    private static final Logger logger = LoggerFactory.getLogger(TheatreService.class);

    @Override
    public Theatre saveTheatre(TheatreRequest theatreRequest) throws ValidationException {
        logger.info("{}: Function start: TheatreServiceImpl.saveTheatre()");
        Theatre theatre=new Theatre(theatreRequest.getName(),theatreRequest.getLocation(),theatreRequest.getSeats());
        Theatre newtheatre = theatreRepository.save(theatre);
        logger.info("{}: Function end: TheatreServiceImpl.saveTheatre()");
        return newtheatre;
    }

    @Override
    public List<Theatre> getAllTheatres() throws ValidationException {
        logger.info("{}: Function start: TheatreServiceImpl.getAllTheatres()");
        List<Theatre> theatres=theatreRepository.findAll();
        logger.info("{}: Function end: TheatreServiceImpl.getAllTheatres()");
        return theatres;
    }

    @Override
    public Theatre getTheatreByLocation(String location) throws ValidationException {
        Theatre theatreInDB = findByLocation(location);
        logger.info("{}: Function start: TheatreServiceImpl.getTheatreByLocation()");
        if (theatreInDB==null)throw new ValidationException("Theatre doesn't exist.");
        Theatre theatre= theatreRepository.findTheatreByLocation(location);
        logger.info("{}: Function end: TheatreServiceImpl.getTheatreByLocation()");
        return theatre;
    }

    @Override
    public Theatre updateTheatre(Theatre theatre, String name) throws ValidationException {
        Theatre theatreInDB = findByName(name);
        logger.info("{}: Function start: TheatreServiceImpl.updateTheatre()");
        if (theatreInDB==null)throw new ValidationException("Can't Update. Because, Theatre doesn't exist.");
        Theatre oldTheatre = theatreInDB;

        oldTheatre.setName(theatre.getName());
        oldTheatre.setLocation(theatre.getLocation());
        oldTheatre.setSeats(theatre.getSeats());

        theatreRepository.save(oldTheatre);
        logger.info("{}: Function end: TheatreServiceImpl.updateTheatre()");
        return oldTheatre;
    }

    @Override
    public void deleteTheatre(int id) throws ValidationException {
        Theatre theatreInDB = findById(id);
        logger.info("{}: Function start: TheatreServiceImpl.deleteTheatre()");
        if (theatreInDB==null) throw new ValidationException("Can't Update. Because, Theatre doesn't exist.");
        theatreRepository.deleteById(id);
        logger.info("{}: Function end: TheatreServiceImpl.deleteTheatre()");
    }

    public Theatre findByLocation(String location){
        return theatreRepository.findTheatreByLocation(location);
    }
    public Theatre findByName(String name){
        return theatreRepository.findByName(name);
    }
    public Theatre findById(int id){
        return theatreRepository.findById(id);
    }
    public Theatre deleteByName(String name){
        return theatreRepository.deleteTheatreByName(name);
    }
}
