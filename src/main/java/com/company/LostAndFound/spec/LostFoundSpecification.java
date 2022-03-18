package com.company.LostAndFound.spec;

import com.company.LostAndFound.entity.LostFoundEntity;
import com.company.LostAndFound.enums.LostFoundStatus;
import com.company.LostAndFound.enums.LostFoundType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LostFoundSpecification {
    public static Specification<LostFoundEntity> status(LostFoundStatus status) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("status"), status);
        });
    }

    public static Specification<LostFoundEntity> type(LostFoundType type) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("type"), type);
        });
    }

    public static Specification<LostFoundEntity> title(String title) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("title"), title);
        });
    }

    public static Specification<LostFoundEntity> equal(String field, Integer id) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(field), id);
        });
    }

    public static Specification<LostFoundEntity> greaterThanOrEqualTo(String field, LocalDate date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(field),
                        LocalDateTime.of(date, LocalTime.MIN));

    }

    public static Specification<LostFoundEntity> lessThanOrEqualTo(String field, LocalDate date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get(field),
                        LocalDateTime.of(date, LocalTime.MAX));
    }

}
