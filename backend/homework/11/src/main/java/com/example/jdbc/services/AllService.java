package com.example.jdbc.services;

import com.example.jdbc.dto.AllDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AllService {

    ShiftService shiftService;
    ShiftTypeService shiftTypeService;
    ShiftUserService shiftUserService;
    UserService userService;
    TenantService tenantService;

    public AllService(ShiftService shiftService, ShiftTypeService shiftTypeService, ShiftUserService shiftUserService, UserService userService, TenantService tenantService) {
        this.shiftService = shiftService;
        this.shiftTypeService = shiftTypeService;
        this.shiftUserService = shiftUserService;
        this.userService = userService;
        this.tenantService = tenantService;
    }

    @Transactional()
    public void add(AllDto allDto){
        tenantService.addTenant(allDto.getTenantDto());
        shiftTypeService.addShiftType(allDto.getShiftTypeDTO());
        shiftService.addShift(allDto.getShiftDTO());
        userService.addUser(allDto.getUserDto());
        shiftUserService.addShiftUser(allDto.getShiftUserDTO());
    }
}

