package com.example.sbhandson1.repository;

import com.example.sbhandson1.dto.VehicleResponseDto;
import com.example.sbhandson1.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
@Repository
public class VehicleInventory {
    private HashMap<Integer, Vehicle> vehicleList;
    public VehicleInventory() {
        this.vehicleList = new HashMap<>();
    }
    public VehicleResponseDto createVehicle(Vehicle vehicle) {
        vehicleList.put(vehicle.getId(), vehicle);
        return new VehicleResponseDto(vehicle, "Vehicle created successfully");
    }
    public Vehicle getVehicle(int id) {
        if (vehicleList.get(id)!=null){
                return new Vehicle(vehicleList.get(id).getId(), vehicleList.get(id).getCompany(), vehicleList.get(id).getYear(), vehicleList.get(id).getColor(), vehicleList.get(id).getPrice());
        }
        return null;
    }
    public VehicleResponseDto updateVehicle(int id, Vehicle vehicle) {
            if(vehicleList.computeIfPresent(id,(k,v)->vehicle) != null) {
                return new VehicleResponseDto(vehicle, "Vehicle updated successfully");
            }

        return new VehicleResponseDto(null, "Vehicle not found");
    }

    public void deleteVehicle(int id) {
            if(vehicleList.get(id) != null) {
                vehicleList.remove(id);
            }
    }

    public VehicleResponseDto getMostOrLeastExpensiveVehicle(String sortDirection) {
        Vehicle vehicle = null;
        if (sortDirection.equals("asc")) {
            vehicle = vehicleList.values().stream().max(Comparator.comparingInt(Vehicle::getPrice)).get();
        } else if (sortDirection.equals("desc")) {
            vehicle = vehicleList.values().stream().min(Comparator.comparingInt(Vehicle::getPrice)).get();
        }
        return new VehicleResponseDto(vehicle, "Vehicle found");
    }
}
