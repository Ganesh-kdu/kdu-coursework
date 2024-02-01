package com.example.jpa.service;

import com.example.jpa.entity.Shifts;
import com.example.jpa.repository.ShiftRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ShiftService {
    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public void saveShift(Shifts shift) {
        shiftRepository.save(shift);
    }

    public List<Shifts> getShifts(UUID tenantId) {
        return shiftRepository.findByTenantId(tenantId);
    }

    public List<Shifts> findTop3ShiftsByDateRange(LocalDate dateStart, LocalDate dateEnd) {
        Pageable pageable = PageRequest.of(0, 3);
        return shiftRepository.findTop3Shifts(dateStart, dateEnd, pageable);
    }
}
