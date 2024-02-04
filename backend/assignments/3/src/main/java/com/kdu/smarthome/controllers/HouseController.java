package com.kdu.smarthome.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.*;
import com.kdu.smarthome.entities.House;
import com.kdu.smarthome.services.HouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/house")
public class HouseController {
    HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping("")
    public ResponseEntity<HouseResponseDto> addHouse(@RequestBody HouseCreateRequestDto houseRequestDto){
        House createdHouse =houseService.createHouse(houseRequestDto);
        return new ResponseEntity<>(new HouseResponseDto("Created Successfully", createdHouse, HttpStatus.CREATED), HttpStatus.OK);
    }

    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<AddUserResponseDto> addUser(@RequestBody AddUserDto addUserDto, @PathVariable Long houseId) throws JsonProcessingException {
        String object = houseService.addUser(houseId, addUserDto.getUsername());
        return new ResponseEntity<>(new AddUserResponseDto("User added successfully",object,HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<HouseListDto> listHouses(){
        List<House> houseList = houseService.getAll();
        return new ResponseEntity<>(new HouseListDto("List of all houses", houseList, HttpStatus.OK),HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<HouseUpdateDto> updateHouse(@RequestParam Long houseId, @RequestBody String newAddress){
        String newHouse = houseService.updateHouse(houseId, newAddress);
        return new ResponseEntity<>(new HouseUpdateDto("Updated address", newHouse, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/api/v1/house/{houseId}")
    public ResponseEntity<String> getHouse(@PathVariable Long houseId){
        return new ResponseEntity<>("IDK",HttpStatus.OK);
    }
}
