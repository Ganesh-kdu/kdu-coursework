package com.example.sbhandson1.controller;

import com.example.sbhandson1.dto.VehicleRequestDto;
import com.example.sbhandson1.dto.VehicleResponseDto;
import com.example.sbhandson1.dto.VehicleUpdateDto;
import com.example.sbhandson1.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/create-vehicle")
    public ResponseEntity<VehicleResponseDto> createVehicle(@RequestBody VehicleRequestDto vehicleDto) {
        return new ResponseEntity<>(vehicleService.createVehicle(vehicleDto), HttpStatus.CREATED);
    }

    @GetMapping("/get-vehicle/{id}")
    public ResponseEntity<VehicleResponseDto> getVehicle(@PathVariable Integer id) {
        return new ResponseEntity<>(vehicleService.getVehicle(id),HttpStatus.OK);
    }

    @PutMapping("/update-vehicle/{id}")
    public ResponseEntity<VehicleResponseDto> updateVehicle(@PathVariable Integer id, @RequestBody VehicleUpdateDto vehicleUpdateDto) {
        return new ResponseEntity<>(vehicleService.updateVehicle(id, vehicleUpdateDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete-vehicle/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Integer id) {
        return new ResponseEntity<>(vehicleService.deleteVehicle(id).getMessage(),HttpStatus.OK);
    }

    @GetMapping("/expensive-vehicles")
    public ResponseEntity<VehicleResponseDto> getMostOrLeastExpensiveVehicle(@RequestParam(required = false) String sortDirection) {
        return new ResponseEntity<>(vehicleService.getMostOrLeastExpensiveVehicle(sortDirection), HttpStatus.OK);
    }
}
