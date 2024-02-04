package com.kdu.smarthome.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.entities.*;
import com.kdu.smarthome.exceptions.custom.NotFoundException;
import com.kdu.smarthome.repository.*;
import com.kdu.smarthome.utilities.JsonUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    RoomRepository roomRepository;
    UserRepository userRepository;
    ResidentRepository residentRepository;
    HouseRepository houseRepository;
    DeviceRepository deviceRepository;
    JsonUtils jsonUtils;

    public RoomService(RoomRepository roomRepository, UserRepository userRepository, ResidentRepository residentRepository, HouseRepository houseRepository, DeviceRepository deviceRepository, JsonUtils jsonUtils) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.residentRepository = residentRepository;
        this.houseRepository = houseRepository;
        this.deviceRepository = deviceRepository;
        this.jsonUtils = jsonUtils;
    }

    public Room addRoom(Long houseId, String roomName)  {
        Optional<House> neededHouse = houseRepository.findById(houseId);
        if(neededHouse.isEmpty()){
            throw new NotFoundException("House doesn't exist");
        }
        Optional<User> checkUser = userRepository.findById((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (checkUser.isEmpty()){
            throw new IllegalCallerException("User not found");
        }
        User user = checkUser.get();
        House house = neededHouse.get();

        CompositeKey checkAdminKey = new CompositeKey(house, user);
        Optional<Residents> residentRecord = residentRepository.findById(checkAdminKey);

        if (residentRecord.isEmpty() || residentRecord.get().getAdmin().equals(Boolean.FALSE)){
            throw new IllegalCallerException("User not authorized to add rooms");
        }
        Room room = new Room();
        room.setName(roomName);
        room.setHouse(house);
        return roomRepository.save(room);
    }
}
