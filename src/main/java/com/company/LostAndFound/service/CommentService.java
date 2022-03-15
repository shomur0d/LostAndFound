package com.company.LostAndFound.service;

import com.company.LostAndFound.dto.CommentDTO;
import com.company.LostAndFound.entity.CommentEntity;
import com.company.LostAndFound.entity.LostFoundEntity;
import com.company.LostAndFound.entity.ProfileEntity;
import com.company.LostAndFound.exeptions.BadRequestException;
import com.company.LostAndFound.exeptions.ItemNotFoundException;
import com.company.LostAndFound.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private LostFoundService lostFoundService;

    public CommentDTO create(CommentDTO dto, Integer profileId){
        ProfileEntity profile = profileService.get(profileId);
        LostFoundEntity lostFound = lostFoundService.get(dto.getLostFoundID());

        if (dto.getContent() == null || dto.getContent().isEmpty()){
            throw new BadRequestException("Content can not be null");
        }

        CommentEntity entity = new CommentEntity();
        entity.setContent(dto.getContent());
        entity.setId(dto.getId());
        entity.setProfile(profile);
        entity.setLostFound(lostFound);
        entity.setCreatedDate(LocalDateTime.now());

        commentRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<CommentDTO> getAll() {
        return commentRepository.findAll().stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public CommentDTO getById(Integer id) {
        return commentRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new ItemNotFoundException("Comment Not Found"));
    }

    public CommentDTO update(CommentDTO dto) {
        CommentEntity entity = get(dto.getId());
        entity.setContent(dto.getContent());
        commentRepository.save(entity);
        return toDTO(entity);
    }

    public void deleteById(Integer id) {
        commentRepository.deleteById(id);
    }

    public List<CommentDTO> getByPid(Integer profileId) {
        return commentRepository.findByProfile_Id(profileId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<CommentDTO> getByLid(Integer lostFoundId) {
        return commentRepository.findByLostFound_Id(lostFoundId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }


    public CommentEntity get(Integer id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Comment Not Found"));
    }

    public CommentDTO toDTO(CommentEntity entity) {
        CommentDTO dto = new CommentDTO();

        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setProfileId(entity.getProfile().getId());
        dto.setLostFoundID(entity.getLostFound().getId());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

}
