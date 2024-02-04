package com.kdu.smarthome.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.ResponseRoomDto;
import com.kdu.smarthome.dto.RoomResponseDto;
import com.kdu.smarthome.entities.Room;
import com.kdu.smarthome.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("")
    public ResponseEntity<RoomResponseDto> addRoom(@RequestParam Long houseId, @RequestBody String roomName){
        ResponseRoomDto room = new ResponseRoomDto(roomService.addRoom(houseId, roomName));
        return new ResponseEntity<>(new RoomResponseDto("Added", room, HttpStatus.OK), HttpStatus.OK);
    }
}
