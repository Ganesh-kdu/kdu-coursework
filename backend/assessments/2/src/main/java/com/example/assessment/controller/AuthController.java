package com.example.assessment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @PostMapping("/test")
    public String test(){
        return "done";
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("test login", HttpStatus.CREATED);

    }
    @PostMapping("/register")
    public ResponseEntity<String> register(){
        return new ResponseEntity<>("test login", HttpStatus.CREATED);
    }

}