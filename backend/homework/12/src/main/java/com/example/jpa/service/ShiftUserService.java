package com.example.jpa.service;

import com.example.jpa.entity.ShiftUser;
import com.example.jpa.exception.custom.MyCustomException;
import com.example.jpa.repository.ShiftUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ShiftUserService {
    private final ShiftUserRepository shiftUserRepository;

    public ShiftUserService(ShiftUserRepository shiftUserRepository) {
        this.shiftUserRepository = shiftUserRepository;
    }

    public void saveShiftUser(ShiftUser shiftUser) {
        shiftUserRepository.save(shiftUser);
    }

    public List<ShiftUser> getShiftUsers(UUID tenantId) {
        return shiftUserRepository.findByTenantId(tenantId);
    }

    @Transactional
    public void deleteShiftUserByShiftEndsAt(UUID userId) throws MyCustomException {
        ShiftUser shiftUser = shiftUserRepository.findById(userId).orElseThrow(() -> new MyCustomException("ShiftUser not found with ID: " + userId));

        if (shiftUser.getShift().getTimeEnd().getHours() == 23 && shiftUser.getShift().getTimeEnd().getMinutes() == 0) {
            shiftUserRepository.delete(shiftUser);
        } else {
            throw new MyCustomException("ShiftUser cannot be deleted");
        }
    }
}
