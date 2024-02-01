package com.example.jdbc.services;

import com.example.jdbc.dao.ShiftDao;
import com.example.jdbc.dto.ShiftDto;
import com.example.jdbc.mapper.DtoToModel;
import com.example.jdbc.model.Shift;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class ShiftService {

    DtoToModel dtoUtil;
    ShiftDao shiftDao;
    public ShiftService(ShiftDao shiftDao, DtoToModel dtoUtil){
        this.shiftDao = shiftDao;
        this.dtoUtil = dtoUtil;
    }
    public String addShift(ShiftDto shiftDto){
        Shift shift = dtoUtil.mapShiftDtoToShift(shiftDto);
        shiftDao.saveShift(shift);
        return shift.getId().toString();
    }
    public Shift getShiftById(UUID id){
        return shiftDao.getShiftByid(id);
    }
}