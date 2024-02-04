package com.kdu.smarthome.repository;

import com.kdu.smarthome.entities.DeviceRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRegistrationRepository extends JpaRepository<DeviceRegistration, String> {
}
