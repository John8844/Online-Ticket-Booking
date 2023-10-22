package com.ticketbooking.onlineticket.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingRequest {

    @NotNull(message = "Theatre Name shouldn't be Null.")
    String theatreName;

    @NotNull(message = "Movie Name shouldn't be Null.")
    String movieName;

    @NotNull(message = "Ticket Quantity shouldn't be Null.")
    int quantity;
}
