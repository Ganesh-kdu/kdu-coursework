package com.kdu.smarthome.controllers;

import com.kdu.smarthome.dto.requests.DeviceRegisterRequestDto;
import com.kdu.smarthome.dto.requests.AddDeviceRoomDto;
import com.kdu.smarthome.services.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/device")
public class DeviceController {
    RegistrationService registrationService;

    public DeviceController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerDevice(@RequestBody DeviceRegisterRequestDto deviceDto){
        registrationService.register(deviceDto);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToRoom(@RequestBody AddDeviceRoomDto addDeviceRoomDto){
        registrationService.add(addDeviceRoomDto);
        return new ResponseEntity<>("Done",HttpStatus.OK);
    }

}
