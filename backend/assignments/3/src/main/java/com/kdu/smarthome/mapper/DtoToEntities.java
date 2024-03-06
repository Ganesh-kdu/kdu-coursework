package com.kdu.smarthome.mapper;

import com.kdu.smarthome.dto.requests.DeviceRegisterRequestDto;
import com.kdu.smarthome.dto.requests.HouseCreateRequestDto;
import com.kdu.smarthome.dto.requests.RequestInventoryDto;
import com.kdu.smarthome.dto.requests.UserDto;
import com.kdu.smarthome.entities.Device;
import com.kdu.smarthome.entities.House;
import com.kdu.smarthome.entities.User;


/**
 * Class with static functions to convert some DTOs to respective entities
 */
public class DtoToEntities {
    private DtoToEntities(){}

    /**
     * User DTO to User entity
     * @param userDto user details
     * @return new User object
     */
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

    /**
     * HouseDTO to House Entity without ID
     * @param houseDto House details
     * @return new House object
     */
    public static House dtoToHouse(HouseCreateRequestDto houseDto){
        House house = new House();
        house.setHouseName(houseDto.getHouseName());
        house.setAddress(houseDto.getAddress());
        return house;
    }

    /**
     * Device DTO to Device entity
     * @param deviceDto Device details
     * @return new Device object
     */
    public static Device dtoToDevice(RequestInventoryDto deviceDto){
        Device device = new Device();
        device.setDevicePassword(deviceDto.getDevicePassword());
        device.setDeviceUsername(deviceDto.getDeviceUsername());
        device.setKickstonId(deviceDto.getKickstoneId());
        device.setManufactureDateTime(deviceDto.getManufactureDateTime());
        device.setManufactureFactoryPlace(deviceDto.getManufactureFactoryPlace());
        return device;
    }
}