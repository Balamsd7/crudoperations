package com.balamsd7.flightbooking.service;

import com.balamsd7.flightbooking.dto.ScheduleDto;
import com.balamsd7.flightbooking.dto.ScheduleSearchDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.model.Schedule;
import com.balamsd7.flightbooking.repository.ScheduleRepository;
import com.balamsd7.flightbooking.utils.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    @Autowired
    private ScheduleRepository scheduleRepository;

    public ResponseDataDto createSchedule(ScheduleDto scheduleDto) {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try{

            Schedule schedule = toScheduleEntity(scheduleDto);
            Schedule savedSchedule = scheduleRepository.save(schedule);
            if(Objects.nonNull(savedSchedule)){
                responseDataDto.setMessage(CommonConstants.SUCCESS);
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

    public ResponseDataDto updateSchedule(ScheduleDto scheduleDto) {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try{
            Optional<Schedule> optional = scheduleRepository.findById(scheduleDto.getScheduleId());
            if(optional.isPresent()){
                Schedule existingSchedule = optional.get();
                existingSchedule.setAirLineId(scheduleDto.getAirlineId());
                existingSchedule.setFlightId(scheduleDto.getFlightId());
                existingSchedule.setAirlineName(scheduleDto.getAirlineName());
                existingSchedule.setFlightName(scheduleDto.getFlightName());
                existingSchedule.setFromPlace(scheduleDto.getFromPlace());
                existingSchedule.setToPlace(scheduleDto.getToPlace());
                existingSchedule.setStartDateTime(scheduleDto.getStartDateTime());
                existingSchedule.setEndDateTime(scheduleDto.getEndDateTime());
                existingSchedule.setScheduledDays(scheduleDto.getScheduledDays());
                existingSchedule.setInstrumentId(scheduleDto.getInstrumentId());
                existingSchedule.setTotalBusinessClassSeats(scheduleDto.getBusinessClassSeats());
                existingSchedule.setTotalNonBusinessClassSeats(scheduleDto.getNonBusinessClassSeats());
                existingSchedule.setTicketCost(scheduleDto.getTicketCost());
                existingSchedule.setNumberOfRows(scheduleDto.getNumberOfRows());
                existingSchedule.setMeal(scheduleDto.getMeal());
                Schedule savedSchedule = scheduleRepository.save(existingSchedule);
                if(Objects.nonNull(savedSchedule)){
                    responseDataDto.setMessage(CommonConstants.SUCCESS);
                }else{
                    responseDataDto.setMessage(CommonConstants.NDF);
                }
            } else{
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return  responseDataDto;
    }

    public ResponseDataDto deleteById(int scheduleId) {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try{
            Optional<Schedule> optional = scheduleRepository.findById(scheduleId);
            if(optional.isPresent()){
                Schedule schedule = optional.get();
                if(Objects.nonNull(schedule)) {
                    scheduleRepository.delete(schedule);
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
        return responseDataDto;
    }

    public ResponseDataDto getAllSchedule() {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try {
            List<Schedule> scheduleList = scheduleRepository.findAll();
            if (!CollectionUtils.isEmpty(scheduleList)) {
                responseDataDto.setMessage(CommonConstants.SUCCESS);
                responseDataDto.setResult(scheduleList);
            } else {
                responseDataDto.setMessage(CommonConstants.NDF);
            }
        }
        catch (Exception e){
            logger.error(CommonConstants.EXCEPTION_MSG, e);
            responseDataDto.setMessage(CommonConstants.FAILURE);
        }
        return responseDataDto;
    }

    public  ResponseDataDto searchSchedule(ScheduleSearchDto scheduleSearchDto) {
        ResponseDataDto responseDataDto = new ResponseDataDto();
        try{
            List<Schedule> scheduleList = scheduleRepository.findByStartDateFromToPlace(scheduleSearchDto.getDateTime(), scheduleSearchDto.getFromPlace(), scheduleSearchDto.getToPlace());
            if(!CollectionUtils.isEmpty(scheduleList)){
                responseDataDto.setMessage(CommonConstants.SUCCESS);
                responseDataDto.setResult(scheduleList);
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

    private Schedule toScheduleEntity(ScheduleDto scheduleDto) {
        Schedule schedule = new Schedule();
        schedule.setAirLineId(scheduleDto.getAirlineId());
        schedule.setFlightId(scheduleDto.getFlightId());
        schedule.setAirlineName(scheduleDto.getAirlineName());
        schedule.setFlightName(scheduleDto.getFlightName());
        schedule.setFromPlace(scheduleDto.getFromPlace());
        schedule.setToPlace(scheduleDto.getToPlace());
        schedule.setStartDateTime(scheduleDto.getStartDateTime());
        schedule.setEndDateTime(scheduleDto.getEndDateTime());
        schedule.setScheduledDays(scheduleDto.getScheduledDays());
        schedule.setInstrumentId(scheduleDto.getInstrumentId());
        schedule.setTotalBusinessClassSeats(scheduleDto.getBusinessClassSeats());
        schedule.setTotalNonBusinessClassSeats(scheduleDto.getNonBusinessClassSeats());
        schedule.setTicketCost(scheduleDto.getTicketCost());
        schedule.setNumberOfRows(scheduleDto.getNumberOfRows());
        schedule.setMeal(scheduleDto.getMeal());
        return schedule;
    }
}
