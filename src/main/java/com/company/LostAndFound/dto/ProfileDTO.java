package com.company.LostAndFound.dto;

import com.company.LostAndFound.enums.ProfileRole;
import com.company.LostAndFound.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {

    private Integer id;
    private String name;
    private String phone;
    private String login;
    private String password;
    private String email;
    private ProfileRole role;
    private ProfileStatus status;

    private String jwt; //token

}
