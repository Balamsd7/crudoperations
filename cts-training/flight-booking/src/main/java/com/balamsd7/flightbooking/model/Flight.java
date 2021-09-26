package com.balamsd7.flightbooking.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int airlineId;
    private int instrumentId;
    private String flightNumber;
    private String flightName;

    private int capacity;
    private int businessClass;
    private int nonBusinessClass;


/*    @OneToOne(cascade=CascadeType.ALL)
    private Instrument instrument;*/
}
