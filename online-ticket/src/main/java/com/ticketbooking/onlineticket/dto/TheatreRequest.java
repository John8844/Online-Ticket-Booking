package com.ticketbooking.onlineticket.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class TheatreRequest {

    @NotNull(message = "Name shouldn't be Null.")
    private  String name;

    @NotNull(message = "Location shouldn't be Null.")
    private  String location;

    @NotNull(message = "Seats shouldn't be Null.")
    private  int seats;
}
