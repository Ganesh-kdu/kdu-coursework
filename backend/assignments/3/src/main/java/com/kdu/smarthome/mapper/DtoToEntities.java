package com.kdu.smarthome.mapper;

import com.kdu.smarthome.dto.UserDto;
import com.kdu.smarthome.entities.User;

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
}
