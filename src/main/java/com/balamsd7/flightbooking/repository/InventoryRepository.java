package com.balamsd7.flightbooking.repository;

import com.balamsd7.flightbooking.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}
