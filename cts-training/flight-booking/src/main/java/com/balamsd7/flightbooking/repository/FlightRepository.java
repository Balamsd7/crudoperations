package com.balamsd7.flightbooking.repository;

import com.balamsd7.flightbooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    Boolean existsByFlightNumber(String flightNumber);

    List<Flight> findByAirlineId(int airlineId);

}
