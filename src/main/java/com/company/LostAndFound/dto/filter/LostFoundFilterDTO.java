package com.company.LostAndFound.dto.filter;

import com.company.LostAndFound.enums.LostFoundStatus;
import com.company.LostAndFound.enums.LostFoundType;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LostFoundFilterDTO {
    private Integer id;
    private String title;
    private Integer profileId;
    private LostFoundType type;
    private LostFoundStatus status;
    private LocalDate fromDate;
    private LocalDate toDate;
}
