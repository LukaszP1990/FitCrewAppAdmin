package com.fitcrew.FitCrewAppAdmin.util;

import com.fitcrew.FitCrewAppAdmin.domains.AdminDocument;
import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;

public class AdminResourceMockUtil {

    private static String ADMIN_EMAIL = "mockedAdmin@gmail.com";
    private static String ADMIN_FIRST_NAME = "firstName";
    private static String ADMIN_LAST_NAME = "lastName";
    private static String ADMIN_ID = "1";
    private static String ENCRYPTED_PASSWORD = "$2y$12$Y3QFw.tzF7OwIJGlpzk9s.5Ymq4zY3hItIkD0Xes3UWxBo2SkEgei";

    public static AdminDocument adminDocument() {
        return AdminDocument.builder()
                .adminId(ADMIN_ID)
                .email(ADMIN_EMAIL)
                .encryptedPassword(ENCRYPTED_PASSWORD)
                .firstName(ADMIN_FIRST_NAME)
                .lastName(ADMIN_LAST_NAME)
                .id(1L)
                .build();
    }

    public static AdminDto adminDto() {
        return AdminDto.builder()
                .adminId(ADMIN_ID)
                .email(ADMIN_EMAIL)
                .encryptedPassword(ENCRYPTED_PASSWORD)
                .firstName(ADMIN_FIRST_NAME)
                .lastName(ADMIN_LAST_NAME)
                .password("password")
                .build();
    }

    //  adminDto.getFirstName(),
    //                    adminDto.getLastName(),
    //                    adminDto.getEmail(),
    //                    adminDto.getPassword())

    public static AdminDto notValidAdminDto() {
        return AdminDto.builder()
                .adminId(ADMIN_ID)
                .build();
    }
}
