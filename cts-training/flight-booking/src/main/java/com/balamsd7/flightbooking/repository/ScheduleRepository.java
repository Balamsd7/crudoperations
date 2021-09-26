package com.balamsd7.flightbooking.repository;

import com.balamsd7.flightbooking.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    //List<Schedule> findByStartDateFromToPlace(Date startDateTime, String fromPlace, String toPlace);
    @Query("SELECT i from Schedule i WHERE i.startDateTime = ?1 AND i.fromPlace = ?2 AND i.toPlace = ?3")
     List<Schedule> findByStartDateFromToPlace(Date startDateTime, String fromPlace, String toPlace);
}
