package com.balamsd7.flightbooking.repository;

import com.balamsd7.flightbooking.model.AirLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<AirLine, Integer> {
}
