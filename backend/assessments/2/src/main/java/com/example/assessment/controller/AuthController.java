package com.example.assessment.controller;

import com.example.assessment.dto.RegistrationSuccessDto;
import com.example.assessment.dto.UserDto;
import com.example.assessment.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    UserService userService;
    PasswordEncoder passwordEncoder;
    public AuthController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/test")
    public String test(){
        return "done";
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("test login", HttpStatus.OK);

    }
    @PostMapping("/register")
    public ResponseEntity<RegistrationSuccessDto> register(@RequestBody UserDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }

}