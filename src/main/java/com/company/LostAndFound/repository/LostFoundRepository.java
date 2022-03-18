package com.company.LostAndFound.repository;

import com.company.LostAndFound.entity.LostFoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LostFoundRepository extends JpaRepository<LostFoundEntity, Integer> {

    List<LostFoundEntity> findByStatus(String status);

    @Query(value = "select * from lostfound l where l.status ='ACTIVE'", nativeQuery = true)
    List<LostFoundEntity> getAllActive();

    @Query(value = "select * from lostfound l where l.status ='DONE'", nativeQuery = true)
    List<LostFoundEntity> getAllDone();

    List<LostFoundEntity> findByProfileId(Integer id);

    @Query(value = "select count(*) from lostfound l where l.status ='DONE'", nativeQuery = true)
    Optional<Integer>getCountDONE();

    @Query(value = "select count(*) from lostfound l where l.type ='LOST'", nativeQuery = true)
    Optional<Integer>getCountLOST();

    @Query(value = "select count(*) from lostfound l where l.type ='FOUND'", nativeQuery = true)
    Optional<Integer>getCountFOUND();

}
