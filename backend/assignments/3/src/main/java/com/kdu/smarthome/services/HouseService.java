package com.kdu.smarthome.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.HouseCreateRequestDto;
import com.kdu.smarthome.entities.CompositeKey;
import com.kdu.smarthome.entities.House;
import com.kdu.smarthome.entities.Residents;
import com.kdu.smarthome.entities.User;
import com.kdu.smarthome.mapper.DtoToEntities;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.ResidentRepository;
import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.utilities.JsonUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class HouseService {
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final ResidentRepository residentRepository;
    private final JsonUtils jsonUtils;

    public HouseService(HouseRepository houseRepository, UserRepository userRepository, ResidentRepository residentRepository, JsonUtils jsonUtils) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.residentRepository = residentRepository;
        this.jsonUtils = jsonUtils;
    }

    public House createHouse(HouseCreateRequestDto houseDto){
        House house = DtoToEntities.dtoToHouse(houseDto);
        User user = userRepository.findById((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).get();
        House result = houseRepository.save(house);
        CompositeKey residentKey = new CompositeKey(result,user);
        Residents residentRecord = new Residents(residentKey, Boolean.TRUE);
        residentRepository.save(residentRecord);
        return result;
    }

    public String addUser(Long houseId, String username) throws JsonProcessingException {
        User user = userRepository.findById((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).get();
        House house = houseRepository.findById(houseId).get();
        CompositeKey residentKey = new CompositeKey(house,user);
        Residents residentRecord = residentRepository.findById(residentKey).get();
        if (residentRecord.getAdmin()){
            User newUser = userRepository.findById(username).get();
            CompositeKey newUserResidentKey = new CompositeKey(house,newUser);
            Residents newResidentRecord = new Residents(newUserResidentKey, Boolean.FALSE);
            return jsonUtils.convertObjToJsonString(residentRepository.save(newResidentRecord));
        }else {
            throw new IllegalCallerException("You are not an admin for this house");
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

}