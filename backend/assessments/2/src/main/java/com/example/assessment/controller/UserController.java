package com.example.assessment.controller;

import com.example.assessment.dto.UserDto;
import com.example.assessment.exceptions.custom.NoUserFoundException;
import com.example.assessment.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UsersRepository/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/update-profile")
    public ResponseEntity<String> updateProfile(){
        return new ResponseEntity<>("Not implemented", HttpStatus.I_AM_A_TEAPOT);
    }
    @PostMapping("/update-address")
    public ResponseEntity<String> updateAddress(){
        return new ResponseEntity<>("Not implemented", HttpStatus.I_AM_A_TEAPOT);
    }
    @PostMapping("/add-address")
    public ResponseEntity<String> addAddress(){
        return new ResponseEntity<>("Not implemented", HttpStatus.I_AM_A_TEAPOT);
    }
    @PostMapping("/add-cart")
    public ResponseEntity<String> updateItem(){
        return new ResponseEntity<>("Not implemented", HttpStatus.I_AM_A_TEAPOT);
    }
    @PostMapping("/view-cart")
    public ResponseEntity<String> viewCart(){
        return new ResponseEntity<>("Not implemented", HttpStatus.I_AM_A_TEAPOT);
    }
    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(){
        return new ResponseEntity<>("Not implemented", HttpStatus.I_AM_A_TEAPOT);
    }
    @PostMapping("/delete-cart")
    public ResponseEntity<String> deleteCart(){
        return new ResponseEntity<>("Not implemented", HttpStatus.I_AM_A_TEAPOT);
    }

}