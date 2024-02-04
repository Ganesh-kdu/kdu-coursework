package com.kdu.smarthome.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationSuccessDto {
    private String message;
    private String token;
}
