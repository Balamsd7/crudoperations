package com.balamsd7.flightbooking.service;

import com.balamsd7.flightbooking.dto.BookingDto;
import com.balamsd7.flightbooking.dto.PassengerDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.model.Booking;
import com.balamsd7.flightbooking.model.Passenger;
import com.balamsd7.flightbooking.repository.BookingRepository;
import com.balamsd7.flightbooking.repository.PassengerRepository;
import com.balamsd7.flightbooking.utils.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;


@Service
public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    public ResponseDataDto createBooking(BookingDto bookingDto) {
        String generatedPNRNumber = "";

        ResponseDataDto responseDataDto = new ResponseDataDto();
        Booking booking = toBookingEntity(bookingDto);

        Booking savedBooking = bookingRepository.save(booking);

        StreamSupport
                .stream(bookingDto.getPassengerDto().spliterator(),false)
                .map(passengerDto-> toPassengerEntity(passengerDto))
                .forEach(passenger -> {
                    // persist customer addresses
                    passenger.setBooking(savedBooking);
                    passengerRepository.save(passenger);
                });
        if(Objects.nonNull(savedBooking)){
            generatedPNRNumber =  savedBooking.getPnrNumber();
            logger.info("Generated PNR Number : "+generatedPNRNumber);

             responseDataDto.setMessage(CommonConstants.SUCCESS);

        }else{
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        responseDataDto.setResult(generatedPNRNumber);
        return responseDataDto;

    }

    private Passenger toPassengerEntity(PassengerDto passengerDto) {
        Passenger passenger = new Passenger();
        passenger.setName(passengerDto.getName());
        passenger.setGender(passengerDto.getGender());
        passenger.setAge(passengerDto.getAge());
        return passenger;
    }

    private Booking toBookingEntity(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setFlightId(bookingDto.getFlightId());
        booking.setName(bookingDto.getName());
        booking.setEmailId(bookingDto.getEmailId());
        booking.setNoOfSeats(bookingDto.getNoOfSeats());
        booking.setMeal(bookingDto.getMeal());
        booking.setSeatNos(bookingDto.getSeatNos());

        booking.setPassengers(new LinkedHashSet<>());

        bookingDto.getPassengerDto().forEach(passengerDto -> {
            Passenger passenger = new Passenger();
            passenger.setName(passengerDto.getName());
            passenger.setGender(passengerDto.getGender());
            passenger.setAge(passengerDto.getAge());

            booking.getPassengers().add(passenger);
        });

        return booking;
    }

    public ResponseDataDto updateBooking(BookingDto bookingDto) {

        ResponseDataDto responseDataDto = new ResponseDataDto();

        Optional<Booking> optional = bookingRepository.findById(bookingDto.getBookingId());

        if(optional.isPresent()) {
            Booking existingBooking = optional.get();
            existingBooking.setName(bookingDto.getName());
            existingBooking.setEmailId(bookingDto.getEmailId());
            existingBooking.setNoOfSeats(bookingDto.getNoOfSeats());
            existingBooking.setMeal(bookingDto.getMeal());
            existingBooking.setSeatNos(bookingDto.getSeatNos());
            existingBooking.setPnrNumber(bookingDto.getPnrNumber());
            existingBooking.setPassengers(new LinkedHashSet<>());

            bookingDto.getPassengerDto().forEach(passengerDto -> {
                Passenger passenger = new Passenger();
                passenger.setName(passengerDto.getName());
                passenger.setGender(passengerDto.getGender());
                passenger.setAge(passengerDto.getAge());

                existingBooking.getPassengers().add(passenger);
            });

            Booking savedBooking = bookingRepository.save(existingBooking);

            StreamSupport
                    .stream(bookingDto.getPassengerDto().spliterator(), false)
                    .map(passengerDto -> toPassengerEntity(passengerDto))
                    .forEach(passenger -> {
                        // persist customer addresses
                        passenger.setBooking(savedBooking);
                        passengerRepository.save(passenger);
                    });
            if (Objects.nonNull(savedBooking)) {

                responseDataDto.setMessage(CommonConstants.SUCCESS);

            } else {
                responseDataDto.setMessage(CommonConstants.FAILURE);
            }
        }else{
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return responseDataDto;
    }
}
