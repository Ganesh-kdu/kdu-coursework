package com.kdu.smarthome.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseResponseDto {
    private String message;
    @JsonProperty("house")
    private ResponseHouseDto house;
    private HttpStatus httpStatus;
}
