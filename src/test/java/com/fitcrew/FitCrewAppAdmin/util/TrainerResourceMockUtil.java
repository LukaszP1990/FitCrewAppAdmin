package com.fitcrew.FitCrewAppAdmin.util;

import com.fitcrew.FitCrewAppAdmin.dto.TrainerDto;
import com.fitcrew.FitCrewAppModel.domain.model.TrainerModel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainerResourceMockUtil {

    private static String TRAINER_FIRST_NAME = "firstName";
    private static String TRAINER_LAST_NAME = "lastName";
    private static String TRAINER_EMAIL = "mockedTrainer@gmail.com";
    private static String TRAINER_TRAINING = "Example training";
    private static String TRAINER_PHONE_NUMBER = "549102934";
    private static String TRAINER_DATE_OF_BIRTH = "01.01.1990";
    private static String TRAINER_DESCRIPTION = "Description about mock trainer";

    public static List<TrainerDto> trainerDtos() {
        return IntStream.rangeClosed(1, 3)
                .mapToObj(value -> prepareTrainerDtoData(String.valueOf(value)))
                .collect(Collectors.toList());
    }

    public static TrainerDto trainerDto() {
        return prepareTrainerDtoData("1");
    }

    public static TrainerDto notValidTrainerDto() {
        return prepareNotValidTrainerDtoData();
    }

    public static List<TrainerModel> trainerModels() {
        return IntStream.rangeClosed(1, 3)
                .mapToObj(value -> prepareTrainerModelData(String.valueOf(value)))
                .collect(Collectors.toList());
    }

    public static TrainerModel trainerModel() {
        return prepareTrainerModelData("1");
    }


    private static TrainerDto prepareTrainerDtoData(String placeInTheRanking) {
        return TrainerDto.builder()
                .firstName(TRAINER_FIRST_NAME)
                .lastName(TRAINER_LAST_NAME)
                .email(TRAINER_EMAIL)
                .phone(TRAINER_PHONE_NUMBER)
                .placeInTheRanking(placeInTheRanking)
                .typeOfTraining(TRAINER_TRAINING)
                .dateOfBirth(TRAINER_DATE_OF_BIRTH)
                .somethingAboutYourself(TRAINER_DESCRIPTION)
                .password("password")
                .build();
    }

    private static TrainerDto prepareNotValidTrainerDtoData() {
        return TrainerDto.builder()
                .placeInTheRanking("1")
                .build();
    }

    private static TrainerModel prepareTrainerModelData(String placeInTheRanking) {
        return TrainerModel.builder()
                .firstName(TRAINER_FIRST_NAME)
                .lastName(TRAINER_LAST_NAME)
                .email(TRAINER_EMAIL)
                .dateOfBirth(TRAINER_DATE_OF_BIRTH)
                .phone(TRAINER_PHONE_NUMBER)
                .placeInTheRanking(placeInTheRanking)
                .typeOfTraining(TRAINER_TRAINING)
                .somethingAboutYourself(TRAINER_DESCRIPTION)
                .build();
    }
}
