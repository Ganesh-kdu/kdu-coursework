package com.kdu.smarthome.dto;

import com.kdu.smarthome.entities.House;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHouseDto {
    private String id;
    private String address;
    private String houseName;
    public ResponseHouseDto(House house){
        this.address=house.getAddress();
        this.houseName=house.getHouseName();
        this.id=house.getId().toString();
    }

}