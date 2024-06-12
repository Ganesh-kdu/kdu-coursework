package com.example.jdbc.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDto {
    String message;
    int statusCode;
}