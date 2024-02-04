package com.kdu.smarthome.repository;

import com.kdu.smarthome.entities.DeviceAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceAllocationRepository extends JpaRepository<DeviceAllocation,String> {
    @Query(value = "SELECT a.kickston_id, i.username FROM allocation a, inventory i WHERE a.house = :houseId and i.kickston_id = a.kickston_id", nativeQuery = true)
    List<Object> findAllByHouse(@Param("houseId") Long houseId);
}
