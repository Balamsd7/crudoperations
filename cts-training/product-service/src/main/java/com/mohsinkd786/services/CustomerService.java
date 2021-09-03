package com.mohsinkd786.services;

import com.mohsinkd786.data.entities.Address;
import com.mohsinkd786.data.entities.Credentials;
import com.mohsinkd786.data.entities.Customer;
import com.mohsinkd786.data.entities.Orders;
import com.mohsinkd786.data.repos.AddressRepository;
import com.mohsinkd786.data.repos.CredentialsRepository;
import com.mohsinkd786.data.repos.CustomerRepository;
import com.mohsinkd786.data.repos.OrdersRepository;
import com.mohsinkd786.dtos.CustomerDto;
import com.mohsinkd786.dtos.OrderDto;
import com.mohsinkd786.mapper.AddressMapper;
import com.mohsinkd786.mapper.CustomerMapper;
import com.mohsinkd786.mapper.OrdersMapper;
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
    private OrdersRepository ordersRepository;
    private CustomerMapper mapper;
    private AddressMapper addressMapper;
    private OrdersMapper ordersMapper;
    @Autowired
    public CustomerService(CredentialsRepository credentialsRepository,
                           CustomerRepository customerRepository,
                           AddressRepository addressRepository,
                           OrdersRepository ordersRepository,
                           CustomerMapper mapper,
                           AddressMapper addressMapper,
                           OrdersMapper ordersMapper){
        this.mapper = mapper;
        this.customerRepository = customerRepository;
        this.credentialsRepository = credentialsRepository;
        this.addressRepository = addressRepository;
        this.ordersRepository = ordersRepository;
        this.addressMapper = addressMapper;
        this.ordersMapper = ordersMapper;
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
        StreamSupport
            .stream(customerDto.getOrders().spliterator(),false)
            .map(ordersDto-> ordersMapper.map(ordersDto, Orders.class))
            .forEach(order -> {
                // persist customer addresses
                order.setCustomer(savedCustomer);
                ordersRepository.save(order);
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

    public List<OrderDto> findCustomersByOrderId(String orderId) {
        List<Orders> ordersList = ordersRepository.findByOrderId(orderId);

        List<OrderDto> orderDtoList = ordersList
                .stream()
                .map(orders -> ordersMapper.map(orders, OrderDto.class))
                .collect(Collectors.toList());
        return  orderDtoList;
    }
}
