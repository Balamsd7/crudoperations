package com.mohsinkd786.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
