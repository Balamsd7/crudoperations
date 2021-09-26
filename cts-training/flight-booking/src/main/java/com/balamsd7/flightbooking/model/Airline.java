package com.balamsd7.flightbooking.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String airlineCode;
    private String airlineName;
    private String airlineLocation;
    private long contactNumber;

    /*@OneToOne
    private Flight flight;*/
}
