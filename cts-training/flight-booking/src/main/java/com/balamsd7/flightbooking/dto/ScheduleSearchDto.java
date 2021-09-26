package com.balamsd7.flightbooking.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ScheduleSearchDto {
    private Date dateTime;
    private String fromPlace;
    private String toPlace;
    //private String trip;

}
