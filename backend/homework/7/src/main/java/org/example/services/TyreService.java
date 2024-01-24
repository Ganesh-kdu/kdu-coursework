package org.example.services;

import org.example.entities.Tyre;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class TyreService {
    @Bean(name = "mrf")
    Tyre tyre1(){
        return new Tyre("MRF",1000);
    }

    @Bean(name = "bridgestone")
    Tyre tyre2(){
        return new Tyre("Bridgestone",10000);
    }
}
