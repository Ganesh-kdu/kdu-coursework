package org.example.services;

import jakarta.annotation.PostConstruct;
import org.example.beans.Tyre;
import org.example.beans.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Import({TyreService.class, SpeakerService.class})
public class VehicleService {
    List<Vehicle> cars;
    @Autowired
    TyreService tyreService;

    @Autowired
    SpeakerService speakerService;

    @Bean
    List<Vehicle> vehicles(){
        return cars;
    }
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
