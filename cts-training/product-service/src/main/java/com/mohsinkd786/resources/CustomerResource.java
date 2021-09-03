package com.mohsinkd786.resources;

import com.mohsinkd786.dtos.CustomerDto;
import com.mohsinkd786.dtos.OrderDto;
import com.mohsinkd786.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customers")
@RestController
public class CustomerResource {

    private CustomerService customerService;
    @Autowired
    public CustomerResource(CustomerService customerService){
        this.customerService = customerService;
    }
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }

    @GetMapping
    public List<CustomerDto> findCustomers(){
        return customerService.findCustomers();
    }

    @GetMapping("{city}")
    public List<CustomerDto> findCustomersByCity(@PathVariable String city){
        return customerService.findCustomersByCity(city);
    }

    @GetMapping("/findCustomersByOrderId")
    public List<OrderDto> findCustomersByOrderId(@RequestParam String orderId){
        return customerService.findCustomersByOrderId(orderId);
    }
}
