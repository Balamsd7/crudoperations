package com.mohsinkd786.data.repos;

import com.mohsinkd786.data.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
