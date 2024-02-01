package com.example.jdbc.controller;

import com.example.jdbc.dto.*;
import com.example.jdbc.model.Shift;
import com.example.jdbc.model.ShiftType;
import com.example.jdbc.model.ShiftUser;
import com.example.jdbc.model.User;
import com.example.jdbc.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class Controller {

    UserService userService;
    ShiftTypeService shiftTypeService;
    ShiftUserService shiftUserService;
    ShiftService shiftService;
    TenantService tenantService;

    public Controller(UserService userService, ShiftTypeService shiftTypeService, ShiftUserService shiftUserService, ShiftService shiftService, TenantService tenantService){
        this.shiftService=shiftService;
        this.tenantService=tenantService;
        this.shiftTypeService=shiftTypeService;
        this.shiftUserService=shiftUserService;
        this.userService=userService;
    }
    @GetMapping("/user/all")
    public ResponseEntity<List<User>> getAllUser(){
        try {
            List<User> users = userService.getAllUser();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/user/search")
    public ResponseEntity<List<User>> getUserByName(@RequestParam("name") String name){
        try{
            List<User> users = userService.getUserByName(name);
            return ResponseEntity.ok(users);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/search/{id}")
    public User getUserById(@PathVariable String id){
        UUID uid = UUID.fromString(id);
        return  userService.getUserById(uid);
    }

    @PostMapping("/add/user")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
        return ResponseEntity.ok("user addedd succes");
    }

    @PutMapping("/update/user/{id}")
    public int updateUserName(@PathVariable UUID id, @RequestBody UserDto userDto){
        return userService.updateUser(id, userDto.getUserName());
    }


    @PostMapping("/add/shiftType")
    public ResponseEntity<String> addShiftType(@RequestBody ShiftTypeDto shiftTypeDto){
        String message = "Added shift type";
        shiftTypeService.addShiftType(shiftTypeDto);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/shiftType/search/{id}")
    public ShiftType getShiftTypeById(@PathVariable UUID id){
        return shiftTypeService.getShiftTypeById(id);
    }


    @PostMapping("/add/shiftUser")
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUserDto shiftUserDto){
        String message = "Add the shift user success";
        shiftUserService.addShiftUser(shiftUserDto);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/shiftUser/search/{id}")
    public ShiftUser getShiftUserById(@PathVariable  UUID id){
        return shiftUserService.getShiftUserById(id);
    }



    @PostMapping("/add/shift")
    public ResponseEntity<String> addShift(@RequestBody ShiftDto shiftDto){
        String message = "add the shift user success";
        shiftService.addShift(shiftDto);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/shift/search/{id}")
    public Shift getShiftById(@PathVariable UUID id){
        return shiftService.getShiftById(id);
    }

    @PostMapping("/add/tenant")
    public ResponseEntity<String> addTenant(@RequestBody TenantDto tenantDto)
    {
        tenantService.addTenant(tenantDto);
        return ResponseEntity.ok("Tenant Added Successfully");
    }

}