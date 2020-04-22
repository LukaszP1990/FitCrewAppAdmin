package com.fitcrew.FitCrewAppAdmin.services.create;

import com.fitcrew.FitCrewAppAdmin.converter.AdminDocumentAdminDtoConverter;
import com.fitcrew.FitCrewAppAdmin.converter.AdminDocumentAdminModelConverter;
import com.fitcrew.FitCrewAppAdmin.dao.AdminDao;
import com.fitcrew.FitCrewAppAdmin.domains.AdminDocument;
import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;
import com.fitcrew.FitCrewAppAdmin.enums.AdminErrorMessageType;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import com.fitcrew.FitCrewAppModel.domain.model.AdminModel;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class AdminCreateService {

    private final AdminDao adminDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AdminDocumentAdminModelConverter adminDocumentModelConverter;
    private final AdminDocumentAdminDtoConverter adminDocumentDtoConverter;

    public AdminCreateService(AdminDao adminDao,
                              BCryptPasswordEncoder bCryptPasswordEncoder,
                              AdminDocumentAdminModelConverter adminDocumentModelConverter,
                              AdminDocumentAdminDtoConverter adminDocumentDtoConverter) {
        this.adminDao = adminDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.adminDocumentModelConverter = adminDocumentModelConverter;
        this.adminDocumentDtoConverter = adminDocumentDtoConverter;
    }

    public Either<ErrorMsg, AdminModel> createAdmin(AdminDto adminDto) {

        return Optional.ofNullable(adminDto)
                .map(this::saveAdminDto)
                .map(adminDocumentModelConverter::adminDocumentToAdminModel)
                .map(Either::<ErrorMsg, AdminModel>right)
                .orElse(Either.left(new ErrorMsg(AdminErrorMessageType.NO_ADMIN_SAVED.toString())));
    }

    private AdminDocument saveAdminDto(AdminDto adminDto) {
        setPredefinedData(adminDto);
        return adminDao.save(
                adminDocumentDtoConverter.adminDtoToAdminDocument(adminDto)
        );
    }

    private void setPredefinedData(AdminDto adminDto) {
        adminDto.setAdminId(UUID.randomUUID().toString());
        adminDto.setEncryptedPassword(bCryptPasswordEncoder.encode(adminDto.getPassword()));
    }
}
