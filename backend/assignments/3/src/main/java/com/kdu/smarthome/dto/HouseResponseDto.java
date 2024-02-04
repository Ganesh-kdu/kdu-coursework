package com.kdu.smarthome.dto;

import com.kdu.smarthome.entities.House;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseResponseDto {
    private String message;
    private House house;
    private HttpStatus httpStatus;
}
