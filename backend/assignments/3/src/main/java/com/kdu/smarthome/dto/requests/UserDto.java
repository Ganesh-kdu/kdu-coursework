package com.kdu.smarthome.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String name;
    private String firstname;
    private String lastname;
    private String emailId;
}