package com.example.jdbc.mapper;

import com.example.jdbc.dto.*;
import com.example.jdbc.model.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

public class DtoToModel {
    public Shift mapShiftDtoToShift(ShiftDto shiftDto) {
        Shift shift = new Shift();
        shift.setId(UUID.randomUUID());
        shift.setShiftTypeId(UUID.fromString(shiftDto.getShiftTypeId()));
        shift.setName(shiftDto.getName());
        shift.setDateStart(LocalDate.parse(shiftDto.getDateStart()));
        shift.setDateEnd(LocalDate.parse(shiftDto.getDateEnd()));
        shift.setTimeStart(Time.valueOf(shiftDto.getTimeStart()));
        shift.setTimeEnd(Time.valueOf(shiftDto.getTimeEnd()));
        shift.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        shift.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        shift.setTimeZone(shiftDto.getTimeZone());
        shift.setTenantId(UUID.fromString(shiftDto.getTenantId()));
        return shift;
    }

    public ShiftType mapShiftTypeDtoToShiftType(ShiftTypeDto shiftTypeDto) {
        ShiftType shiftType = new ShiftType();
        shiftType.setId(UUID.randomUUID());
        shiftType.setName(shiftTypeDto.getName());
        shiftType.setDescription(shiftTypeDto.getDescription());
        shiftType.setActive(shiftTypeDto.isActive());
        shiftType.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        shiftType.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        shiftType.setTimeZone(shiftTypeDto.getTimeZone());
        shiftType.setTenantId(UUID.fromString(shiftTypeDto.getTenantId()));
        return shiftType;
    }

    public ShiftUser mapShiftUserDtoToShiftUser(ShiftUserDto shiftUserDto) {
        ShiftUser shiftUser = new ShiftUser();
        shiftUser.setId(UUID.randomUUID());
        shiftUser.setShiftId(UUID.fromString(shiftUserDto.getShiftId()));
        shiftUser.setUserId(UUID.fromString(shiftUserDto.getUserId()));
        shiftUser.setTenantId(UUID.fromString(shiftUserDto.getTenantId()));
        return shiftUser;
    }

    public Tenant mapTenantDtoToTenant(TenantDto tenantDto) {
        Tenant tenant = new Tenant();
        tenant.setId(UUID.randomUUID());
        tenant.setName(tenantDto.getName());
        tenant.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        tenant.setCreatedBy(tenantDto.getCreatedBy());
        tenant.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        tenant.setUpdatedBy(tenantDto.getUpdatedBy());
        return tenant;
    }

    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUserName(userDto.getUserName());
        user.setLoggedIn(userDto.isLoggedIn());
        user.setTimeZone(userDto.getTimeZone());
        user.setTenantId(UUID.fromString(userDto.getTenantId()));
        return user;
    }
}
