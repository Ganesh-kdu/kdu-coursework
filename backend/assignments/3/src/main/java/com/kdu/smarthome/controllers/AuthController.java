package com.kdu.smarthome.controllers;

import com.kdu.smarthome.dto.RegistrationSuccessDto;
import com.kdu.smarthome.dto.UserDto;
import com.kdu.smarthome.filter.TokenGeneratorFilter;
import com.kdu.smarthome.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    UserService userService;
    PasswordEncoder passwordEncoder;
    public AuthController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationSuccessDto> register(@RequestBody UserDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userService.addUser(userDto);
        String token = TokenGeneratorFilter.generateToken(userDto.getUsername(), "ROLE_USER");
        return new ResponseEntity<>(new RegistrationSuccessDto(token), HttpStatus.OK);
    }

    @PostMapping("/check")
    public ResponseEntity<RegistrationSuccessDto> check(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return new ResponseEntity<>(new RegistrationSuccessDto("Reached"), HttpStatus.OK);
    }
}
