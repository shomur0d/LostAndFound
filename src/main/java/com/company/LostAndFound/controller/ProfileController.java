package com.company.LostAndFound.controller;

import com.company.LostAndFound.dto.ProfileDTO;
import com.company.LostAndFound.dto.ProfileJwtDTO;
import com.company.LostAndFound.dto.filter.ProfileFilterDTO;
import com.company.LostAndFound.enums.ProfileRole;
import com.company.LostAndFound.service.ProfileService;
import com.company.LostAndFound.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
@Api(tags = "Profile Control", value = "Access only for ADMIN")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "@ApiResponse(code = 400, message = \"...\")")
    })
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileDTO dto, HttpServletRequest request) {

        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.ADMIN);

        ProfileDTO response = profileService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity getAll(HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id, HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.deleteById(id));
    }

    @PostMapping("/filter")
    public ResponseEntity filter(@RequestParam("page") int page,
                                 @RequestParam("size") int size,
                                 @RequestBody ProfileFilterDTO dto) {
        return ResponseEntity.ok(profileService.filterSpecification(page, size, dto));
    }



}
