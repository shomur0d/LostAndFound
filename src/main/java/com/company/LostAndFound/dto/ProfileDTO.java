package com.company.LostAndFound.dto;

import com.company.LostAndFound.enums.ProfileRole;
import com.company.LostAndFound.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {

    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    private String login;
    @Size(min = 3, max = 15)
    private String password;
    @Email
    private String email;
    private ProfileRole role;
    private ProfileStatus status;

    private String jwt; //token

}
