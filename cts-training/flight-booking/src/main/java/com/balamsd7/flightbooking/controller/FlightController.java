package com.balamsd7.flightbooking.controller;

import com.balamsd7.flightbooking.dto.FlightDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1.0/flight/details")
@RestController
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDataDto> createFlight(@RequestBody FlightDto flightDto){
        return ResponseEntity.ok(flightService.createFlight(flightDto));
    }
    @GetMapping("/getAllFlight")
    public ResponseEntity<ResponseDataDto> getAllFlight (){
        return ResponseEntity.ok(flightService.getAllFlight());
    }

    @GetMapping("/getFlightById")
    public ResponseEntity<ResponseDataDto> getFlightById (@RequestParam("id") int flightId){
        return ResponseEntity.ok(flightService.getFlightById(flightId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDataDto> deleteByFlightId (@RequestParam("id") int flightId){
        return ResponseEntity.ok(flightService.deleteByFlightId(flightId));
    }
}
