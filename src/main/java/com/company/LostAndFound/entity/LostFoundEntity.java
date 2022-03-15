package com.company.LostAndFound.entity;

import com.company.LostAndFound.enums.LostFoundStatus;
import com.company.LostAndFound.enums.LostFoundType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lostfound")
public class LostFoundEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;

    @Enumerated(EnumType.STRING)
    @Column
    private LostFoundType type;

    @Enumerated(EnumType.STRING)
    @Column
    private LostFoundStatus status = LostFoundStatus.ACTIVE;

    @Column(name = "created_date")
    private LocalDateTime createdDate;



}
