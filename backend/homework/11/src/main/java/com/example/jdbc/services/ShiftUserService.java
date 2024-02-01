package com.example.jdbc.services;


import com.example.jdbc.dao.ShiftUserDao;
import com.example.jdbc.dto.ShiftUserDto;
import com.example.jdbc.model.ShiftUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShiftUserService {
    ShiftUserDao shiftUserDao;

    public ShiftUserService(ShiftUserDao shiftUserDao){
        this.shiftUserDao = shiftUserDao;
    }
    public void addShiftUser(ShiftUserDto shiftUserDto){
        ShiftUser shiftUser = mapShiftUserDtoToShiftUser(shiftUserDto);
        shiftUserDao.saveShiftUser(shiftUser);
    }

    public ShiftUser mapShiftUserDtoToShiftUser(ShiftUserDto shiftUserDto) {
        ShiftUser shiftUser = new ShiftUser();
        shiftUser.setId(UUID.randomUUID());
        shiftUser.setShiftId(UUID.fromString(shiftUserDto.getShiftId()));
        shiftUser.setUserId(UUID.fromString(shiftUserDto.getUserId()));
        shiftUser.setTenantId(UUID.fromString(shiftUserDto.getTenantId()));
        return shiftUser;
    }

    public ShiftUser getShiftUserById(UUID id){
        return shiftUserDao.getShiftUserById(id);
    }

}