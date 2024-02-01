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
    AllService allService;

    public Controller(UserService userService, ShiftTypeService shiftTypeService, ShiftUserService shiftUserService, ShiftService shiftService, TenantService tenantService, AllService allService){
        this.shiftService=shiftService;
        this.tenantService=tenantService;
        this.shiftTypeService=shiftTypeService;
        this.shiftUserService=shiftUserService;
        this.userService=userService;
        this.allService=allService;
    }

    @PostMapping("/add/shiftType")
    public ResponseEntity<String> addShiftType(@RequestBody ShiftTypeDto shiftTypeDto){
        String id = shiftTypeService.addShiftType(shiftTypeDto);
        return ResponseEntity.ok("Shift type added successfully with ID = " + id);
    }

    @PostMapping("/add/shift")
    public ResponseEntity<String> addShift(@RequestBody ShiftDto shiftDto){
        String id = shiftService.addShift(shiftDto);
        return ResponseEntity.ok("Shift created successfully with ID = " + id);
    }
    @PostMapping("/add/shiftUser")
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUserDto shiftUserDto){
        String id = shiftUserService.addShiftUser(shiftUserDto);
        return ResponseEntity.ok("Shift User added successfully with ID = " + id);
    }
   @PostMapping("/add/user")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto){
        String id = userService.addUser(userDto);
        return ResponseEntity.ok("user added successfully with ID = " + id);
    }

    @PostMapping("/add/tenant")
    public ResponseEntity<String> addTenant(@RequestBody TenantDto tenantDto)
    {
        String id = tenantService.addTenant(tenantDto);
        return ResponseEntity.ok("Tenant Added Successfully with ID = " + id);
    }

    @PostMapping("/add/all")
    public ResponseEntity<String> addAll(@RequestBody AllDto allDto){
        try {
            allService.add(allDto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok("All details added Successfully");
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

    @PutMapping("/update/user/{id}")
    public int updateUserName(@PathVariable UUID id, @RequestBody UserDto userDto){
        return userService.updateUser(id, userDto.getUserName());
    }

    @GetMapping("/shiftType/search/{id}")
    public ShiftType getShiftTypeById(@PathVariable UUID id){
        return shiftTypeService.getShiftTypeById(id);
    }




    @GetMapping("/shiftUser/search/{id}")
    public ShiftUser getShiftUserById(@PathVariable  UUID id){
        return shiftUserService.getShiftUserById(id);
    }





    @GetMapping("/shift/search/{id}")
    public Shift getShiftById(@PathVariable UUID id){
        return shiftService.getShiftById(id);
    }



}