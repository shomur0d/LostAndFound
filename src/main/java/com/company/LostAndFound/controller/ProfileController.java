package com.company.LostAndFound.controller;

import com.company.LostAndFound.dto.ProfileDTO;
import com.company.LostAndFound.dto.ProfileJwtDTO;
import com.company.LostAndFound.enums.ProfileRole;
import com.company.LostAndFound.service.ProfileService;
import com.company.LostAndFound.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("")
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto, HttpServletRequest request) {

        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.ADMIN);

        ProfileDTO response = profileService.create(dto);
        return ResponseEntity.ok(response);
    }

}
