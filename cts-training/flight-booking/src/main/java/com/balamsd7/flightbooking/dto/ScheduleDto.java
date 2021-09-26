package com.balamsd7.flightbooking.dto;

import lombok.Data;
import lombok.NonNull;

import java.sql.Date;

@Data
public class ScheduleDto {
    private int scheduleId;
    @NonNull
    private int airlineId;
    @NonNull
    private int flightId;
    private int instrumentId;
    @NonNull
    private String fromPlace;
    @NonNull
    private String toPlace;
    private String airlineName;
    private String flightName;
    private Date startDateTime;
    private Date endDateTime;
    private String scheduledDays;
    private int businessClassSeats;
    private int nonBusinessClassSeats;
    @NonNull
    private double ticketCost;
    private int numberOfRows;
    private String meal;

    public ScheduleDto(){}
}
