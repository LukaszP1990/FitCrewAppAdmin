package com.fitcrew.FitCrewAppAdmin.services;

import com.fitcrew.FitCrewAppAdmin.dao.AdminDao;
import com.fitcrew.FitCrewAppAdmin.domains.AdminEntity;
import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

        setPredefinedData(adminDto);

        ModelMapper modelMapper = prepareModelMapper();

        AdminEntity adminEntity = modelMapper.map(adminDto, AdminEntity.class);
        AdminEntity savedAdmin = adminDao.save(adminEntity);

        return checkIfAdminWasSaved(savedAdmin, modelMapper);

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

    private Either<ErrorMsg, AdminDto> checkIfAdminWasSaved(AdminEntity savedAdmin, ModelMapper modelMapper) {
        if (savedAdmin != null) {

            log.debug("Admin saved successfully: {}", savedAdmin);
            AdminDto returnAdmin = modelMapper.map(savedAdmin, AdminDto.class);

            return Either.right(returnAdmin);
        } else {
            log.debug("Admin save failed");
            return Either.left(new ErrorMsg("Admin save failed"));
        }
    }
}
