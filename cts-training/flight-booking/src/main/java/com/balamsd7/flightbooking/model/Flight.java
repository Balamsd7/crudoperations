package com.balamsd7.flightbooking.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String flightNumber;
    private String flightName;
    private String flightAddress;

    private int capacity;

    @OneToOne
    private AirLine airLine;

    @OneToOne
    private Instrument instrument;
}
