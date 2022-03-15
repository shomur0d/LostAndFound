package com.company.LostAndFound.dto.Auth;

import lombok.Data;

@Data
public class RegistrationDTO {
    private String name;
    private String phone;
    private String login;
    private String password;
    private String email;
}
