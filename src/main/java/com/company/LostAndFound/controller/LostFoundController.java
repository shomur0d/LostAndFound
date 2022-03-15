package com.company.LostAndFound.controller;

import com.company.LostAndFound.dto.LostFoundDTO;
import com.company.LostAndFound.dto.ProfileJwtDTO;
import com.company.LostAndFound.enums.ProfileRole;
import com.company.LostAndFound.exeptions.BadRequestException;
import com.company.LostAndFound.service.LostFoundService;
import com.company.LostAndFound.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/{id}")
    public ResponseEntity updateStatus(@ApiParam(value = "id", readOnly = true,example = "id of lostFound")
                                     @PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request);
        if (!jwtDTO.getId().equals(lostFoundService.getById(id).getProfileId())){
            throw new BadRequestException("It is not your post!");
        }
        if(jwtDTO.getRole().equals(ProfileRole.ADMIN)){
            lostFoundService.updateStatus(id, jwtDTO.getId());
        }
        lostFoundService.updateStatus(id, jwtDTO.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ApiOperation(value = "Get All lostFounds", notes = "Auth required")
    public ResponseEntity getAll(HttpServletRequest request) {
        JwtUtil.getProfile(request);
        return ResponseEntity.ok(lostFoundService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an LostFound", notes = "By Id")
    public ResponseEntity getById(@PathVariable Integer id,
                                  HttpServletRequest request) {

        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request);
        return ResponseEntity.ok(lostFoundService.getById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete By ID", notes = "U can only delete your own posts!")
    public void deleteById(@PathVariable("id") Integer id,
                           HttpServletRequest request){
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request);
        if (!jwtDTO.getId().equals(lostFoundService.getById(id).getProfileId())){
            throw new BadRequestException("It is not your post!");
        }
        if(jwtDTO.getRole().equals(ProfileRole.ADMIN)){
            lostFoundService.deleteById(id);
        }
        lostFoundService.deleteById(id);
    }


}
