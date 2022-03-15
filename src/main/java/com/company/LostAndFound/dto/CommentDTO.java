package com.company.LostAndFound.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {
    private Integer id;

    @NotBlank
    private String content;
    private LocalDateTime createdDate;
    private Integer profileId;
    @NotNull
    private Integer lostFoundID;
}
