package com.balamsd7.flightbooking.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class AirlineDto {
    private  int airlineId;
    @NonNull
    private String airlineCode;
    @NonNull
    private String airlineName;
    @NonNull
    private String airlineLocation;
    private long contactNumber;
    //private FlightDto flightDto;

    public AirlineDto(){}
}
