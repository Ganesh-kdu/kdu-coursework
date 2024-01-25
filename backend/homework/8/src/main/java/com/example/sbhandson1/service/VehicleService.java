package com.example.sbhandson1.service;

import com.example.sbhandson1.dto.VehicleRequestDto;
import com.example.sbhandson1.dto.VehicleResponseDto;
import com.example.sbhandson1.entity.Vehicle;
import com.example.sbhandson1.repository.VehicleInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehicleService {

    @Autowired
    private VehicleInventory vehicleInventory;

    public VehicleResponseDto createVehicle(VehicleRequestDto vehicleRequestDto) {
        try {
            Vehicle vehicle = new Vehicle(vehicleRequestDto.getId(), vehicleRequestDto.getCompany(), vehicleRequestDto.getYear(), vehicleRequestDto.getColor(), vehicleRequestDto.getPrice());
            return vehicleInventory.createVehicle(vehicle);
        }catch (Exception e){
            return new VehicleResponseDto(null,"Invalid data, failed to create vehicle");
        }
    }
    public VehicleResponseDto getVehicle(int id) {
        Vehicle vehicle = vehicleInventory.getVehicle(id);

        if (vehicle == null) {
            return new VehicleResponseDto(null, "Vehicle found");
        }
        return new VehicleResponseDto(vehicle, "Vehicle found");
    }
    public VehicleResponseDto updateVehicle(int id, VehicleRequestDto vehicleDto) {
        Vehicle vehicle = new Vehicle(vehicleDto.getId(), vehicleDto.getCompany(), vehicleDto.getYear(), vehicleDto.getColor(), vehicleDto.getPrice());
        return vehicleInventory.updateVehicle(id, vehicle);
    }

    public VehicleResponseDto deleteVehicle(int id) {
        Vehicle vehicle = vehicleInventory.getVehicle(id);
        if (vehicle == null) {
            return new VehicleResponseDto(null, "Vehicle not found");
        }
        vehicleInventory.deleteVehicle(id);
        return new VehicleResponseDto(null, "Vehicle deleted successfully");
    }

    public VehicleResponseDto getMostOrLeastExpensiveVehicle(String sortDirection) {
        if (sortDirection == null) {
            sortDirection = "asc";
        }
        return vehicleInventory.getMostOrLeastExpensiveVehicle(sortDirection);
    }
}
