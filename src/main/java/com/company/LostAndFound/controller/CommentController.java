package com.company.LostAndFound.controller;

import com.company.LostAndFound.dto.CommentDTO;
import com.company.LostAndFound.dto.ProfileJwtDTO;
import com.company.LostAndFound.exeptions.BadRequestException;
import com.company.LostAndFound.service.CommentService;
import com.company.LostAndFound.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
@Api(tags = "Comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    @ApiOperation(value = "Comment writing method", notes = "Here u can write comments to posts")
    private ResponseEntity create(@Valid @RequestBody CommentDTO dto,
                                  HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request);
        return ResponseEntity.ok(commentService.create(dto, jwtDTO.getId()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Comment By Id")
    public CommentDTO getById(@PathVariable("id") Integer id,
                              HttpServletRequest request){
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request);

        return commentService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "Updating By ID", notes = "U can only update your own comments!")
    public ResponseEntity update(@RequestBody CommentDTO dto,
                                 HttpServletRequest request){

        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request);
        if (!jwtDTO.getId().equals(dto.getProfileId())){
            throw new BadRequestException("It is not your comment!");
        }
        return ResponseEntity.ok(commentService.update(dto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete By ID", notes = "U can only delete your own comments!")
    public void deleteById(@PathVariable("id") Integer id,
                           HttpServletRequest request){
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request);
        if (!jwtDTO.getId().equals(commentService.getById(id).getProfileId())){
            throw new BadRequestException("It is not your comment!");
        }
        commentService.deleteById(id);
    }

    @GetMapping("/pid/{pid}")
    @ApiOperation(value = "Get By ProfileId", notes = "None required")
    public ResponseEntity getByPid(@PathVariable Integer pid){
        return ResponseEntity.ok(commentService.getByPid(pid));
    }



}
