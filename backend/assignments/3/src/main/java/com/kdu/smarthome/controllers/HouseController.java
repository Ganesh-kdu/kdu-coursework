package com.kdu.smarthome.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.requests.AddUserDto;
import com.kdu.smarthome.dto.requests.HouseCreateRequestDto;
import com.kdu.smarthome.dto.responses.*;
import com.kdu.smarthome.entities.House;
import com.kdu.smarthome.services.HouseService;
import com.kdu.smarthome.utilities.JsonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/house")
public class HouseController {
    HouseService houseService;
    JsonUtils jsonUtils;

    public HouseController(HouseService houseService, JsonUtils jsonUtils) {
        this.houseService = houseService;
        this.jsonUtils = jsonUtils;
    }

    @PostMapping("")
    public ResponseEntity<HouseResponseDto> addHouse(@RequestBody HouseCreateRequestDto houseRequestDto){
        ResponseHouseDto createdHouse = new ResponseHouseDto(houseService.createHouse(houseRequestDto));
        return new ResponseEntity<>(new HouseResponseDto("Created Successfully", createdHouse, HttpStatus.CREATED), HttpStatus.OK);
    }

    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<AddUserResponseDto> addUser(@RequestBody AddUserDto addUserDto, @PathVariable Long houseId) throws JsonProcessingException {
        String object = houseService.addUser(houseId, addUserDto.getUsername());
        return new ResponseEntity<>(new AddUserResponseDto("User added successfully",object,HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<HouseListDto> listHouses() throws JsonProcessingException {
        List<House> houseList = houseService.getAll();
        return new ResponseEntity<>(new HouseListDto("List of all houses", jsonUtils.convertListToJsonString(houseList), HttpStatus.OK),HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<HouseUpdateDto> updateHouse(@RequestParam Long houseId, @RequestBody String newAddress){
        String newHouse = houseService.updateHouse(houseId, newAddress);
        return new ResponseEntity<>(new HouseUpdateDto("Updated address", newHouse, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/{houseId}")
    public ResponseEntity<RoomAndDeviceDto> getHouse(@PathVariable Long houseId) throws JsonProcessingException {
        String response = houseService.getRoomsAndDevices(houseId);
        return new ResponseEntity<>(new RoomAndDeviceDto("List", response, HttpStatus.OK),HttpStatus.OK);
    }
}
