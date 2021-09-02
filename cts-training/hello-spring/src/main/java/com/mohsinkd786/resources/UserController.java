package com.mohsinkd786.resources;

import com.mohsinkd786.dtos.UserDto;
import com.mohsinkd786.request.UserRequest;
import com.mohsinkd786.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/users")
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping
    public List<UserDto> findUsers(){
        return userService.findUsers();
    }

    @PostMapping
    public UserDto save(@RequestBody UserRequest request){
        return userService.save(request);
    }

    @GetMapping("{id}") // http://localhost:8080/v1/users/1
    public UserDto findById(@PathVariable String id){
        return userService.findById(id);
    }

    @DeleteMapping("{id}")
    public boolean deleteById(@PathVariable String id){
        return userService.deleteById(id);
    }
}
