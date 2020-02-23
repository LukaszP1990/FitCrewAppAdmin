package com.fitcrew.FitCrewAppAdmin.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.userdetails.UserDetails;

import com.fitcrew.FitCrewAppAdmin.dao.AdminDao;
import com.fitcrew.FitCrewAppAdmin.domains.AdminEntity;
import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;
import com.fitcrew.FitCrewAppAdmin.enums.AdminErrorMessageType;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import com.fitcrew.FitCrewAppAdmin.util.AdminResourceMockUtil;

import io.vavr.control.Either;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AdminSignInServiceTest {

	private static String ENCRYPTED_PASSWORD = "$2y$12$Y3QFw.tzF7OwIJGlpzk9s.5Ymq4zY3hItIkD0Xes3UWxBo2SkEgei";
	private static final AdminEntity mockedAdminEntity = AdminResourceMockUtil.adminEntity();
	private static String ADMIN_EMAIL = "mockedAdmin@gmail.com";
	private static String ADMIN_FIRST_NAME = "firstName";
	private static String ADMIN_LAST_NAME = "lastName";
	private static String ADMIN_ID = "1";

	@Captor
	private ArgumentCaptor<String> stringArgumentCaptor;

	@InjectMocks
	private AdminSignInService adminSignInService;

	@Mock
	private AdminDao adminDao;


	@Test
	void shouldGetAdminDetailsByEmail() {

		when(adminDao.findByEmail(anyString()))
				.thenReturn(Optional.of(mockedAdminEntity));

		Either<ErrorMsg, AdminDto> adminDetailsByEmail =
				adminSignInService.getAdminDetailsByEmail(ADMIN_EMAIL);

		verify(adminDao, times(1))
				.findByEmail(ADMIN_EMAIL);
		verify(adminDao)
				.findByEmail(stringArgumentCaptor.capture());

		assertNotNull(adminDetailsByEmail);
		assertTrue(adminDetailsByEmail.isRight());
		assertAll(() -> {
			assertEquals(ADMIN_FIRST_NAME, adminDetailsByEmail.get().getFirstName());
			assertEquals(ADMIN_LAST_NAME, adminDetailsByEmail.get().getLastName());
			assertEquals(
					ENCRYPTED_PASSWORD,
					adminDetailsByEmail.get().getEncryptedPassword()
			);
			assertEquals(ADMIN_EMAIL, adminDetailsByEmail.get().getEmail());
			assertEquals(ADMIN_ID, adminDetailsByEmail.get().getAdminId());
		});
	}

	@Test
	void shouldNotGetAdminDetailsByEmail() {

		Either<ErrorMsg, AdminDto> adminDetailsByEmail =
				adminSignInService.getAdminDetailsByEmail(ADMIN_EMAIL);
		assertNotNull(adminDetailsByEmail);
		assertTrue(adminDetailsByEmail.isLeft());
		assertEquals(AdminErrorMessageType.NO_ADMIN_FOUND.toString(), adminDetailsByEmail.getLeft().getMsg());
	}

	@Test
	void shouldLoadUserByUsername() {

		when(adminDao.findByEmail(anyString()))
				.thenReturn(Optional.of(mockedAdminEntity));

		UserDetails adminDetailsByEmail = adminSignInService.loadUserByUsername(ADMIN_EMAIL);

		verify(adminDao, times(1))
				.findByEmail(ADMIN_EMAIL);
		verify(adminDao)
				.findByEmail(stringArgumentCaptor.capture());

		assertNotNull(adminDetailsByEmail);
		assertAll(() -> {
			assertEquals(ADMIN_EMAIL, adminDetailsByEmail.getUsername());
			assertEquals(ENCRYPTED_PASSWORD, adminDetailsByEmail.getPassword());
		});
	}

	@Test
	void shouldNotLoadUserByUsername() {

		UserDetails userByUsername = adminSignInService.loadUserByUsername(ADMIN_EMAIL);
		assertNull(userByUsername);
	}
}