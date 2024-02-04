package com.kdu.smarthome.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDeviceRoomDto {
    private Long houseId;
    private Long roomId;
    private String kickstonId;
}
