package com.example.jdbc.services;


import com.example.jdbc.dao.ShiftUserDao;
import com.example.jdbc.dto.ShiftUserDto;
import com.example.jdbc.mapper.DtoToModel;
import com.example.jdbc.model.ShiftUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShiftUserService {
    ShiftUserDao shiftUserDao;
    DtoToModel dtoUtil;

    public ShiftUserService(ShiftUserDao shiftUserDao, DtoToModel dtoUtil){
        this.shiftUserDao = shiftUserDao;
        this.dtoUtil = dtoUtil;
    }
    public String addShiftUser(ShiftUserDto shiftUserDto){
        ShiftUser shiftUser = dtoUtil.mapShiftUserDtoToShiftUser(shiftUserDto);
        shiftUserDao.saveShiftUser(shiftUser);
        return shiftUser.getId().toString();
    }

    public ShiftUser getShiftUserById(UUID id){
        return shiftUserDao.getShiftUserById(id);
    }

}