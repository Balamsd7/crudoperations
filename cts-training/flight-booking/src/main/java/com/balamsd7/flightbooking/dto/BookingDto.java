package com.balamsd7.flightbooking.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Data
public class BookingDto {
    private int bookingId;
    @NonNull
    private int scheduleId;
    @NonNull
    private int flightId;
    @NonNull
    private int airlineId;
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
    private String airlineName;
    private String flightName;
    private int totalCost;
    private Set<PassengerDto> passengerDto;
    public BookingDto(){}

}
