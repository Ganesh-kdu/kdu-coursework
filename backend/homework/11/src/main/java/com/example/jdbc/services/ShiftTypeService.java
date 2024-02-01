package com.example.jdbc.services;

import com.example.jdbc.dao.ShiftTypeDao;
import com.example.jdbc.dto.ShiftTypeDto;
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
    public void addShiftType(ShiftTypeDto shiftTypeDto){
        ShiftType shiftType = mapShiftTypeDtoToShiftType(shiftTypeDto);
        shiftTypeDao.saveShiftType(shiftType);
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

    public ShiftType getShiftTypeById(UUID id){
        return shiftTypeDao.getShiftTypeById(id);
    }

}