package com.example.jdbc.dao;

import com.example.jdbc.mapper.ShiftUserRowMapper;
import com.example.jdbc.model.ShiftUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ShiftUserDao {
    final JdbcTemplate jdbcTemplate;
    @Autowired
    public ShiftUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveShiftUser(ShiftUser shiftUser){
        String sql= "INSERT INTO shift_user VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, shiftUser.getId(), shiftUser.getShiftId(), shiftUser.getUserId(), shiftUser.getTenantId());
    }
    public ShiftUser getShiftUserById(UUID id){
        String  sql = "SELECT * FROM shift_user WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new ShiftUserRowMapper(),id);
    }
}