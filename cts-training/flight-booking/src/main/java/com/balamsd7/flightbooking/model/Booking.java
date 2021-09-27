package com.balamsd7.flightbooking.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int scheduleId;
    private int airlineId;
    private int flightId;
    private String name;
    private String emailId;
    private int noOfSeats;
    private String meal;
    //private String seatNos;
    private String status;
    private String pnrNumber = "PNR"+ UUID.randomUUID().toString();
    private String airlineName;
    private String flightName;
    private int totalCost;

    @OneToMany(mappedBy = "booking")
    private Set<Passenger> passengers;


}
