package com.balamsd7.flightbooking.repository;

import com.balamsd7.flightbooking.model.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepository extends JpaRepository<Instrument, Integer> {
}
