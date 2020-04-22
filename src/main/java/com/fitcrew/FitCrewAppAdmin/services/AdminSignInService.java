package com.fitcrew.FitCrewAppAdmin.services;

import com.fitcrew.FitCrewAppAdmin.converter.AdminDocumentAdminModelConverter;
import com.fitcrew.FitCrewAppAdmin.dao.AdminDao;
import com.fitcrew.FitCrewAppAdmin.enums.AdminErrorMessageType;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import com.fitcrew.FitCrewAppModel.domain.model.AdminModel;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class AdminSignInService implements UserDetailsService {

    private final AdminDao adminDao;
    private final AdminDocumentAdminModelConverter adminConverter;

    AdminSignInService(AdminDao adminDao,
                       AdminDocumentAdminModelConverter adminConverter) {
        this.adminDao = adminDao;
        this.adminConverter = adminConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Admin searched in database by username: {}", username);
        return adminDao.findByEmail(username)
                .map(adminDocument -> new User(adminDocument.getEmail(),
                        adminDocument.getEncryptedPassword(),
                        true,
                        true,
                        true,
                        true,
                        new ArrayList<>()))
                .orElse(null);
    }

    public Either<ErrorMsg, AdminModel> getAdminDetailsByEmail(String email) {
        return adminDao.findByEmail(email)
                .map(adminConverter::adminDocumentToAdminModel)
                .map(Either::<ErrorMsg, AdminModel>right)
                .orElseGet(() -> Either.left(new ErrorMsg(AdminErrorMessageType.NO_ADMIN_FOUND.toString())));
    }
}
