package com.balamsd7.flightbooking.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PassengerDto {
    @NonNull
    private String name;
    private String gender;
    @NonNull
    private int age;
    public  PassengerDto(){}
}
