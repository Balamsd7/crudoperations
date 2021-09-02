package com.mohsinkd786.services;

import com.mohsinkd786.data.entities.Address;
import com.mohsinkd786.data.entities.Credentials;
import com.mohsinkd786.data.entities.Customer;
import com.mohsinkd786.data.repos.AddressRepository;
import com.mohsinkd786.data.repos.CredentialsRepository;
import com.mohsinkd786.data.repos.CustomerRepository;
import com.mohsinkd786.dtos.CustomerDto;
import com.mohsinkd786.mapper.AddressMapper;
import com.mohsinkd786.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private CredentialsRepository credentialsRepository;
    private AddressRepository addressRepository;
    private CustomerMapper mapper;
    private AddressMapper addressMapper;
    @Autowired
    public CustomerService(CredentialsRepository credentialsRepository,
                           CustomerRepository customerRepository,
                           AddressRepository addressRepository,
                           CustomerMapper mapper,
                           AddressMapper addressMapper){
        this.mapper = mapper;
        this.customerRepository = customerRepository;
        this.credentialsRepository = credentialsRepository;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public CustomerDto createCustomer(CustomerDto customerDto){
        Credentials credentialsEntity = mapper.map(customerDto,Credentials.class);
        Credentials savedCredentials = credentialsRepository.save(credentialsEntity);
        // save customer
        Customer customerEntity = mapper.map(customerDto,Customer.class);
        customerEntity.setCredentials(savedCredentials);
        Customer savedCustomer = customerRepository.save(customerEntity);

        StreamSupport
            .stream(customerDto.getAddresses().spliterator(),false)
            .map(addressDto-> addressMapper.map(addressDto,Address.class))
            .forEach(address -> {
                // persist customer addresses
                address.setCustomer(savedCustomer);
                addressRepository.save(address);
            });

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

    public List<CustomerDto> findCustomersByCity(String city){
        List<Customer> customerList = customerRepository
                .findByAddresses_City(city);

        List<CustomerDto> customers= customerList
                .stream()
                .map(customer -> mapper.map(customer,CustomerDto.class))
                .collect(Collectors.toList());
        return customers;
    }
}
