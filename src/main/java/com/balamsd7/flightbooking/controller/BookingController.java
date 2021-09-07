package com.balamsd7.flightbooking.controller;

import com.balamsd7.flightbooking.dto.BookingDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1.0/flight")
@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<ResponseDataDto> createBooking(@RequestBody BookingDto bookingDto){
        return ResponseEntity.ok(bookingService.createBooking(bookingDto));
    }
    @PostMapping("/booking/update")
    public ResponseEntity<ResponseDataDto> updateBooking(@RequestBody BookingDto bookingDto){
        return ResponseEntity.ok(bookingService.updateBooking(bookingDto));
    }
}
