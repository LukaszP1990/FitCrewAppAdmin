package com.fitcrew.FitCrewAppAdmin.converter;

import com.fitcrew.FitCrewAppAdmin.domains.AdminDocument;
import com.fitcrew.FitCrewAppAdmin.util.AdminResourceMockUtil;
import com.fitcrew.FitCrewAppModel.domain.model.AdminModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminDocumentAdminDtoConverterTest {

    private static AdminDocument adminDocument = AdminResourceMockUtil.adminDocument();
    private AdminDocumentAdminModelConverter adminConverter = new AdminDocumentAdminModelConverterImpl();
    private static String ADMIN_EMAIL = "mockedAdmin@gmail.com";
    private static String ADMIN_FIRST_NAME = "firstName";
    private static String ADMIN_LAST_NAME = "lastName";
    private static String ADMIN_ID = "1";
    private static String ADMIN_ENCRYPTED_PASSWORD = "$2y$12$Y3QFw.tzF7OwIJGlpzk9s.5Ymq4zY3hItIkD0Xes3UWxBo2SkEgei";

    @Test
    void shouldConvertAdminDtoToAdminModel() {
        AdminModel adminModel = adminConverter.adminDocumentToAdminModel(adminDocument);
        assertNotNull(adminModel);
        assertAll(() -> {
            assertEquals(ADMIN_EMAIL, adminModel.getEmail());
            assertEquals(ADMIN_FIRST_NAME, adminModel.getFirstName());
            assertEquals(ADMIN_LAST_NAME, adminModel.getLastName());
            assertEquals(ADMIN_ID, adminModel.getAdminId());
            assertEquals(ADMIN_ENCRYPTED_PASSWORD, adminModel.getEncryptedPassword());
        });
    }
}