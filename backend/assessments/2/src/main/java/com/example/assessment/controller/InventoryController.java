package com.example.assessment.controller;

import com.example.assessment.dto.InventoryItemDto;
import com.example.assessment.services.InventoryService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    InventoryService inventoryService;
    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }
    @PostMapping("/update/")
    public ResponseEntity<String> updateItem(@RequestParam String id, @RequestBody InventoryItemDto inventoryItemDto){
        String message = inventoryService.update(id, inventoryItemDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addItem(@RequestBody InventoryItemDto inventoryItemDto){
        inventoryService.add(inventoryItemDto);
        return new ResponseEntity<>("Not implemented", HttpStatus.CREATED);
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteItem(){

        return new ResponseEntity<>("Not implemented", HttpStatus.OK);
    }
}
