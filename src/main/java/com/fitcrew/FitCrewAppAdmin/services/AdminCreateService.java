package com.fitcrew.FitCrewAppAdmin.services;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitcrew.FitCrewAppAdmin.dao.AdminDao;
import com.fitcrew.FitCrewAppAdmin.domains.AdminEntity;
import com.fitcrew.FitCrewAppAdmin.enums.AdminErrorMessageType;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import com.fitcrew.FitCrewAppModel.domain.model.AdminDto;

import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminCreateService {

    private final AdminDao adminDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static int adminId = 1;

    public AdminCreateService(AdminDao adminDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminDao = adminDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Either<ErrorMsg, AdminDto> createAdmin(AdminDto adminDto) {
        ModelMapper modelMapper = prepareModelMapper();

        return Optional.ofNullable(adminDto)
                .map(admin -> saveAdminDto(admin, modelMapper))
                .map(admin -> modelMapper.map(admin, AdminDto.class))
                .map(Either::<ErrorMsg, AdminDto>right)
                .orElse(Either.left(new ErrorMsg(AdminErrorMessageType.NO_ADMIN_SAVED.toString())));
    }

    private AdminEntity saveAdminDto(AdminDto adminDto, ModelMapper modelMapper) {
        setPredefinedData(adminDto);
        AdminEntity adminEntity = modelMapper.map(adminDto, AdminEntity.class);
        return adminDao.save(adminEntity);
    }

    private void setPredefinedData(AdminDto adminDto) {
        adminDto.setAdminId(UUID.randomUUID().toString());
        adminDto.setEncryptedPassword(bCryptPasswordEncoder.encode(adminDto.getPassword()));
    }

    private PropertyMap<AdminDto, AdminEntity> skipModifiedFieldsMap = new PropertyMap<AdminDto, AdminEntity>() {
        protected void configure() {
            skip().setId(adminId);
            adminId++;
        }
    };

    private ModelMapper prepareModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper
                .getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(skipModifiedFieldsMap);
        return modelMapper;
    }
}
