package com.kdu.smarthome.repository;

import com.kdu.smarthome.entities.CompositeKey;
import com.kdu.smarthome.entities.Residents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResidentRepository extends JpaRepository<Residents, CompositeKey> {
}
