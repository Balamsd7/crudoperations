package com.balamsd7.flightbooking.controller;

import com.balamsd7.flightbooking.dto.BookingDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1.0/flight/booking")
@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping()
    public ResponseEntity<ResponseDataDto> createBooking(@RequestBody BookingDto bookingDto){
        return ResponseEntity.ok(bookingService.createBooking(bookingDto));
    }
    @PostMapping("/update")
    public ResponseEntity<ResponseDataDto> updateBooking(@RequestBody BookingDto bookingDto){
        return ResponseEntity.ok(bookingService.updateBooking(bookingDto));
    }

    @GetMapping("/ticket/{pnr}")
    public ResponseEntity<ResponseDataDto> getBookedTicketByPnrNumber(@PathVariable("pnr") String pnrNumber){
        return ResponseEntity.ok(bookingService.getBookedTicketByPnrNumber(pnrNumber));
    }

    @GetMapping("/history/{emailId}")
    public ResponseEntity<ResponseDataDto> getBookedTicketByEmailId(@PathVariable("emailId") String emailId){
        return ResponseEntity.ok(bookingService.getBookedTicketByEmailId(emailId));
    }
    @DeleteMapping("/cancel/{pnr}")
    public ResponseEntity<Boolean> cancelTicketByPnrNumber(@PathVariable("pnr") String pnrNumber){
        return ResponseEntity.ok(bookingService.cancelTicketByPnrNumber(pnrNumber));
    }
}
