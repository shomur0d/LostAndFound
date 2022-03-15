package com.company.LostAndFound.repository;

import com.company.LostAndFound.entity.LostFoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LostFoundRepository extends JpaRepository<LostFoundEntity, Integer> {

    List<LostFoundEntity> findByStatus(String status);
}
