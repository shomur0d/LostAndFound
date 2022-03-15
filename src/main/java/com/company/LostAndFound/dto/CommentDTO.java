package com.company.LostAndFound.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {
    private Integer id;
    private String content;

    private LocalDateTime createdDate;
    private Integer profileId;
    private Integer lostFoundID;
}
