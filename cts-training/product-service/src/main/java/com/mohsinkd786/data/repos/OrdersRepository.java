package com.mohsinkd786.data.repos;

import com.mohsinkd786.data.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findByOrderId(String orderId);
}
