package com.mohsinkd786.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class CustomerDto {
    private String name;
    private String email;
    private String phone;
    private String username;
    private String password;
    private Set<AddressDto> addresses;
}
