package com.fitcrew.FitCrewAppAdmin.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fitcrew.FitCrewAppAdmin.dao.AdminDao;
import com.fitcrew.FitCrewAppAdmin.domains.AdminEntity;
import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;
import com.fitcrew.FitCrewAppAdmin.dto.ClientDto;
import com.fitcrew.FitCrewAppAdmin.enums.AdminErrorMessageType;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import com.fitcrew.FitCrewAppAdmin.util.AdminResourceMockUtil;

import io.vavr.control.Either;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AdminCreateServiceTest {

	private static String ENCRYPTED_PASSWORD = "$2y$12$Y3QFw.tzF7OwIJGlpzk9s.5Ymq4zY3hItIkD0Xes3UWxBo2SkEgei";
	private static final AdminEntity mockedAdminEntity = AdminResourceMockUtil.adminEntity();
	private static final AdminDto mockedAdminDto = AdminResourceMockUtil.adminDto();
	private static String ADMIN_EMAIL = "mockedAdmin@gmail.com";
	private static String ADMIN_FIRST_NAME = "firstName";
	private static String ADMIN_LAST_NAME = "lastName";
	private static String ADMIN_ID = "1";

	@Captor
	private ArgumentCaptor<AdminEntity> adminEntityArgumentCaptor;

	@Mock
	private AdminDao adminDao;

	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@InjectMocks
	private AdminCreateService adminCreateService;

	@Test
	void shouldCreateAdminEntity() {

		when(adminDao.save(any(AdminEntity.class)))
				.thenReturn(mockedAdminEntity);
		when(bCryptPasswordEncoder.encode(anyString()))
				.thenReturn(ENCRYPTED_PASSWORD);

		Either<ErrorMsg, AdminDto> admin =
				adminCreateService.createAdmin(mockedAdminDto);

		verify(adminDao, times(1))
				.save(any());
		verify(adminDao)
				.save(adminEntityArgumentCaptor.capture());

		assertNotNull(admin);
		assertAll(() -> {
			assertTrue(admin.isRight());
			assertEquals(ADMIN_FIRST_NAME, admin.get().getFirstName());
			assertEquals(ADMIN_LAST_NAME, admin.get().getLastName());
			assertEquals(ENCRYPTED_PASSWORD, admin.get().getEncryptedPassword());
			assertEquals(ADMIN_EMAIL, admin.get().getEmail());
			assertEquals(ADMIN_ID, admin.get().getAdminId());
		});
	}

	@Test
	void shouldNotCreateAdminEntity() {

		Either<ErrorMsg, AdminDto> admin =
				adminCreateService.createAdmin(mockedAdminDto);

		assertNotNull(admin);
		assertTrue(admin.isLeft());
		assertEquals(AdminErrorMessageType.NO_ADMIN_SAVED.toString(), admin.getLeft().getMsg());
	}
}