package com.example.assessment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @PostMapping("/update")
    public ResponseEntity<String> updateItem(){
        return new ResponseEntity<>("Not implemented", HttpStatus.I_AM_A_TEAPOT);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addItem(){
        return new ResponseEntity<>("Not implemented", HttpStatus.I_AM_A_TEAPOT);
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteItem(){
        return new ResponseEntity<>("Not implemented", HttpStatus.I_AM_A_TEAPOT);
    }
}
