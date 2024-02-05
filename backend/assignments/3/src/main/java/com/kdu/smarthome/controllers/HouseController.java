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
/**
 * Controller for house related operations - create, add user, list etc
 */
public class HouseController {
    HouseService houseService;
    JsonUtils jsonUtils;

    public HouseController(HouseService houseService, JsonUtils jsonUtils) {
        this.houseService = houseService;
        this.jsonUtils = jsonUtils;
    }

    /**
     * Creates a house and makes user the admin of the house
     * @param houseRequestDto house details
     * @return created house object with ID
     */
    @PostMapping("")
    public ResponseEntity<HouseResponseDto> addHouse(@RequestBody HouseCreateRequestDto houseRequestDto){
        ResponseHouseDto createdHouse = new ResponseHouseDto(houseService.createHouse(houseRequestDto));
        return new ResponseEntity<>(new HouseResponseDto("Created Successfully", createdHouse, HttpStatus.CREATED), HttpStatus.OK);
    }

    /**
     * Adds a registered user to the house as a normal user, given that the current user is the house's admin
     * @param addUserDto ID of the user being added
     * @param houseId House to add user to
     * @return Operation result
     * @throws JsonProcessingException
     */
    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<AddUserResponseDto> addUser(@RequestBody AddUserDto addUserDto, @PathVariable Long houseId) throws JsonProcessingException {
        String object = houseService.addUser(houseId, addUserDto.getUsername());
        return new ResponseEntity<>(new AddUserResponseDto("User added successfully",object,HttpStatus.OK), HttpStatus.OK);
    }

    /**
     * Fetches the details of all houses in the database
     * @return Response entity of list of houses
     * @throws JsonProcessingException
     */
    @GetMapping("")
    public ResponseEntity<HouseListDto> listHouses() throws JsonProcessingException {
        List<House> houseList = houseService.getAll();
        return new ResponseEntity<>(new HouseListDto("List of all houses", jsonUtils.convertListToJsonString(houseList), HttpStatus.OK),HttpStatus.OK);
    }

    /**
     * Updates a particular house's address
     * @param houseId ID of house
     * @param newAddress New address of same house
     * @return New house details
     */
    @PutMapping("")
    public ResponseEntity<HouseUpdateDto> updateHouse(@RequestParam Long houseId, @RequestBody String newAddress){
        String newHouse = houseService.updateHouse(houseId, newAddress);
        return new ResponseEntity<>(new HouseUpdateDto("Updated address", newHouse, HttpStatus.OK), HttpStatus.OK);
    }

    /**
     * List all the rooms and devices in the given room
     * @param houseId ID of house to list components of
     * @return List of all rooms followed by all devices
     * @throws JsonProcessingException
     */
    @GetMapping("/{houseId}")
    public ResponseEntity<RoomAndDeviceDto> getHouse(@PathVariable Long houseId) throws JsonProcessingException {
        String response = houseService.getRoomsAndDevices(houseId);
        return new ResponseEntity<>(new RoomAndDeviceDto("List", response, HttpStatus.OK),HttpStatus.OK);
    }
}
