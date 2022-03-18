package com.company.LostAndFound.service;

import com.company.LostAndFound.dto.ProfileDTO;
import com.company.LostAndFound.dto.filter.ProfileFilterDTO;
import com.company.LostAndFound.entity.ProfileEntity;
import com.company.LostAndFound.enums.ProfileStatus;
import com.company.LostAndFound.exeptions.ItemNotFoundException;
import com.company.LostAndFound.repository.ProfileRepository;
import com.company.LostAndFound.spec.ProfileSpecification;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
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


    public PageImpl<ProfileDTO> filterSpecification(int page, int size, ProfileFilterDTO filterDTO){
        Pageable pageable = PageRequest.of(page, size);
        Specification<ProfileEntity> spec = null;
        if (filterDTO.getStatus() != null) {
            spec = Specification.where(ProfileSpecification.status(filterDTO.getStatus()));
        } else {
            spec = Specification.where(ProfileSpecification.status(ProfileStatus.ACTIVE));
        }

        if (filterDTO.getName() != null) {
            spec.and(ProfileSpecification.name(filterDTO.getName()));
        }
        if (filterDTO.getRole() != null) {
            spec.and(ProfileSpecification.role(filterDTO.getRole()));
        }
        if (filterDTO.getId() != null) {
            spec.and(ProfileSpecification.equal("profile", filterDTO.getId()));
        }
        if (filterDTO.getPhone() != null){
            spec.and(ProfileSpecification.phone(filterDTO.getPhone()));
        }

        Page<ProfileEntity> entityPage = profileRepository.findAll(spec, pageable);
        List<ProfileDTO> dtoList = entityPage.getContent().stream()
                .map(this::toDTO).collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());

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
