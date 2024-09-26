package com.example.assessment.controller;

import com.example.assessment.dto.InventoryItemDto;
import com.example.assessment.services.InventoryService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
        String id = inventoryService.add(inventoryItemDto).toString();
        return new ResponseEntity<>("Added item with id = "+id, HttpStatus.CREATED);
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteItem(@RequestParam String id){
        String message = inventoryService.delete(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<InventoryItemDto> getItem(@RequestParam String id){
        return new ResponseEntity<>(inventoryService.get(id),HttpStatus.OK);
    }
}
