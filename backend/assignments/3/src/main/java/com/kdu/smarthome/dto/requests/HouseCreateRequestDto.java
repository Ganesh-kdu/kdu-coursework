package com.kdu.smarthome.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseCreateRequestDto {
    @JsonProperty("address")
    private String address;
    @JsonProperty("house_name")
    private String houseName;
}
