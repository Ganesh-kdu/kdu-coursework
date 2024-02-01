package com.example.jpa.repository;

import com.example.jpa.entity.ShiftUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShiftUserRepository extends JpaRepository<ShiftUser, UUID> {
    List<ShiftUser> findByTenantId(UUID tenantId);
}