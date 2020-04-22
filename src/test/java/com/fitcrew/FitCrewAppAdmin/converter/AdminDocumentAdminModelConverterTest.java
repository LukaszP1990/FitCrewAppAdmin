package com.fitcrew.FitCrewAppAdmin.converter;

import com.fitcrew.FitCrewAppAdmin.domains.AdminDocument;
import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;
import com.fitcrew.FitCrewAppAdmin.util.AdminResourceMockUtil;
import com.fitcrew.FitCrewAppModel.domain.model.AdminModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminDocumentAdminModelConverterTest {

    private static AdminDto adminDto = AdminResourceMockUtil.adminDto();
    private AdminDocumentAdminDtoConverter adminConverter = new AdminDocumentAdminDtoConverterImpl();
    private static String ADMIN_EMAIL = "mockedAdmin@gmail.com";
    private static String ADMIN_FIRST_NAME = "firstName";
    private static String ADMIN_LAST_NAME = "lastName";
    private static String ADMIN_ID = "1";
    private static String ADMIN_ENCRYPTED_PASSWORD = "$2y$12$Y3QFw.tzF7OwIJGlpzk9s.5Ymq4zY3hItIkD0Xes3UWxBo2SkEgei";

    @Test
    void shouldConvertAdminDtoToAdminDocument() {
        AdminDocument adminDocument = adminConverter.adminDtoToAdminDocument(adminDto);
        assertNotNull(adminDocument);
        assertAll(() -> {
            assertEquals(ADMIN_EMAIL, adminDocument.getEmail());
            assertEquals(ADMIN_FIRST_NAME, adminDocument.getFirstName());
            assertEquals(ADMIN_LAST_NAME, adminDocument.getLastName());
            assertEquals(ADMIN_ID, adminDocument.getAdminId());
            assertEquals(ADMIN_ENCRYPTED_PASSWORD, adminDocument.getEncryptedPassword());
        });
    }
}