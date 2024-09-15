package com.example.jpa.service;

import com.example.jpa.entity.Tenant;
import com.example.jpa.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Tenant addTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }
}
