package com.fitcrew.FitCrewAppAdmin.services;

import com.fitcrew.FitCrewAppAdmin.dao.AdminDao;
import com.fitcrew.FitCrewAppAdmin.domains.AdminEntity;
import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

    public AdminSignInService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.debug("Admin searched in database by username: {}", username);
        AdminEntity adminEntity = adminDao.findByEmail(username);

        if (adminEntity == null) {
            log.debug("Admin not found in database");
            return null;
        }
        return new User(adminEntity.getEmail(),
                adminEntity.getEncryptedPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>());
    }

    public AdminDto getAdminDetailsByEmail(String email) {

        log.debug("Admin searched in database by email: {}", email);
        AdminEntity adminEntity = adminDao.findByEmail(email);

        if (adminEntity == null) {
            log.debug("Admin not found in database");
            return null;
        }

        return new ModelMapper().map(adminEntity, AdminDto.class);
    }
}
