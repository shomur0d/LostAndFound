package com.company.LostAndFound.service;

import com.company.LostAndFound.dto.LostFoundDTO;
import com.company.LostAndFound.entity.LostFoundEntity;
import com.company.LostAndFound.entity.ProfileEntity;
import com.company.LostAndFound.enums.LostFoundStatus;
import com.company.LostAndFound.exeptions.BadRequestException;
import com.company.LostAndFound.exeptions.ItemNotFoundException;
import com.company.LostAndFound.repository.LostFoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        entity.setStatus(LostFoundStatus.ACTIVE);

        lostFoundRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<LostFoundDTO> getAll() {
        return lostFoundRepository.findAll().stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public List<LostFoundDTO> getAllActive() {
        return lostFoundRepository.getAllActive().stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public List<LostFoundDTO> getAllDone() {
        return lostFoundRepository.getAllDone().stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public List<LostFoundDTO> getByPid(Integer id) {
        return lostFoundRepository.findByProfileId(id).stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Integer getCountLost(){
        Optional<Integer> optional = lostFoundRepository.getCountLOST();
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public Integer getCountFound(){
        Optional<Integer> optional = lostFoundRepository.getCountFOUND();
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public Integer getCountDone(){
        Optional<Integer> optional = lostFoundRepository.getCountDONE();
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public void updateStatus(Integer lostFoundId, Integer userId) {
        ProfileEntity profileEntity = profileService.get(userId);

        LostFoundEntity entity = get(lostFoundId);
        entity.setStatus(LostFoundStatus.DONE);
        lostFoundRepository.save(entity);
    }

    public LostFoundDTO getById(Integer id) {
        return lostFoundRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("NOT EXIST"));
    }

    public LostFoundDTO deleteById(Integer id) {
        LostFoundDTO dto = getById(id);
        lostFoundRepository.deleteById(id);
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
