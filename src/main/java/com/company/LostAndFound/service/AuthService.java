package com.company.LostAndFound.service;

import com.company.LostAndFound.dto.Auth.AuthorizationDTO;
import com.company.LostAndFound.dto.Auth.RegistrationDTO;
import com.company.LostAndFound.dto.ProfileDTO;
import com.company.LostAndFound.entity.ProfileEntity;
import com.company.LostAndFound.enums.ProfileRole;
import com.company.LostAndFound.enums.ProfileStatus;
import com.company.LostAndFound.exeptions.BadRequestException;
import com.company.LostAndFound.exeptions.ItemNotFoundException;
import com.company.LostAndFound.repository.ProfileRepository;
import com.company.LostAndFound.util.JwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EmailService emailService;

    public ProfileDTO authorization(AuthorizationDTO dto) {
        String password = DigestUtils.md5Hex(dto.getPassword());
        Optional<ProfileEntity> optional = profileRepository.findByLoginAndPassword(dto.getLogin(),password);
        if (!optional.isPresent()){
            throw new RuntimeException("Login or password is incorrect");
        }
        if (!optional.get().getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new BadRequestException("You are not allowed");
        }

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName(optional.get().getName());
        profileDTO.setPhone(optional.get().getPhone());
        profileDTO.setJwt(JwtUtil.createJwt(optional.get().getId(), optional.get().getRole()));

        return profileDTO;
    }

    public void registration(RegistrationDTO dto){

        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()){
            throw new BadRequestException("Email exists");
        }

        optional = profileRepository.findByLogin(dto.getLogin());
        if (optional.isPresent()){
            throw new BadRequestException("Login exists");
        }

        String password = DigestUtils.md5Hex(dto.getPassword());

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setLogin(dto.getLogin());
        entity.setPassword(password);
        entity.setRole(ProfileRole.USER);
        entity.setStatus(ProfileStatus.CREATED);
        profileRepository.save(entity);

        String jwt = JwtUtil.createJwt(entity.getId());
        StringBuilder builder = new StringBuilder();
        builder.append("Salom jigar qalaysan\n");
        builder.append("Agar bu sen bo'lsang shu linkga bos: ");
        builder.append("http://localhost:8080/auth/verification/"+ jwt);
        emailService.sendEmail(dto.getEmail(), "Registration LostAndFound Test", builder.toString());
    }

    public void verification(String jwt){
        Integer id = JwtUtil.decodeJwtAndGetId(jwt);
        Optional<ProfileEntity> optional = profileRepository.findById(id);
        if (!optional.isPresent()){
            throw new ItemNotFoundException("User not found");
        }
        optional.get().setStatus(ProfileStatus.ACTIVE);
        profileRepository.save(optional.get());
    }

}
