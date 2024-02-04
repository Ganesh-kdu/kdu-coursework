package com.kdu.smarthome.dto;

import com.kdu.smarthome.entities.House;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseListDto {
    private String message;
    private List<House> houses;
    private HttpStatus httpStatus;
}
