package com.kdu.smarthome.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestInventoryDto {
    @JsonProperty("kickston_id")
    private String kickstoneId;
    @JsonProperty("device_username")
    private String deviceUsername;
    @JsonProperty("device_password")
    private String devicePassword;
    @JsonProperty("manufacture_date_time")
    private LocalDateTime manufactureDateTime;
    @JsonProperty("manufacture_factory_place")
    private String manufactureFactoryPlace;

}