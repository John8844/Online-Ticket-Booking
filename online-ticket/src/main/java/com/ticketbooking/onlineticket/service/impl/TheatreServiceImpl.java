package com.ticketbooking.onlineticket.service.impl;

import com.ticketbooking.onlineticket.dto.TheatreRequest;
import com.ticketbooking.onlineticket.exception.ValidationException;
import com.ticketbooking.onlineticket.modal.Theatre;
import com.ticketbooking.onlineticket.repository.TheatreRepository;
import com.ticketbooking.onlineticket.service.TheatreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TheatreServiceImpl implements TheatreService {

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
}
