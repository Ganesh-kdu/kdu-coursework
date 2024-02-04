package com.kdu.smarthome.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    @PostMapping("")
    public void addRoom(@RequestParam Long id, @RequestBody String roomName){

    }
}
