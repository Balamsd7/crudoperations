package com.mohsinkd786.data.repos;


import com.mohsinkd786.data.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    List<Customer> findByAddresses_City(String city);
}
