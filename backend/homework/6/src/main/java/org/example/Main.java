package org.example;

import org.example.beans.Vehicle;
import org.example.services.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[]args){
        AnnotationConfigApplicationContext newContext = new AnnotationConfigApplicationContext(VehicleService.class);
        List<Vehicle> l = newContext.getBean(List.class);
        for(Vehicle a:l)
        System.out.println(a.getPrice());
    }
}
