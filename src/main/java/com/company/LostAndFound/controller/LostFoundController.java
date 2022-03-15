package com.company.LostAndFound.controller;

import com.company.LostAndFound.dto.LostFoundDTO;
import com.company.LostAndFound.dto.ProfileJwtDTO;
import com.company.LostAndFound.enums.ProfileRole;
import com.company.LostAndFound.service.LostFoundService;
import com.company.LostAndFound.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/lostfound")
@Api(tags = "LostAndFound")
public class LostFoundController {
    @Autowired
    private LostFoundService lostFoundService;

    @PostMapping
    @ApiOperation(value = "LostFound creating method", notes = "Here u can post things that u lost or found")
    public ResponseEntity create(@Valid @RequestBody LostFoundDTO dto,
                                    HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request);
        LostFoundDTO response = lostFoundService.create(dto, jwtDTO.getId());
        return ResponseEntity.ok(response);
    }


}
