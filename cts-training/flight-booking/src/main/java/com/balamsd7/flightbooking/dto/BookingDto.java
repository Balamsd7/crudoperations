package com.balamsd7.flightbooking.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Data
public class BookingDto {
    private int bookingId;
    @NonNull
    private String flightId;
    @NonNull
    private String name;
    @NonNull
    private String emailId;
    @NonNull
    private int noOfSeats;
    private String meal;
    private String status;
    //private String seatNos;
    private String pnrNumber;
    private Set<PassengerDto> passengerDto;
    public BookingDto(){}

}
