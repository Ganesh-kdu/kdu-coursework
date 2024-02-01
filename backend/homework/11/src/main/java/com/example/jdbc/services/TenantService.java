package com.example.jdbc.services;


import com.example.jdbc.dao.TenantDao;
import com.example.jdbc.dto.TenantDto;
import com.example.jdbc.mapper.DtoToModel;
import com.example.jdbc.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class TenantService {
    TenantDao tenantDao;
    DtoToModel dtoUtil;
    public TenantService(TenantDao tenantDao, DtoToModel dtoUtil){
        this.tenantDao = tenantDao;
        this.dtoUtil = dtoUtil;
    }
    public String addTenant(TenantDto tenantDto){
        Tenant tenant = dtoUtil.mapTenantDtoToTenant(tenantDto);
        tenantDao.saveTenant(tenant);
        return tenant.getId().toString();
    }

}