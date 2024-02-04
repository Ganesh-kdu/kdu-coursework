package com.kdu.smarthome.dto;

import com.kdu.smarthome.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRoomDto {
    private String id;
    private String houseId;
    private String roomName;
    public ResponseRoomDto(Room room){
        this.id = room.getId().toString();
        this.houseId = room.getHouse().getId().toString();
        this.roomName = room.getName();
    }
}
