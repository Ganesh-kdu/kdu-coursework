package com.kdu.smarthome.controllers;

import com.kdu.smarthome.dto.requests.ResponseRoomDto;
import com.kdu.smarthome.dto.requests.RoomResponseDto;
import com.kdu.smarthome.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
/**
 * Controller for room operations - adding a room
 */
public class RoomController {
    RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Endpoint to add a new room to the given house
     * @param houseId ID of house to add room to
     * @param roomName Name of new room
     * @return Room object with ID
     */
    @PostMapping("")
    public ResponseEntity<RoomResponseDto> addRoom(@RequestParam Long houseId, @RequestBody String roomName){
        ResponseRoomDto room = new ResponseRoomDto(roomService.addRoom(houseId, roomName));
        return new ResponseEntity<>(new RoomResponseDto("Added", room, HttpStatus.OK), HttpStatus.OK);
    }
}
