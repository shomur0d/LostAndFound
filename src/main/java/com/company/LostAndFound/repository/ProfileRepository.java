package com.company.LostAndFound.repository;

import com.company.LostAndFound.entity.LostFoundEntity;
import com.company.LostAndFound.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer>,
        JpaSpecificationExecutor<ProfileEntity> {

    Optional<ProfileEntity> findByLoginAndPassword(String login, String password);

    Optional<ProfileEntity> findByEmail(String email);
    Optional<ProfileEntity> findByLogin(String login);

    @Transactional
    @Modifying
    @Query("Update ProfileEntity p set p.name=:name where p.phone=:phone")
    int updateCommentById(@Param("name") String name, @Param("phone") String phone);


}
