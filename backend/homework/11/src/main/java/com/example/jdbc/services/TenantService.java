package com.example.jdbc.services;


import com.example.jdbc.dao.TenantDao;
import com.example.jdbc.dto.TenantDto;
import com.example.jdbc.mapper.DtoToModel;
import com.example.jdbc.model.Tenant;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class TenantService {
    TenantDao tenantDao;
    public TenantService(TenantDao tenantDao){
        this.tenantDao = tenantDao;
    }
    public String addTenant(TenantDto tenantDto){
        Tenant tenant = DtoToModel.mapTenantDtoToTenant(tenantDto);
        tenantDao.saveTenant(tenant);
        return tenant.getId().toString();
    }

}