package com.example.assessment.repository;

import com.example.assessment.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemsRepository extends JpaRepository<Item, UUID> {
}
