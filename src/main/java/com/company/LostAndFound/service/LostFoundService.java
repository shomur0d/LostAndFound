package com.company.LostAndFound.service;

import com.company.LostAndFound.dto.LostFoundDTO;
import com.company.LostAndFound.entity.LostFoundEntity;
import com.company.LostAndFound.entity.ProfileEntity;
import com.company.LostAndFound.exeptions.BadRequestException;
import com.company.LostAndFound.exeptions.ItemNotFoundException;
import com.company.LostAndFound.repository.LostFoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LostFoundService {
    @Autowired
    private LostFoundRepository lostFoundRepository;
    @Autowired
    private ProfileService profileService;

    public LostFoundDTO create(LostFoundDTO dto, Integer userId) {
        ProfileEntity profile = profileService.get(userId);

        if (dto.getTitle() == null || dto.getTitle().isEmpty()){
            throw new BadRequestException("Title can not be null");
        }
        if (dto.getContent() == null || dto.getContent().isEmpty()){
            throw new BadRequestException("Content can not be null");
        }

        LostFoundEntity entity = new LostFoundEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setProfile(profile);
        entity.setType(dto.getType());

        lostFoundRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }





    public LostFoundEntity get(Integer id) {
        return lostFoundRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Article not found"));
    }

    public LostFoundDTO toDTO (LostFoundEntity entity){
        LostFoundDTO dto = new LostFoundDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setProfileId(entity.getProfile().getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setType(entity.getType());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
