package com.balamsd7.flightbooking.controller;

import com.balamsd7.flightbooking.dto.InventoryDto;
import com.balamsd7.flightbooking.dto.InventorySearchDto;
import com.balamsd7.flightbooking.dto.ResponseDataDto;
import com.balamsd7.flightbooking.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1.0/flight/airline/inventory")
@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add")
    public ResponseEntity<String> createInventory(@RequestBody InventoryDto inventoryDto){
        return  ResponseEntity.ok(inventoryService.createInventory(inventoryDto));
    }
    @PostMapping("/update")
    public ResponseEntity<String> updateInventory(@RequestBody InventoryDto inventoryDto){
        return  ResponseEntity.ok(inventoryService.updateInventory(inventoryDto));
    }

    @DeleteMapping( "/delete")
    public ResponseEntity<Boolean> deleteById(@RequestParam(name = "id")   int inventoryId){
        return  ResponseEntity.ok(inventoryService.deleteById(inventoryId));
    }

    @GetMapping("/getAllInventory")
    public ResponseEntity<ResponseDataDto> getAllInventory(){
        return  ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseDataDto> searchInventory(@RequestBody InventorySearchDto inventorySearchDto){
        return  ResponseEntity.ok(inventoryService.searchInventory(inventorySearchDto));
    }



}
