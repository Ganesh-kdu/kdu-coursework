package org.example;

import static org.example.inventory.InventoryStore.*;

public class Main {
    public static void main(String[]args){
        createVehiclesList();
        printVehiclesList();
        mostExpensive();
    }
}
