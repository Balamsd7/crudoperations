package com.mohsinkd786.services;

import com.mohsinkd786.data.entities.Credentials;
import com.mohsinkd786.data.entities.Customer;
import com.mohsinkd786.data.repos.CredentialsRepository;
import com.mohsinkd786.data.repos.CustomerRepository;
import com.mohsinkd786.dtos.CustomerDto;
import com.mohsinkd786.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CredentialsRepository credentialsRepository;
    private CustomerRepository customerRepository;

    private CustomerMapper mapper;

    @Autowired
    public void setMapper(CustomerMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setCredentialsRepository(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto){
        Credentials credentialsEntity = mapper.map(customerDto,Credentials.class);
        Credentials savedCredentials = credentialsRepository.save(credentialsEntity);
        // save customer
        Customer customerEntity = mapper.map(customerDto,Customer.class);
        customerEntity.setCredentials(savedCredentials);
        Customer savedCustomer = customerRepository.save(customerEntity);

        CustomerDto savedCustomerDto = mapper.map(savedCustomer,CustomerDto.class);

        return savedCustomerDto;
    }
    public List<CustomerDto> findCustomers(){
        List<CustomerDto> customers= customerRepository
                .findAll()
                .stream()
                .map(customer -> mapper.map(customer,CustomerDto.class))
                .collect(Collectors.toList());
        return customers;
    }
}
