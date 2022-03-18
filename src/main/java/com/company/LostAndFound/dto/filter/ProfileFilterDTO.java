package com.company.LostAndFound.dto.filter;

import com.company.LostAndFound.enums.ProfileRole;
import com.company.LostAndFound.enums.ProfileStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileFilterDTO {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private ProfileRole role;
    private ProfileStatus status;
    private LocalDate fromDate;
    private LocalDate toDate;
}
