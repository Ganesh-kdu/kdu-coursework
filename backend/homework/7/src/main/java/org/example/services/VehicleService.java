package org.example.services;

import jakarta.annotation.PostConstruct;
import org.example.entities.Speaker;
import org.example.entities.Tyre;
import org.example.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Import({TyreService.class, SpeakerService.class})
public class VehicleService {
    private final Tyre mrfTyre;
    private final Tyre bridgestoneTyre;
    private final Speaker sonySpeaker;
    private final Speaker boseSpeaker;
    protected static final List<Vehicle> factoryOneVehicle = new ArrayList<>();
    protected static final List<Vehicle> factoryTwoVehicle = new ArrayList<>();

    @Bean(name="factoryOne")
    List<Vehicle> vehicles1(){
        return factoryOneVehicle;
    }

    @Bean(name="factoryTwo")
    List<Vehicle> vehicles2(){
        return factoryTwoVehicle;
    }

    /**
     * Been for the most expensive vehicle in the list
     * @return Vehicle
     */
    @Bean
    Vehicle getMostExpensive(){
        List<Vehicle> allVehicles = new ArrayList<>();
        allVehicles.addAll(factoryOneVehicle);
        allVehicles.addAll(factoryTwoVehicle);

        Vehicle mostExpensiveVehicle = allVehicles.get(0);
        for (Vehicle vehicle: allVehicles){
            if(vehicle.getPrice()>mostExpensiveVehicle.getPrice()){
                mostExpensiveVehicle = vehicle;
            }
        }
        return mostExpensiveVehicle;
    }

    @Autowired
    VehicleService(@Qualifier("mrf") Tyre mrfTyre,
                   @Qualifier("bridgestone") Tyre bridgestoneTyre,
                   @Qualifier("sony") Speaker sonySpeaker,
                   @Qualifier("bose") Speaker boseSpeaker){
        this.mrfTyre = mrfTyre;
        this.boseSpeaker = boseSpeaker;
        this.bridgestoneTyre = bridgestoneTyre;
        this.sonySpeaker = sonySpeaker;
    }

    /**
     * Post constructor to create a list of cars with different kinds of tyres and speakers
     * @see Vehicle
     * @see org.example.services.TyreService
     * @see org.example.services.SpeakerService
     */
    @PostConstruct
    void postConstruct(){
        genFactoryOne();
        genFactoryTwo();
    }
    private void genFactoryOne(){
        for(int i=0;i<10;i++){
            if(i%4==0)
                factoryOneVehicle.add(new Vehicle(mrfTyre,sonySpeaker,50000));
            else if (i%4==1)
                factoryOneVehicle.add(new Vehicle(mrfTyre,boseSpeaker,50000));
            else if (i%4==2)
                factoryOneVehicle.add(new Vehicle(bridgestoneTyre,sonySpeaker,50000));
            else
                factoryOneVehicle.add(new Vehicle(bridgestoneTyre,boseSpeaker,50000));
        }
    }
    private void genFactoryTwo(){
        for(int i=0;i<16;i++){
            if(i%4==0)
                factoryTwoVehicle.add(new Vehicle(mrfTyre,sonySpeaker,60000));
            else if (i%4==1)
                factoryTwoVehicle.add(new Vehicle(mrfTyre,boseSpeaker,60000));
            else if (i%4==2)
                factoryTwoVehicle.add(new Vehicle(bridgestoneTyre,sonySpeaker,60000));
            else
                factoryTwoVehicle.add(new Vehicle(bridgestoneTyre,boseSpeaker,60000));
        }
    }
}
