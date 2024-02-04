package com.kdu.smarthome.repository;

import com.kdu.smarthome.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query(value = "SELECT * FROM rooms WHERE house_id = :houseId", nativeQuery = true)
    List<Room> findAllByHouse(@Param("houseId") Long houseId);
}
