package com.example.jdbc.dao;

import com.example.jdbc.mapper.ShiftTypeRowMapper;
import com.example.jdbc.model.ShiftType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ShiftTypeDao {
    final JdbcTemplate jdbcTemplate;
    public ShiftTypeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveShiftType(ShiftType shiftType){

        String sql = "INSERT INTO shift_types VALUES(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, shiftType.getId(), shiftType.getName(), shiftType.getDescription(), shiftType.isActive(), shiftType.getCreatedAt(), shiftType.getUpdatedAt(), shiftType.getTimeZone(), shiftType.getTenantId());
    }
    public ShiftType getShiftTypeById(UUID id){
        String sql = "SELECT * FROM shift_types WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new ShiftTypeRowMapper(), id);
    }
}