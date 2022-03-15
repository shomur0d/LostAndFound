package com.company.LostAndFound.service;

import com.company.LostAndFound.dto.CommentDTO;
import com.company.LostAndFound.entity.CommentEntity;
import com.company.LostAndFound.entity.LostFoundEntity;
import com.company.LostAndFound.entity.ProfileEntity;
import com.company.LostAndFound.exeptions.BadRequestException;
import com.company.LostAndFound.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private LostFoundService lostFoundService;

    public CommentDTO create(CommentDTO dto, Integer userId){
        ProfileEntity profile = profileService.get(userId);
        LostFoundEntity lostFound = lostFoundService.get(dto.getLostFoundID());

        if (dto.getContent() == null || dto.getContent().isEmpty()){
            throw new BadRequestException("Content can not be null");
        }

        CommentEntity entity = new CommentEntity();
        entity.setContent(dto.getContent());
        entity.setProfile(profile);
        entity.setLostFound(lostFound);
        entity.setCreatedDate(LocalDateTime.now());

        commentRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }



}
