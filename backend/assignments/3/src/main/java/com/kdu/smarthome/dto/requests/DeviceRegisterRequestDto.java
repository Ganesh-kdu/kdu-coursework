package com.kdu.smarthome.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceRegisterRequestDto {
    @JsonProperty("kickston_id")
    private String kickStonId;

    @JsonProperty("device_username")
    private String deviceUsername;

    @JsonProperty("device_password")
    private String devicePassword;

    @Data
    @AllArgsConstructor
    public static class UserDto {
        private String username;
        private String password;
        private String name;
        private String firstname;
        private String lastname;
        private String emailId;
    }
}
