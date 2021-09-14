package com.balamsd7.flightbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {


    @Autowired
    private RegisterService registerService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.balamsd7.flightbooking.model.User userDetails = registerService.getUserDetailsByUserName(username);

        if(userDetails!=null && userDetails.getUserName().equals(username)){
            // pass : admin
            // spring allows only hashed passwords = https://bcrypt-generator.com/
            // ideally the username & hashed password should come from database
            return new User(username,userDetails.getPassword(),new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("User not found : "+username);
        }
    }
}
