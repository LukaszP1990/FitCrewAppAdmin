package com.fitcrew.FitCrewAppAdmin.services;

import com.fitcrew.FitCrewAppAdmin.converter.AdminDocumentAdminDtoConverter;
import com.fitcrew.FitCrewAppAdmin.converter.AdminDocumentAdminDtoConverterImpl;
import com.fitcrew.FitCrewAppAdmin.converter.AdminDocumentAdminModelConverter;
import com.fitcrew.FitCrewAppAdmin.converter.AdminDocumentAdminModelConverterImpl;
import com.fitcrew.FitCrewAppAdmin.dao.AdminDao;
import com.fitcrew.FitCrewAppAdmin.domains.AdminDocument;
import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;
import com.fitcrew.FitCrewAppAdmin.enums.AdminErrorMessageType;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import com.fitcrew.FitCrewAppAdmin.services.create.AdminCreateService;
import com.fitcrew.FitCrewAppAdmin.util.AdminResourceMockUtil;
import com.fitcrew.FitCrewAppModel.domain.model.AdminModel;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AdminCreateServiceTest {

	private static String ENCRYPTED_PASSWORD = "$2y$12$Y3QFw.tzF7OwIJGlpzk9s.5Ymq4zY3hItIkD0Xes3UWxBo2SkEgei";
	private static final AdminDocument MOCKED_ADMIN_DOCUMENT = AdminResourceMockUtil.adminDocument();
	private static final AdminDto mockedAdminDto = AdminResourceMockUtil.adminDto();
	private static String ADMIN_EMAIL = "mockedAdmin@gmail.com";
	private static String ADMIN_FIRST_NAME = "firstName";
	private static String ADMIN_LAST_NAME = "lastName";
	private static String ADMIN_ID = "1";

	@Captor
	private ArgumentCaptor<AdminDocument> adminDocumentArgumentCaptor;

	private BCryptPasswordEncoder bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
	private AdminDao adminDao = Mockito.mock(AdminDao.class);
	private AdminDocumentAdminModelConverter adminDocumentModelConverter = new AdminDocumentAdminModelConverterImpl();
	private AdminDocumentAdminDtoConverter adminDocumentDtoConverter = new AdminDocumentAdminDtoConverterImpl();

	private AdminCreateService adminCreateService =
			new AdminCreateService(adminDao, bCryptPasswordEncoder, adminDocumentModelConverter, adminDocumentDtoConverter);

	@Test
	void shouldCreateAdminDocument() {

		when(adminDao.save(any(AdminDocument.class)))
				.thenReturn(MOCKED_ADMIN_DOCUMENT);
		when(bCryptPasswordEncoder.encode(anyString()))
				.thenReturn(ENCRYPTED_PASSWORD);

		Either<ErrorMsg, AdminModel> admin =
				adminCreateService.createAdmin(mockedAdminDto);

		verify(adminDao, times(1))
				.save(any());
		verify(adminDao)
				.save(adminDocumentArgumentCaptor.capture());

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
	void shouldNotCreateAdminDocument() {

		Either<ErrorMsg, AdminModel> admin =
				adminCreateService.createAdmin(mockedAdminDto);

		assertNotNull(admin);
		assertTrue(admin.isLeft());
		assertEquals(AdminErrorMessageType.NO_ADMIN_SAVED.toString(), admin.getLeft().getMsg());
	}
}