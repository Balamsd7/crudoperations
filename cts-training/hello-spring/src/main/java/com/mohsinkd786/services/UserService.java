package com.mohsinkd786.services;

import com.mohsinkd786.dtos.UserDto;
import com.mohsinkd786.request.UserRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private List<UserDto> userDtos = new ArrayList<>();

    public List<UserDto> findUsers(){
        return userDtos;
    }

    public UserDto save(UserRequest request){
        UserDto userDto = toUserDto(request);
        userDtos.add(userDto);
        return userDto;
    }

    public UserDto findById(String userId){
        Optional<UserDto> userDto = userDtos.stream().filter(u-> u.getId().equals(userId)).findFirst();
        return userDto.isPresent() ? userDto.get() : null;
    }

    public boolean deleteById(String userId){
        UserDto userDto = findById(userId);
        if (Objects.nonNull(userDto)) {
            userDtos.remove(userDto);
            return true;
        }else {
            return false;
        }
    }

    private static UserDto toUserDto(UserRequest request){
        UserDto userDto = new UserDto();
        userDto.setName(request.getFirstName() + " "+request.getLastName());
        userDto.setEmail(request.getEmail());
        return userDto;
    }
}
