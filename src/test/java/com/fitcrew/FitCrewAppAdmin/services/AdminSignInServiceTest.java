package com.fitcrew.FitCrewAppAdmin.services;

import com.fitcrew.FitCrewAppAdmin.converter.AdminDocumentAdminModelConverter;
import com.fitcrew.FitCrewAppAdmin.converter.AdminDocumentAdminModelConverterImpl;
import com.fitcrew.FitCrewAppAdmin.dao.AdminDao;
import com.fitcrew.FitCrewAppAdmin.domains.AdminDocument;
import com.fitcrew.FitCrewAppAdmin.enums.AdminErrorMessageType;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import com.fitcrew.FitCrewAppAdmin.util.AdminResourceMockUtil;
import com.fitcrew.FitCrewAppModel.domain.model.AdminModel;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AdminSignInServiceTest {

    private static String ENCRYPTED_PASSWORD = "$2y$12$Y3QFw.tzF7OwIJGlpzk9s.5Ymq4zY3hItIkD0Xes3UWxBo2SkEgei";
    private static final AdminDocument MOCKED_ADMIN_DOCUMENT = AdminResourceMockUtil.adminDocument();
    private static String ADMIN_EMAIL = "mockedAdmin@gmail.com";
    private static String ADMIN_FIRST_NAME = "firstName";
    private static String ADMIN_LAST_NAME = "lastName";
    private static String ADMIN_ID = "1";

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    private AdminDao adminDao = Mockito.mock(AdminDao.class);
    private AdminDocumentAdminModelConverter adminConverter = new AdminDocumentAdminModelConverterImpl();
    private AdminSignInService adminSignInService = new AdminSignInService(adminDao, adminConverter);

    @Test
    void shouldGetAdminDetailsByEmail() {

        when(adminDao.findByEmail(anyString()))
                .thenReturn(Optional.of(MOCKED_ADMIN_DOCUMENT));

        Either<ErrorMsg, AdminModel> adminDetailsByEmail =
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

        Either<ErrorMsg, AdminModel> adminDetailsByEmail =
                adminSignInService.getAdminDetailsByEmail(ADMIN_EMAIL);
        assertNotNull(adminDetailsByEmail);
        assertTrue(adminDetailsByEmail.isLeft());
        assertEquals(AdminErrorMessageType.NO_ADMIN_FOUND.toString(), adminDetailsByEmail.getLeft().getMsg());
    }

    @Test
    void shouldLoadUserByUsername() {

        when(adminDao.findByEmail(anyString()))
                .thenReturn(Optional.of(MOCKED_ADMIN_DOCUMENT));

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