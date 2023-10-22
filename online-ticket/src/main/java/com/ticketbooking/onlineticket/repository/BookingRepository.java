package com.ticketbooking.onlineticket.repository;

import com.ticketbooking.onlineticket.modal.Booking;
import com.ticketbooking.onlineticket.modal.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

    Booking findById(int id);
}
