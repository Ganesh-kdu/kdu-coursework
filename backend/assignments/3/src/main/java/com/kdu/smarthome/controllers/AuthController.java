package com.kdu.smarthome.controllers;

import com.kdu.smarthome.dto.requests.DeviceRegisterRequestDto;
import com.kdu.smarthome.dto.requests.UserDto;
import com.kdu.smarthome.dto.responses.RegistrationSuccessDto;
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
/**
 * Controller for authentication paths
 */
public class AuthController {
    UserService userService;
    PasswordEncoder passwordEncoder;
    public AuthController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Take user details and register. Returns a JWT auth token.
     * @param userDto User details
     * @return JWT token string
     */
    @PostMapping("/register")
    public ResponseEntity<RegistrationSuccessDto> register(@RequestBody UserDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userService.addUser(userDto);
        String token = TokenGeneratorFilter.generateToken(userDto.getUsername(), "ROLE_USER");
        return new ResponseEntity<>(new RegistrationSuccessDto("Registration success",token), HttpStatus.OK);
    }

    /**
     * Endpoint to check if authentication is working. Returns the token's username
     * @return Username inside the token
     */
    @PostMapping("/check")
    public ResponseEntity<RegistrationSuccessDto> check(){
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(new RegistrationSuccessDto("Reached", user), HttpStatus.OK);
    }
}
