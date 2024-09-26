package com.example.assessment.repository;

import com.example.assessment.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressesRepository extends JpaRepository<Address, UUID> {
}
