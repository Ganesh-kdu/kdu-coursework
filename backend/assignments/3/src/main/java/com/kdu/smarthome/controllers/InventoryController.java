package com.kdu.smarthome.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.responses.AddDeviceResponseDto;
import com.kdu.smarthome.dto.responses.InventoryAllDto;
import com.kdu.smarthome.dto.requests.RequestInventoryDto;
import com.kdu.smarthome.services.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
/**
 * Controller to manage device inventory - add and get
 */
public class InventoryController {
    InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * List all devices from inventory
     * @return List of devices
     * @throws JsonProcessingException
     */
    @GetMapping("")
    public ResponseEntity<InventoryAllDto> getAllItems() throws JsonProcessingException {
        String devices = inventoryService.getAllItems();
        return new ResponseEntity<>(new InventoryAllDto(devices,HttpStatus.OK), HttpStatus.OK);
    }

    /**
     * Add a new device to the inventory
     * @param requestInventoryDto Device details
     * @return Operation result
     * @throws JsonProcessingException
     */
    @PostMapping("")
    public ResponseEntity<AddDeviceResponseDto> addDevice(@RequestBody RequestInventoryDto requestInventoryDto) throws JsonProcessingException {
        String object = inventoryService.registerDevice(requestInventoryDto);
        return new ResponseEntity<>(new AddDeviceResponseDto("Added device",object,HttpStatus.OK),HttpStatus.OK);
    }
}
