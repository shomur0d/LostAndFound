package com.company.LostAndFound.controller;

import com.company.LostAndFound.dto.Auth.AuthorizationDTO;
import com.company.LostAndFound.dto.Auth.RegistrationDTO;
import com.company.LostAndFound.dto.ProfileDTO;
import com.company.LostAndFound.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Api(tags = "Authorization")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ApiOperation(value = "Login method", notes = "Enter your login and password")
    public ResponseEntity<ProfileDTO> login(@RequestBody AuthorizationDTO dto) {
        ProfileDTO response = authService.authorization(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registration")
    @ApiOperation(value = "Registration method")
    public ResponseEntity<ProfileDTO> registration(@RequestBody RegistrationDTO dto) {
        authService.registration(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verification/{jwt}")
    @ApiOperation(value = "Register method")
    public ResponseEntity<?> verification(@PathVariable("jwt") String jwt) {
        authService.verification(jwt);
        return ResponseEntity.ok().build();
    }
}
