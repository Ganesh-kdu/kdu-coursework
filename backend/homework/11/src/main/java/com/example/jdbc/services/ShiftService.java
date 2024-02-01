package com.example.jdbc.services;

import com.example.jdbc.dao.ShiftDao;
import com.example.jdbc.dto.ShiftDto;
import com.example.jdbc.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class ShiftService {

    ShiftDao shiftDao;
    public ShiftService(ShiftDao shiftDao){
        this.shiftDao = shiftDao;
    }
    public void addShift(ShiftDto shiftDto){
        Shift shift = mapShiftDtoToShift(shiftDto);

        shiftDao.saveShift(shift);
    }

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

    public Shift getShiftById(UUID id){
        return shiftDao.getShiftByid(id);
    }
}