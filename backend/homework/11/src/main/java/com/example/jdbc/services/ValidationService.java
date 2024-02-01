package com.example.jdbc.services;

import com.example.jdbc.dao.*;

public class ValidationService {

    TenantDao tenantDao;
    UserDao userDao;
    ShiftDao shiftDao;
    ShiftTypeDao shiftTypeDao;
    ShiftUserDao shiftUserDao;

    public void validateTenant(int id){
        tenantDao.getTenantById(id);
    }
}
