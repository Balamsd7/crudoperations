package com.balamsd7.flightbooking.service;

import com.balamsd7.flightbooking.dto.AirlineDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.model.Airline;
import com.balamsd7.flightbooking.repository.AirlineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AirlineServiceTest {

    @InjectMocks
    AirlineService airlineService;

    @Mock
    private AirlineRepository airlineRepository;

    @Test
    void createAirline() {
        AirlineDto airlineDto = new AirlineDto();
        airlineDto.setAirlineId(1);
        airlineDto.setAirlineName("Air Asia");

        when(airlineRepository.existsByAirlineName("Air India")).thenReturn(true);
        Airline airline = new Airline();
        airline.setId(airlineDto.getAirlineId());
        when(airlineRepository.save(airline)).thenReturn(new Airline());
        ResponseDataDto result = airlineService.createAirline(airlineDto);
        assertEquals("success", result.getMessage());
    }

    @Test
    void getAllAirline() {
    }

    @Test
    void getAirlineById() {
    }

    @Test
    void deleteByAirlineId() {
    }
}