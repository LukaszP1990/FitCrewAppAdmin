package com.fitcrew.FitCrewAppAdmin.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fitcrew.FitCrewAppAdmin.dto.TrainerDto;

public class TrainerResourceMockUtil {

	public static List<TrainerDto> trainerDtos() {
		return IntStream.rangeClosed(1, 3)
				.mapToObj(value -> prepareTrainerDtoData(String.valueOf(value)))
				.collect(Collectors.toList());
	}

	public static TrainerDto trainerDto() {
		return prepareTrainerDtoData("1");
	}


	private static TrainerDto prepareTrainerDtoData(String placeInTheRanking) {
		return TrainerDto.builder()
				.firstName("firstName")
				.lastName("lastName")
				.email("mockedTrainer@gmail.com")
				.phone("549102934")
				.placeInTheRanking(placeInTheRanking)
				.typesOfTraining("Example training")
				.somethingAboutYourself("Description about mock trainer")
				.build();
	}
}
