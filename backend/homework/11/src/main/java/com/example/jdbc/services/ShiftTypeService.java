package com.example.jdbc.services;

import com.example.jdbc.dao.ShiftTypeDao;
import com.example.jdbc.dto.ShiftTypeDto;
import com.example.jdbc.mapper.DtoToModel;
import com.example.jdbc.model.ShiftType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class ShiftTypeService {
    ShiftTypeDao shiftTypeDao;
    public ShiftTypeService(ShiftTypeDao shiftTypeDao){
        this.shiftTypeDao = shiftTypeDao;
    }
    public String addShiftType(ShiftTypeDto shiftTypeDto){
        ShiftType shiftType = DtoToModel.mapShiftTypeDtoToShiftType(shiftTypeDto);
        shiftTypeDao.saveShiftType(shiftType);
        return shiftType.getId().toString();
    }




    public ShiftType getShiftTypeById(UUID id){
        return shiftTypeDao.getShiftTypeById(id);
    }

}