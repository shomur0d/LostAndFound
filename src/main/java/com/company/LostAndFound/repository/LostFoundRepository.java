package com.company.LostAndFound.repository;

import com.company.LostAndFound.entity.LostFoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostFoundRepository extends JpaRepository<LostFoundEntity, Integer> {

}
