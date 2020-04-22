package com.fitcrew.FitCrewAppAdmin.util;

import com.fitcrew.FitCrewAppAdmin.dto.ClientDto;
import com.fitcrew.FitCrewAppModel.domain.model.ClientModel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClientResourceMockUtil {

	private static String CLIENT_EMAIL = "mockedClient@gmail.com";
	private static String CLIENT_FIRST_NAME = "firstName";
	private static String CLIENT_LAST_NAME = "lastName";
	private static String CLIENT_DATE_OF_BIRTH = "01.01.1990";
	private static String CLIENT_PHONE_NUMBER = "501928341";
	private static String CLIENT_ENCRYPTED_PASSWORD = "$2y$12$Y3QFw.tzF7OwIJGlpzk9s.5Ymq4zY3hItIkD0Xes3UWxBo2SkEgei";
	private static String CLIENT_PASSWORD = "test";

	public static ClientDto clientDto() {
		return prepareClientDtoData();
	}

	public static ClientDto notValidClientDto() {
		return prepareNotValidClientDtoData();
	}

	public static ClientModel clientModel() {
		return prepareClientModelData();
	}

	public static List<ClientModel> clientModels() {
		return IntStream.rangeClosed(1, 3)
				.mapToObj(value -> prepareClientModelData(
				))
				.collect(Collectors.toList());
	}

	private static ClientDto prepareClientDtoData() {
		return ClientDto.builder()
				.firstName(CLIENT_FIRST_NAME)
				.lastName(CLIENT_LAST_NAME)
				.dateOfBirth(CLIENT_DATE_OF_BIRTH)
				.password(CLIENT_PASSWORD)
				.encryptedPassword(CLIENT_ENCRYPTED_PASSWORD)
				.email(CLIENT_EMAIL)
				.phone(CLIENT_PHONE_NUMBER)
				.build();
	}

	private static ClientDto prepareNotValidClientDtoData() {
		return ClientDto.builder()
				.firstName(CLIENT_FIRST_NAME)
				.build();
	}

	private static ClientModel prepareClientModelData() {
		return ClientModel.builder()
				.firstName(CLIENT_FIRST_NAME)
				.lastName(CLIENT_LAST_NAME)
				.dateOfBirth(CLIENT_DATE_OF_BIRTH)
				.password(CLIENT_PASSWORD)
				.encryptedPassword(CLIENT_ENCRYPTED_PASSWORD)
				.email(CLIENT_EMAIL)
				.phone(CLIENT_PHONE_NUMBER)
				.build();
	}

}
