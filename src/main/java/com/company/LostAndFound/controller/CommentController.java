package com.company.LostAndFound.controller;

import com.company.LostAndFound.dto.CommentDTO;
import com.company.LostAndFound.dto.ProfileJwtDTO;
import com.company.LostAndFound.service.CommentService;
import com.company.LostAndFound.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    private ResponseEntity create(@RequestBody CommentDTO dto,
                                  HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request);
        CommentDTO response = commentService.create(dto, jwtDTO.getId());
        return ResponseEntity.ok(response);
    }

}
