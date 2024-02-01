package com.example.jdbc.services;


import com.example.jdbc.dao.TenantDao;
import com.example.jdbc.dto.TenantDto;
import com.example.jdbc.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class TenantService {
    TenantDao tenantDao;
    public TenantService(TenantDao tenantDao){
        this.tenantDao = tenantDao;
    }
    public void addTenant(TenantDto tenantDto){
        Tenant tenant = mapTenantDtoToTenant(tenantDto);
        tenantDao.saveTenant(tenant);
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


}