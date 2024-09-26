package org.example;

import org.example.beans.Vehicle;
import org.example.logging.Log;
import org.example.services.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    private static List<Vehicle> vehicles;
    private static AnnotationConfigApplicationContext newContext;

    /**
     * Creates context using VehicleService class and sets the vehicles list
     * @see org.example.services.VehicleService
     */
    public static void createVehiclesList(){
        newContext = new AnnotationConfigApplicationContext(VehicleService.class);
        vehicles = newContext.getBean(List.class);
    }
    /**
     * Printer function for the member list of Vehicles
     */
    public static void printVehiclesList() {
        Log.customLogger("Prices of Cars: ","INFO");
        for (Vehicle vehicle : vehicles)
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
    public static void main(String[]args){
        createVehiclesList();
        printVehiclesList();
        mostExpensive();
    }
}
