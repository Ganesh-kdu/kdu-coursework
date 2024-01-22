package org.example.services;

import jakarta.annotation.PostConstruct;
import org.example.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Import({TyreService.class, SpeakerService.class})
public class VehicleService {
    List<Vehicle> cars;
    private final TyreService tyreService;
    private final SpeakerService speakerService;

    @Bean
    List<Vehicle> vehicles(){
        return cars;
    }

    /**
     * Been for the most expensive vehicle in the list
     * @return Vehicle
     */
    @Bean
    Vehicle getMostExpensive(){
        Vehicle mostExpensiveVehicle = cars.get(0);
        for (Vehicle vehicle: cars){
            if(vehicle.getPrice()>mostExpensiveVehicle.getPrice()){
                mostExpensiveVehicle = vehicle;
            }
        }
        return mostExpensiveVehicle;
    }
    VehicleService(){
        tyreService = new TyreService();
        speakerService = new SpeakerService();
    }

    /**
     * Post constructor to create a list of cars with different kinds of tyres and speakers
     * @see org.example.beans.Vehicle
     * @see org.example.services.TyreService
     * @see org.example.services.SpeakerService
     */
    @PostConstruct
    void postConstruct(){
        cars = new ArrayList<>();
        for(int i=0;i<10;i++){
            if(i%4==0)
                cars.add(new Vehicle(tyreService.tyre1(),speakerService.speaker1(),1000));
            else if (i%4==1)
                cars.add(new Vehicle(tyreService.tyre1(),speakerService.speaker2(),1000));
            else if (i%4==2)
                cars.add(new Vehicle(tyreService.tyre2(),speakerService.speaker1(),1000));
            else
                cars.add(new Vehicle(tyreService.tyre2(),speakerService.speaker2(),1000));
        }
    }
}
