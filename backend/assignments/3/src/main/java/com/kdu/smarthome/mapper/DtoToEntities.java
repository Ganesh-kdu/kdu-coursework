package com.kdu.smarthome.mapper;

import com.kdu.smarthome.dto.HouseCreateRequestDto;
import com.kdu.smarthome.dto.UserDto;
import com.kdu.smarthome.entities.House;
import com.kdu.smarthome.entities.User;

import java.util.HashMap;

public class DtoToEntities {
    public static User dtoToUser(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmailId());
        user.setPassword(userDto.getPassword());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        return user;
    }

    public static House dtoToHouse(HouseCreateRequestDto houseDto){
        House house = new House();
        house.setHouseName(houseDto.getHouseName());
        house.setAddress(houseDto.getAddress());
        return house;
    }
}
