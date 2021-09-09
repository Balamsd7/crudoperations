package com.balamsd7.flightbooking.service;

import com.balamsd7.flightbooking.dto.AirlineDto;
import com.balamsd7.flightbooking.dto.FlightDto;
import com.balamsd7.flightbooking.dto.InstrumentDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.model.AirLine;
import com.balamsd7.flightbooking.model.Flight;
import com.balamsd7.flightbooking.model.Instrument;
import com.balamsd7.flightbooking.repository.AirlineRepository;
import com.balamsd7.flightbooking.utils.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    public  ResponseDataDto createAirline(AirlineDto airlineDto) {

        ResponseDataDto responseDataDto = new ResponseDataDto();
        AirLine airLine = toAirlineEntity(airlineDto);
        AirLine savedAirline = airlineRepository.save(airLine);
        if(Objects.nonNull(savedAirline)){
            responseDataDto.setMessage(CommonConstants.SUCCESS);
            responseDataDto.setResult(savedAirline);
        }else{
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return  responseDataDto;
    }

    private  AirLine toAirlineEntity(AirlineDto airlineDto) {
        AirLine airLine = new AirLine();
        airLine.setAirlineName(airlineDto.getAirlineName());
        airLine.setAirlineNumber(airlineDto.getAirlineNumber());
        airLine.setContactNumber(airlineDto.getContactNumber());

        Flight flight = new Flight();
        flight.setFlightName(airlineDto.getFlightDto().getFlightName());
        flight.setFlightAddress(airlineDto.getFlightDto().getFlightAddress());
        flight.setCapacity(airlineDto.getFlightDto().getCapacity());

        Instrument instrument = new Instrument();
        instrument.setInstrumentName(airlineDto.getFlightDto().getInstrumentDto().getInstrumentName());
        instrument.setInstrumentNumber(airlineDto.getFlightDto().getInstrumentDto().getInstrumentNo());
        flight.setInstrument(instrument);

        airLine.setFlight(flight);
        return airLine;
    }

    public ResponseDataDto getAllAirline() {
        ResponseDataDto responseDataDto = new ResponseDataDto();
         List<AirLine> airLineList =  airlineRepository.findAll();
         if(!CollectionUtils.isEmpty(airLineList)){
             List<AirlineDto> airlineDtoList = airLineList
                     .stream()
                     .map(airLine ->  toAirlineDto(airLine))
                     .collect(Collectors.toList());
             responseDataDto.setMessage(CommonConstants.SUCCESS);
             responseDataDto.setResult(airlineDtoList);
         }else{
             responseDataDto.setMessage(CommonConstants.NDF);
         }
         return  responseDataDto;
    }

    private  AirlineDto  toAirlineDto(AirLine airLine) {
        AirlineDto airlineDto = new AirlineDto();
        airlineDto.setAirlineName(airLine.getAirlineName());
        airlineDto.setAirlineNumber(airLine.getAirlineNumber());
        airlineDto.setContactNumber(airLine.getContactNumber());

        FlightDto flightDto = new FlightDto();
        flightDto.setFlightId(airLine.getFlight().getId());
        flightDto.setFlightName(airLine.getFlight().getFlightName());
        flightDto.setFlightAddress(airLine.getFlight().getFlightAddress());
        flightDto.setCapacity(airLine.getFlight().getCapacity());

        InstrumentDto instrumentDto = new InstrumentDto();
        instrumentDto.setInstrumentId(airLine.getFlight().getInstrument().getId());
        instrumentDto.setInstrumentName(airLine.getFlight().getInstrument().getInstrumentName());
        instrumentDto.setInstrumentNo(airLine.getFlight().getInstrument().getInstrumentNumber());
        flightDto.setInstrumentDto(instrumentDto);

        airlineDto.setFlightDto(flightDto);
        return airlineDto;
    }

    public ResponseDataDto   getAirlineById(int airlineId) {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        Optional<AirLine> optional = airlineRepository.findById(airlineId);

        if(optional.isPresent()){
            AirLine airLine = optional.get();
            if(Objects.nonNull(airLine)) {
                AirlineDto airlineDto = toAirlineDto(airLine);
                responseDataDto.setMessage(CommonConstants.SUCCESS);
                responseDataDto.setResult(airlineDto);
            }else{
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }else{
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return  responseDataDto;

    }

    public ResponseDataDto   deleteByAirlineId(int airlineId) {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        Optional<AirLine> optional = airlineRepository.findById(airlineId);

        if(optional.isPresent()){
            AirLine airLine = optional.get();
            if(Objects.nonNull(airLine)) {
                airlineRepository.delete(airLine);
                responseDataDto.setMessage(CommonConstants.SUCCESS);
            }else{
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }else{
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return  responseDataDto;
    }
}
