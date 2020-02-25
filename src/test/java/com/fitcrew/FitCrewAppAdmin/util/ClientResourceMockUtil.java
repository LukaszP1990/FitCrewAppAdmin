package com.fitcrew.FitCrewAppAdmin.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fitcrew.FitCrewAppModel.domain.model.ClientDto;

public class ClientResourceMockUtil {

	public static ClientDto clientDto() {
		return prepareClientDtoData();
	}

	public static List<ClientDto> clientDtos() {
		return IntStream.rangeClosed(1, 3)
				.mapToObj(value -> prepareClientDtoData(
				))
				.collect(Collectors.toList());
	}

	private static ClientDto prepareClientDtoData() {
		return ClientDto.builder()
				.firstName("firstName")
				.lastName("lastName")
				.dateOfBirth("01.01.1990")
				.password("test")
				.encryptedPassword("$2y$12$Y3QFw.tzF7OwIJGlpzk9s.5Ymq4zY3hItIkD0Xes3UWxBo2SkEgei")
				.email("mockedClient@gmail.com")
				.phone("501928341")
				.build();
	}

}
