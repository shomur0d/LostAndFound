package com.company.LostAndFound.controller;

import com.company.LostAndFound.dto.LostFoundDTO;
import com.company.LostAndFound.dto.ProfileJwtDTO;
import com.company.LostAndFound.enums.ProfileRole;
import com.company.LostAndFound.service.LostFoundService;
import com.company.LostAndFound.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/lostfound")
public class LostFoundController {
    @Autowired
    private LostFoundService lostFoundService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LostFoundDTO dto,
                                    HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request);
        LostFoundDTO response = lostFoundService.create(dto, jwtDTO.getId());
        return ResponseEntity.ok(response);
    }


}
