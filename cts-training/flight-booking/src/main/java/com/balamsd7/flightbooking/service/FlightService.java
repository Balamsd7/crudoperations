package com.balamsd7.flightbooking.service;

import com.balamsd7.flightbooking.dto.FlightDto;
import com.balamsd7.flightbooking.dto.InstrumentDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.model.Flight;
import com.balamsd7.flightbooking.model.Instrument;
import com.balamsd7.flightbooking.repository.FlightRepository;
import com.balamsd7.flightbooking.repository.InstrumentRepository;
import com.balamsd7.flightbooking.utils.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private InstrumentRepository instrumentRepository;

    public ResponseDataDto createFlight(FlightDto flightDto) {

        ResponseDataDto responseDataDto = new ResponseDataDto();
        try{
            if(flightRepository.existsByFlightNumber(flightDto.getFlightNumber())) {
                responseDataDto.setMessage("Flight Number is already in use!");
                return responseDataDto;
            }
            Flight flight = toFlightEntity(flightDto);
            Flight savedFlight = flightRepository.save(flight);
            if(Objects.nonNull(savedFlight)){
                responseDataDto.setMessage(CommonConstants.SUCCESS);
                responseDataDto.setResult(savedFlight);
            }else{
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return  responseDataDto;
    }

    public ResponseDataDto getAllFlight() {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try{
            List<Flight> flightList =  flightRepository.findAll();
            if(!CollectionUtils.isEmpty(flightList)){
                List<FlightDto> flightDtoList = flightList
                        .stream()
                        .map(flight ->  toFlightDto(flight))
                        .collect(Collectors.toList());
                responseDataDto.setMessage(CommonConstants.SUCCESS);
                responseDataDto.setResult(flightDtoList);
            }else{
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return responseDataDto;
    }

    public ResponseDataDto getFlightById(int flightId) {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try{
            Optional<Flight> optional = flightRepository.findById(flightId);

            if(optional.isPresent()){
                Flight flight = optional.get();
                if(Objects.nonNull(flight)) {
                    FlightDto flightDto = toFlightDto(flight);
                    responseDataDto.setMessage(CommonConstants.SUCCESS);
                    responseDataDto.setResult(flightDto);
                }else{
                    responseDataDto.setMessage(CommonConstants.NDF);
                }
            }else{
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return  responseDataDto;

    }

    public ResponseDataDto deleteByFlightId(int flightId) {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try{
            Optional<Flight> optional = flightRepository.findById(flightId);

            if(optional.isPresent()){
                Flight flight = optional.get();
                if(Objects.nonNull(flight)) {
                    flightRepository.delete(flight);
                    responseDataDto.setMessage(CommonConstants.SUCCESS);
                }else{
                    responseDataDto.setMessage(CommonConstants.NDF);
                }
            }else{
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return  responseDataDto;
    }
    private  Flight toFlightEntity(FlightDto flightDto) {
        Flight flight = new Flight();
        flight.setFlightName(flightDto.getFlightName());
        flight.setFlightNumber(flightDto.getFlightNumber());
        flight.setInstrumentId(flightDto.getInstrumentId());
        flight.setCapacity(flightDto.getCapacity());
        flight.setAirlineId(flightDto.getAirlineId());
        flight.setBusinessClass(flightDto.getBusinessClass());
        flight.setNonBusinessClass(flightDto.getNonBusinessClass());


/*        Instrument instrument = new Instrument();
        instrument.setInstrumentName(flightDto.getInstrumentDto().getInstrumentName());
        instrument.setInstrumentNumber(flightDto.getInstrumentDto().getInstrumentNo());
        flight.setInstrument(instrument);*/

        return flight;
    }

    private  FlightDto  toFlightDto(Flight flight) {

        FlightDto flightDto = new FlightDto();
        flightDto.setFlightId(flight.getId());
        flightDto.setFlightName(flight.getFlightName());
        flightDto.setFlightNumber(flight.getFlightNumber());
        flightDto.setInstrumentId(flight.getInstrumentId());
        flightDto.setCapacity(flight.getCapacity());
        flightDto.setBusinessClass(flight.getBusinessClass());
        flightDto.setNonBusinessClass(flight.getNonBusinessClass());
        flightDto.setAirlineId(flight.getAirlineId());

/*        InstrumentDto instrumentDto = new InstrumentDto();
        instrumentDto.setInstrumentId(flight.getInstrument().getId());
        instrumentDto.setInstrumentName(flight.getInstrument().getInstrumentName());
        instrumentDto.setInstrumentNo(flight.getInstrument().getInstrumentNumber());
        flightDto.setInstrumentDto(instrumentDto);*/


        return flightDto;
    }

    private  InstrumentDto  toInstrumentDto(Instrument instrument) {

        InstrumentDto instrumentDto = new InstrumentDto();
        instrumentDto.setInstrumentId(instrument.getId());
        instrumentDto.setInstrumentName(instrument.getInstrumentName());
        instrumentDto.setInstrumentNo(instrument.getInstrumentNumber());
        instrumentDto.setCapacity(instrument.getCapacity());
        instrumentDto.setBusinessClass(instrument.getBusinessClass());
        instrumentDto.setNonBusinessClass(instrument.getNonBusinessClass());


        return instrumentDto;
    }


    public ResponseDataDto getInstruments() {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try{
            List<Instrument> instrumentList =  instrumentRepository.findAll();
            if(!CollectionUtils.isEmpty(instrumentList)){
                List<InstrumentDto> instrumentDtoList = instrumentList
                        .stream()
                        .map(instrument ->  toInstrumentDto(instrument))
                        .collect(Collectors.toList());
                responseDataDto.setMessage(CommonConstants.SUCCESS);
                responseDataDto.setResult(instrumentDtoList);
            }else{
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return responseDataDto;
    }
}
