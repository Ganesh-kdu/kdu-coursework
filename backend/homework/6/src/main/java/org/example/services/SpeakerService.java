package org.example.services;

import org.example.beans.Speaker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpeakerService {
    @Bean(name = "sony")
    Speaker speaker1(){
        return new Speaker("Sony",1000);
    }

    @Bean(name = "bose")
    Speaker speaker2(){
        return new Speaker("Bose",10000);
    }
}
