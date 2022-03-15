package com.company.LostAndFound.service;

import com.company.LostAndFound.dto.ProfileDTO;
import com.company.LostAndFound.entity.ProfileEntity;
import com.company.LostAndFound.exeptions.ItemNotFoundException;
import com.company.LostAndFound.repository.ProfileRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO create(ProfileDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        String password = DigestUtils.md5Hex(dto.getPassword());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setLogin(dto.getLogin());
        entity.setPassword(password);
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());
        entity.setStatus(dto.getStatus());

        profileRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<ProfileDTO> getAll() {
        return profileRepository.findAll().stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public ProfileDTO getById(Integer id) {
        ProfileEntity entity = profileRepository
                .findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Profile Not Found with this ID"));
        return toDTO(entity);
    }

    public ProfileDTO deleteById(Integer id) {
        ProfileDTO dto = getById(id);
        profileRepository.deleteById(id);
        return dto;
    }


    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Profile not found"));
    }
    public ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setLogin(entity.getLogin());
        dto.setEmail(entity.getEmail());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setPassword(entity.getPassword());
        return dto;
    }

}
