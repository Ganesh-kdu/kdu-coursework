package com.kdu.smarthome.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserResponseDto {
    private String message;
    private String object;
    private HttpStatus httpStatus;
}