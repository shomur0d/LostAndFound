package com.company.LostAndFound.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Integer id;
    private String content;

    private LocalDateTime createdDate;
    private Integer profileId;
    private Integer lostFoundID;
}
