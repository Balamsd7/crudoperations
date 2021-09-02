package com.mohsinkd786.controller;

import com.mohsinkd786.dto.EmployeeDto;
import com.mohsinkd786.dto.EmployeeRequest;
import com.mohsinkd786.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("sayHello")
    public String sayHello(){
        return "Employee Controller";
    }

    @GetMapping
    public List<EmployeeDto> getEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public boolean saveEmployee(@RequestBody EmployeeRequest request){
        return employeeService.saveEmployee(request);
    }
}
