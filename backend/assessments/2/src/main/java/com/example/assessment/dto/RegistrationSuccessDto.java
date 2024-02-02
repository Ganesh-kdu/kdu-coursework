package com.example.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationSuccessDto {
    private String username;
    private String email;
    private String role;
}
