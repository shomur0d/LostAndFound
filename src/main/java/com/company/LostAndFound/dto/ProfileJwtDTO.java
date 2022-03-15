package com.company.LostAndFound.dto;

import com.company.LostAndFound.enums.ProfileRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileJwtDTO {
    private Integer id;
    private ProfileRole role;
}
