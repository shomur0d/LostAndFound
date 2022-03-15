package com.company.LostAndFound.dto;

import com.company.LostAndFound.entity.ProfileEntity;
import com.company.LostAndFound.enums.LostFoundStatus;
import com.company.LostAndFound.enums.LostFoundType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LostFoundDTO {
    private Integer id;
    @NotEmpty(message = "Title can not be null dude!")
    private String title;
    @NotEmpty(message = "Content can not be null dude!")
    private String content;
    private Integer profileId;
    private LostFoundType type;
    private LostFoundStatus status;
    private LocalDateTime createdDate;
}
