package com.balamsd7.flightbooking.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserRegisterRequestDto {
    @NonNull
    private String userName;
    @NonNull
    private String password;
    private String firstName;
    private String lastName;
    @NonNull
    private String emailId;
    @NonNull
    private long mobileNumber;
    private String country;
    private String state;
    private String city;

    public  UserRegisterRequestDto(){}
}
