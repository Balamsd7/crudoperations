package com.balamsd7.flightbooking.service;

import com.balamsd7.flightbooking.Exception.BusinessException;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.dto.UserRegisterRequestDto;
import com.balamsd7.flightbooking.model.User;
import com.balamsd7.flightbooking.repository.UserRegisterRepository;
import com.balamsd7.flightbooking.utils.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RegisterService {

    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    private UserRegisterRepository userRegisterRepository;

    public ResponseDataDto userRegister(UserRegisterRequestDto userRegisterRequestDto) {

        ResponseDataDto responseDataDto = new ResponseDataDto();
        try{
            User user = toRegisterEntity(userRegisterRequestDto);

            User savedUser  =  userRegisterRepository.save(user);

            if(Objects.nonNull(savedUser)){
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

    private User toRegisterEntity(UserRegisterRequestDto userRegisterRequestDto) {
        User user = new User();
        user.setUserName(userRegisterRequestDto.getUserName());
        user.setPassword(userRegisterRequestDto.getPassword());
        user.setRoleId(2);
        user.setFirstName(userRegisterRequestDto.getFirstName());
        user.setLastName(userRegisterRequestDto.getLastName());
        user.setEmailId(userRegisterRequestDto.getEmailId());
        user.setMobileNumber(userRegisterRequestDto.getMobileNumber());
        user.setCountry(userRegisterRequestDto.getCountry());
        user.setState(userRegisterRequestDto.getState());
        user.setCity(userRegisterRequestDto.getCity());

        return  user;
    }
    public User getUserDetailsByUserName(String userName){
        User user = userRegisterRepository.findByUserName(userName);
        return user;
    }

}
