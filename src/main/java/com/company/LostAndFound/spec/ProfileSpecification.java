package com.company.LostAndFound.spec;

import com.company.LostAndFound.entity.ProfileEntity;
import com.company.LostAndFound.enums.ProfileRole;
import com.company.LostAndFound.enums.ProfileStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ProfileSpecification {
    public static Specification<ProfileEntity> status(ProfileStatus status) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("status"), status);
        });
    }

    public static Specification<ProfileEntity> role(ProfileRole role) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("role"), role);
        });
    }

    public static Specification<ProfileEntity> equal(String field, Integer id) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(field), id);
        });
    }

    public static Specification<ProfileEntity> name(String name) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("name"), name);
        });
    }

    public static Specification<ProfileEntity> phone(String phone) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("phone"), phone);
        });
    }

}
