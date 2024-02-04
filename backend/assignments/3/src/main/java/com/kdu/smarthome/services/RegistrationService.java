package com.kdu.smarthome.services;

import com.kdu.smarthome.dto.requests.DeviceRegisterRequestDto;
import com.kdu.smarthome.dto.requests.AddDeviceRoomDto;
import com.kdu.smarthome.entities.*;
import com.kdu.smarthome.exceptions.custom.NotFoundException;
import com.kdu.smarthome.repository.*;
import com.kdu.smarthome.utilities.JsonUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RegistrationService {
    DeviceRegistrationRepository deviceRegistrationRepository;
    ResidentRepository residentRepository;
    UserRepository userRepository;
    DeviceRepository deviceRepository;
    JsonUtils jsonUtils;
    RoomRepository roomRepository;
    DeviceAllocationRepository deviceAllocationRepository;

    public RegistrationService(DeviceRegistrationRepository deviceRegistrationRepository, ResidentRepository residentRepository, UserRepository userRepository, DeviceRepository deviceRepository, JsonUtils jsonUtils, RoomRepository roomRepository, DeviceAllocationRepository deviceAllocationRepository) {
        this.deviceRegistrationRepository = deviceRegistrationRepository;
        this.residentRepository = residentRepository;
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.jsonUtils = jsonUtils;
        this.roomRepository = roomRepository;
        this.deviceAllocationRepository = deviceAllocationRepository;
    }

    public void register(DeviceRegisterRequestDto deviceDto) {
        Optional<Device> requiredDevice = deviceRepository.findById(deviceDto.getKickStonId());
        if (requiredDevice.isEmpty()){
            throw new NotFoundException("Device doesn't exist");
        }
        Device device = requiredDevice.get();
        Optional<DeviceRegistration> checkDeviceRegistration = deviceRegistrationRepository.findById(device.getKickstonId());
        if(checkDeviceRegistration.isPresent()){
            throw new NotFoundException("Already registered");
        }

        if(!Objects.equals(device.getDevicePassword(), deviceDto.getDevicePassword())){
            throw new IllegalCallerException("Invalid credentials");
        }
        if(!Objects.equals(device.getDeviceUsername(), deviceDto.getDeviceUsername())){
            throw new NotFoundException("Invalid credentials");
        }
        Optional<User> currentUser = userRepository.findById((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (currentUser.isEmpty()){
            throw new IllegalCallerException("User not authorized");
        }
        User user = currentUser.get();
        List<Residents> residentCheck = residentRepository.findIfUserIsAdmin(user.getUsername());
        if (residentCheck.isEmpty()){
            throw new IllegalCallerException("User not an admin");
        }

        deviceRegistrationRepository.save(new DeviceRegistration(deviceDto.getKickStonId(), user));
    }

    public void add(AddDeviceRoomDto deviceDto){
        Optional<Device> requiredDevice = deviceRepository.findById(deviceDto.getKickstonId());
        if (requiredDevice.isEmpty()){
            throw new NotFoundException("Device doesn't exist");
        }
        Device device = requiredDevice.get();
        Optional<DeviceRegistration> checkDeviceRegistration = deviceRegistrationRepository.findById(device.getKickstonId());
        if(checkDeviceRegistration.isEmpty()){
            throw new NotFoundException("Not registered");
        }

        Optional<Room> possibleRoom = roomRepository.findById(deviceDto.getRoomId());
        if(possibleRoom.isEmpty()){
            throw new NotFoundException("Room doesn't exist");
        }
        Room room = possibleRoom.get();
        House house = room.getHouse();
        if (!house.getId().equals(deviceDto.getHouseId())){
            throw new NotFoundException("Room belongs to different house");
        }

        Optional<User> currentUser = userRepository.findById((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (currentUser.isEmpty()){
            throw new IllegalCallerException("User not authorized");
        }
        User user = currentUser.get();
        CompositeKey searchKey = new CompositeKey(house, user);
        Optional<Residents> typeOfResident = residentRepository.findById(searchKey);
        if (typeOfResident.isEmpty()){
            throw new IllegalCallerException("Not authorized to make changes to this house");
        }
        Optional<DeviceAllocation> allocation = deviceAllocationRepository.findById(deviceDto.getKickstonId());

        if(typeOfResident.get().getAdmin().equals(Boolean.FALSE) && allocation.isEmpty()){
            throw new IllegalCallerException("User not authorised until admin adds first");
        }
        deviceAllocationRepository.save(new DeviceAllocation(deviceDto.getKickstonId(), house, room));
    }
}
