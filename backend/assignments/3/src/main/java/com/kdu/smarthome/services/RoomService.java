package com.kdu.smarthome.services;

import com.kdu.smarthome.repository.*;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    RoomRepository roomRepository;
    UserRepository userRepository;
    ResidentRepository residentRepository;
    HouseRepository houseRepository;
    DeviceRepository deviceRepository;

    public RoomService(RoomRepository roomRepository, UserRepository userRepository, ResidentRepository residentRepository, HouseRepository houseRepository, DeviceRepository deviceRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.residentRepository = residentRepository;
        this.houseRepository = houseRepository;
        this.deviceRepository = deviceRepository;
    }

    public void addRoom(Long houseId, String roomName){

    }
}
