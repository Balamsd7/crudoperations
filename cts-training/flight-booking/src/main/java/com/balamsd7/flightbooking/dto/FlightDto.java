package com.balamsd7.flightbooking.dto;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.criteria.CriteriaBuilder;

@Data
public class FlightDto {
    private int flightId;
    private int capacity;
    private int airlineId;
    private int instrumentId;
    private int businessClass;
    private int nonBusinessClass;
    @NonNull
    private String flightNumber;
    @NonNull
    private String flightName;
    public FlightDto(){}

}
