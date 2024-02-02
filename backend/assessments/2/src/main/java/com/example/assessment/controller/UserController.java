package com.example.assessment.controller;
import com.example.assessment.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/update-profile")
    public ResponseEntity<String> updateProfile(){
        return new ResponseEntity<>("Not implemented", HttpStatus.NO_CONTENT);
    }
    @PostMapping("/add-address")
    public ResponseEntity<String> addAddress(){
        return new ResponseEntity<>("Not implemented", HttpStatus.NO_CONTENT);
    }
    @PostMapping("/add-cart")
    public ResponseEntity<String> updateItem(){
        return new ResponseEntity<>("Not implemented", HttpStatus.NO_CONTENT);
    }
    @PostMapping("/view-cart")
    public ResponseEntity<String> viewCart(){
        return new ResponseEntity<>("Not implemented", HttpStatus.NO_CONTENT);
    }
    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(){
        return new ResponseEntity<>("Not implemented", HttpStatus.NO_CONTENT);
    }
    @PostMapping("/delete-cart")
    public ResponseEntity<String> deleteCart(){
        return new ResponseEntity<>("Not implemented", HttpStatus.NO_CONTENT);
    }

}