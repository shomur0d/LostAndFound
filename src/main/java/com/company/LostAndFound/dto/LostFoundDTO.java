package com.company.LostAndFound.dto;

import com.company.LostAndFound.entity.ProfileEntity;
import com.company.LostAndFound.enums.LostFoundStatus;
import com.company.LostAndFound.enums.LostFoundType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class LostFoundDTO {
    private Integer id;
    private String title;
    private String content;
    private Integer profileId;
    private LostFoundType type;
    private LostFoundStatus status;
    private LocalDateTime createdDate;
}
