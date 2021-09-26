package com.balamsd7.flightbooking.dto;

import lombok.Data;

@Data
public class InstrumentDto {
    private int instrumentId;
    private String instrumentName;
    private String instrumentNo;
    private int capacity;
    private int businessClass;
    private int nonBusinessClass;
}
