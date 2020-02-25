package com.fitcrew.FitCrewAppAdmin.util;

import com.fitcrew.FitCrewAppAdmin.domains.AdminEntity;
import com.fitcrew.FitCrewAppModel.domain.model.AdminDto;

public class AdminResourceMockUtil {

	public static AdminEntity adminEntity() {
		return AdminEntity.builder()
				.adminId("1")
				.email("mockedAdmin@gmail.com")
				.encryptedPassword("$2y$12$Y3QFw.tzF7OwIJGlpzk9s.5Ymq4zY3hItIkD0Xes3UWxBo2SkEgei")
				.firstName("firstName")
				.lastName("lastName")
				.id(1L).build();
	}

	public static AdminDto adminDto() {
		return AdminDto.builder()
				.adminId("1")
				.email("mockedAdmin@gmail.com")
				.encryptedPassword("$2y$12$Y3QFw.tzF7OwIJGlpzk9s.5Ymq4zY3hItIkD0Xes3UWxBo2SkEgei")
				.firstName("firstName")
				.lastName("lastName")
				.build();
	}

}
