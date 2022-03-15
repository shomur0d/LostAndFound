package com.company.LostAndFound.repository;

import com.company.LostAndFound.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {

    Optional<ProfileEntity> findByLoginAndPassword(String login, String password);

    Optional<ProfileEntity> findByEmail(String email);
    Optional<ProfileEntity> findByLogin(String login);

}
