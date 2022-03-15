package com.company.LostAndFound.repository;

import com.company.LostAndFound.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findByProfile_Id(Integer profileId);
    List<CommentEntity> findByLostFound_Id(Integer lostFoundId);

}
