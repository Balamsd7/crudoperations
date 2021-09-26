package com.balamsd7.flightbooking.controller;

import com.balamsd7.flightbooking.dto.ScheduleDto;
import com.balamsd7.flightbooking.dto.ScheduleSearchDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.service.ScheduleService;
import com.balamsd7.flightbooking.utils.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/v1.0/flight/airline/schedule")
@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDataDto> createSchedule(@RequestBody ScheduleDto scheduleDto){
        return  APIResponseBuilder.buildResponseFromDto(scheduleService.createSchedule(scheduleDto));
    }
    @PostMapping("/update")
    public ResponseEntity<ResponseDataDto> updateSchedule(@RequestBody ScheduleDto scheduleDto){
        return  APIResponseBuilder.buildResponseFromDto(scheduleService.updateSchedule(scheduleDto));
    }

    @DeleteMapping( "/delete")
    public ResponseEntity<ResponseDataDto> deleteById(@RequestParam(name = "id")   int scheduleId){
        return  APIResponseBuilder.buildResponseFromDto(scheduleService.deleteById(scheduleId));
    }

    @GetMapping("/getAllSchedule")
    public ResponseEntity<ResponseDataDto> getAllSchedule(){
        return  APIResponseBuilder.buildResponseFromDto(scheduleService.getAllSchedule());
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseDataDto> searchSchedule(@RequestBody ScheduleSearchDto scheduleSearchDto){
        return  APIResponseBuilder.buildResponseFromDto(scheduleService.searchSchedule(scheduleSearchDto));
    }



}
