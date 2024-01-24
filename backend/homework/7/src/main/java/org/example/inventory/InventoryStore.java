package org.example.inventory;

import org.example.entities.Vehicle;
import org.example.logging.Log;
import org.example.services.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class InventoryStore {
    private static List<Vehicle> factoryOneVehicles;
    private static List<Vehicle> factoryTwoVehicles;
    private static AnnotationConfigApplicationContext newContext;
    private InventoryStore(){}
    /**
     * Creates context using VehicleService class and sets the vehicles list
     * @see VehicleService
     */
    public static void createVehiclesList(){
        newContext = new AnnotationConfigApplicationContext(VehicleService.class);
        factoryOneVehicles = newContext.getBean("factoryOne",List.class);
        factoryTwoVehicles = newContext.getBean("factoryTwo",List.class);
    }
    /**
     * Printer function for the member list of Vehicles
     */
    public static void printVehiclesList() {
        Log.customLogger("Prices of Cars from factory 1: ","INFO");
        for (Vehicle vehicle : factoryOneVehicles)
            Log.customLogger(vehicle.getPrice(),"INFO");
        Log.customLogger("Prices of Cars from factory 2: ","INFO");
        for (Vehicle vehicle : factoryTwoVehicles)
            Log.customLogger(vehicle.getPrice(),"INFO");
    }

    /**
     * Gets the most expensive Vehicle bean from the context
     */
    public static void mostExpensive(){
        Log.customLogger("Most expensive car","INFO");
        Vehicle mostExpensiveVehicle = newContext.getBean(Vehicle.class);
        Log.customLogger(mostExpensiveVehicle.getPrice(),"INFO");
    }
}
