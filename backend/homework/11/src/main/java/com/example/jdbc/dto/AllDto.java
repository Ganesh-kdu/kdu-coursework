package com.example.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllDto {
    private ShiftDto shiftDTO;
    private ShiftTypeDto  shiftTypeDTO;
    private TenantDto tenantDto;
    private ShiftUserDto shiftUserDTO;
    private UserDto userDto;
}
