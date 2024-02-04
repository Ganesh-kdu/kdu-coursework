package com.kdu.smarthome.repository;

import com.kdu.smarthome.entities.CompositeKey;
import com.kdu.smarthome.entities.Residents;
import com.kdu.smarthome.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ResidentRepository extends JpaRepository<Residents, CompositeKey> {
     @Query(value = "Select * from residents Where user_id = :user_id and is_admin limit 1;", nativeQuery = true)
     List<Residents> findIfUserIsAdmin(@Param("user_id") String userId);
}
