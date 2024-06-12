package com.example.jdbc.dao;

import com.example.jdbc.mapper.TenantRowMapper;
import com.example.jdbc.model.Tenant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TenantDao {
    final JdbcTemplate jdbcTemplate;
    public TenantDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveTenant(Tenant tenant) {
        String sql = "INSERT INTO tenant VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, tenant.getId(), tenant.getName(), tenant.getCreatedAt(), tenant.getCreatedBy(), tenant.getUpdatedAt(), tenant.getUpdatedBy());
    }

    public Tenant getTenantById(int id) {
        String sql = "SELECT * FROM tenant WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new TenantRowMapper(), id);
    }

    public int updateNameForId(int id, String name) {
        String sql = "UPDATE tenant SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, name, id);
    }
}