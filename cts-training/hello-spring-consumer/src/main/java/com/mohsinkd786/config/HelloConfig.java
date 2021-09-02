package com.mohsinkd786.config;

import com.mohsinkd786.service.EmployeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HelloConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

//    @Bean
//    public EmployeeService employeeService(){
//        return new EmployeeService();
//    }
}
