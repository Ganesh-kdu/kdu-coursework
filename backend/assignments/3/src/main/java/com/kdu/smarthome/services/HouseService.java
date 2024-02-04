package com.kdu.smarthome.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.requests.HouseCreateRequestDto;
import com.kdu.smarthome.entities.CompositeKey;
import com.kdu.smarthome.entities.House;
import com.kdu.smarthome.entities.Residents;
import com.kdu.smarthome.entities.User;
import com.kdu.smarthome.exceptions.custom.NotFoundException;
import com.kdu.smarthome.mapper.DtoToEntities;
import com.kdu.smarthome.repository.*;
import com.kdu.smarthome.utilities.JsonUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseService {
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final ResidentRepository residentRepository;
    private final RoomRepository roomRepository;
    private final DeviceAllocationRepository deviceAllocationRepository;
    private final JsonUtils jsonUtils;
    private static final String NOT_AN_ADMIN
            = "You are not an admin for this house";

    public HouseService(HouseRepository houseRepository, UserRepository userRepository, ResidentRepository residentRepository, RoomRepository roomRepository, DeviceAllocationRepository deviceAllocationRepository, JsonUtils jsonUtils) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.residentRepository = residentRepository;
        this.roomRepository = roomRepository;
        this.deviceAllocationRepository = deviceAllocationRepository;
        this.jsonUtils = jsonUtils;
    }

    public House createHouse(HouseCreateRequestDto houseDto){
        House house = DtoToEntities.dtoToHouse(houseDto);
        Optional<User> optionalUser = userRepository.findById((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if(optionalUser.isEmpty()){
            throw new NotFoundException("User not found");
        }
        User user = optionalUser.get();
        House result = houseRepository.save(house);
        CompositeKey residentKey = new CompositeKey(result,user);
        Residents residentRecord = new Residents(residentKey, Boolean.TRUE);
        residentRepository.save(residentRecord);
        return result;
    }

    public String addUser(Long houseId, String username) throws JsonProcessingException {
        Optional<User> optionalUser = userRepository.findById((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (optionalUser.isEmpty()){
            throw new IllegalCallerException(NOT_AN_ADMIN);
        }
        User user = optionalUser.get();
        Optional<House> optionalHouse = houseRepository.findById(houseId);
        if(optionalHouse.isEmpty()){
            throw new NotFoundException("House not found");
        }
        House house = optionalHouse.get();
        CompositeKey residentKey = new CompositeKey(house,user);
        Optional<Residents> residentRecordCheck = residentRepository.findById(residentKey);
        if (residentRecordCheck.isEmpty()){
            throw new NotFoundException("Resident not found");
        }
        Residents residentRecord = residentRecordCheck.get();
        if (residentRecord.getAdmin().equals(Boolean.TRUE)){
            Optional<User> newUserCheck = userRepository.findById(username);
           if (newUserCheck.isEmpty()){
                throw new NotFoundException("User or house doesn't exist");
            }
           User newUser = newUserCheck.get();
            CompositeKey newUserResidentKey = new CompositeKey(house,newUser);
            Residents newResidentRecord = new Residents(newUserResidentKey, Boolean.FALSE);
            return jsonUtils.convertObjToJsonString(residentRepository.save(newResidentRecord));
        }else {
            throw new IllegalCallerException(NOT_AN_ADMIN);
        }
    }

    public List<House> getAll(){
        return houseRepository.findAll();
    }

    public String updateHouse(Long id, String address){
        House houseToUpdate = houseRepository.getReferenceById(id);
        houseToUpdate.setAddress(address);
        return houseRepository.save(houseToUpdate).toString();
    }
    public String getRoomsAndDevices(Long houseId) throws JsonProcessingException {
        Optional<House> optionalHouse = houseRepository.findById(houseId);
        if(optionalHouse.isEmpty()){
            throw new NotFoundException("House not found");
        }
        House house = optionalHouse.get();
        String rooms = jsonUtils.convertListToJsonString(roomRepository.findAllByHouse(house.getId()));
        String devices = jsonUtils.convertListToJsonString(deviceAllocationRepository.findAllByHouse(house.getId()));
        return rooms + devices;
    }
}
