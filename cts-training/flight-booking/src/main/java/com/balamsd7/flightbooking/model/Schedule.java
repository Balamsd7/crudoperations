package com.balamsd7.flightbooking.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int airLineId;
    private int flightId;
    private int instrumentId;
    private String airlineName;
    private String flightName;
    private String fromPlace;
    private String toPlace;
    private Date startDateTime;
    private Date endDateTime;
    private String scheduledDays;
    private int totalBusinessClassSeats;
    private int totalNonBusinessClassSeats;
    private double ticketCost;
    private int numberOfRows;
    private String meal;
}
