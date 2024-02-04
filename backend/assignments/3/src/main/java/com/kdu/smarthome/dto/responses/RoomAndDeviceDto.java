package com.kdu.smarthome.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomAndDeviceDto {
    private String message;
    private String roomsAndDevices;
    private HttpStatus httpStatus;
}