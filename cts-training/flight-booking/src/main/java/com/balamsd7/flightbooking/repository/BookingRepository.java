package com.balamsd7.flightbooking.repository;

import com.balamsd7.flightbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
