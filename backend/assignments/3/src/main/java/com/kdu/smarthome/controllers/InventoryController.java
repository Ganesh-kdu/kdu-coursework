package com.kdu.smarthome.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.AddDeviceDto;
import com.kdu.smarthome.dto.InventoryAllDto;
import com.kdu.smarthome.dto.RequestInventoryDto;
import com.kdu.smarthome.services.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("")
    public ResponseEntity<InventoryAllDto> getAllItems() throws JsonProcessingException {
        inventoryService.getAllItems();
        return new ResponseEntity<>(new InventoryAllDto(), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<AddDeviceDto> addDevice(@RequestBody RequestInventoryDto requestInventoryDto){
        inventoryService.registerDevice(requestInventoryDto);
        return new ResponseEntity<AddDeviceDto>(new AddDeviceDto("","",HttpStatus.OK),HttpStatus.OK);
    }
}
