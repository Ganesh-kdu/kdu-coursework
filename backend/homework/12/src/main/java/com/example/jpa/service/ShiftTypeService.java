package com.example.jpa.service;

import com.example.jpa.entity.ShiftType;
import com.example.jpa.repository.ShiftTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShiftTypeService {
    private final ShiftTypeRepository shiftTypeRepository;

    public ShiftTypeService(ShiftTypeRepository shiftTypeRepository) {
        this.shiftTypeRepository = shiftTypeRepository;
    }

    public void saveShiftType(ShiftType shiftType) {
        shiftTypeRepository.save(shiftType);
    }

    public List<ShiftType> getShiftTypes(UUID tenantId) {
        return shiftTypeRepository.findByTenantId(tenantId);
    }
}
