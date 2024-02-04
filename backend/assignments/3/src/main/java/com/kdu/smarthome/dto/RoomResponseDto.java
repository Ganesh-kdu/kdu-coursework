package com.kdu.smarthome.dto;

import com.kdu.smarthome.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDto {
    private String message;
    private ResponseRoomDto room;
    private HttpStatus httpStatus;
}
