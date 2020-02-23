package com.fitcrew.FitCrewAppAdmin.services;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fitcrew.FitCrewAppAdmin.dao.AdminDao;
import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;
import com.fitcrew.FitCrewAppAdmin.enums.AdminErrorMessageType;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;

import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;

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
		return adminDao.findByEmail(username)
				.map(adminEntity -> new User(adminEntity.getEmail(),
						adminEntity.getEncryptedPassword(),
						true,
						true,
						true,
						true,
						new ArrayList<>()))
				.orElse(null);
	}

	public Either<ErrorMsg, AdminDto> getAdminDetailsByEmail(String email) {
		return adminDao.findByEmail(email)
				.map(adminEntity -> new ModelMapper().map(adminEntity, AdminDto.class))
				.map(Either::<ErrorMsg, AdminDto>right)
				.orElseGet(() -> Either.left(new ErrorMsg(AdminErrorMessageType.NO_ADMIN_FOUND.toString())));
	}
}
