package com.fitcrew.FitCrewAppAdmin.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.fitcrew.FitCrewAppAdmin.dto.ClientDto;
import com.fitcrew.FitCrewAppAdmin.dto.TrainerDto;
import com.fitcrew.FitCrewAppAdmin.enums.AdminErrorMessageType;
import com.fitcrew.FitCrewAppAdmin.feignclient.FeignClientService;
import com.fitcrew.FitCrewAppAdmin.feignclient.FeignTrainerService;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import com.fitcrew.FitCrewAppAdmin.util.ClientResourceMockUtil;
import com.fitcrew.FitCrewAppAdmin.util.TrainerResourceMockUtil;

import io.vavr.control.Either;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AdminCapabilitiesServiceTest {

	private final static List<ClientDto> mockedClientDtos = ClientResourceMockUtil.clientDtos();
	private final static ClientDto mockedClientDto = ClientResourceMockUtil.clientDto();
	private final static List<TrainerDto> mockedTrainerDtos = TrainerResourceMockUtil.trainerDtos();
	private final static TrainerDto mockedTrainerDto = TrainerResourceMockUtil.trainerDto();
	private static String CLIENT_EMAIL = "mockedClient@gmail.com";
	private static String TRAINER_EMAIL = "mockedTrainer@gmail.com";
	private static String FIRST_NAME = "firstName";
	private static String LAST_NAME = "lastName";
	private static String CLIENT_DATE_OF_BIRTH = "01.01.1990";
	private static String CLIENT_PHONE_NUMBER = "501928341";
	private static String TRAINER_PHONE_NUMBER = "549102934";
	private static String TRAINER_RANKING_PLACE = "1";
	private static String TRAINING_TYPE = "Example training";
	private static String TRAINER_DETAILS = "Description about mock trainer";

	@Captor
	private ArgumentCaptor<String> stringArgumentCaptor;

	@Captor
	private ArgumentCaptor<ClientDto> clientDtoArgumentCaptor;

	@Captor
	private ArgumentCaptor<TrainerDto> trainerDtoArgumentCaptor;

	@Mock
	private FeignClientService feignClientService;

	@Mock
	private FeignTrainerService feignTrainerService;

	@InjectMocks
	private AdminCapabilitiesService adminCapabilitiesService;

	@Test
	void shouldGetClients() {
		when(feignClientService.getClients()).thenReturn(mockedClientDtos);

		Either<ErrorMsg, List<ClientDto>> clients = adminCapabilitiesService.getClients();

		verify(feignClientService, times(1)).getClients();
		assertNotNull(adminCapabilitiesService.getClients());
		assertAll(() -> {
			assertTrue(clients.isRight());
			assertEquals(3, clients.get().size());
		});
	}

	@Test
	void shouldFailBecauseOfEmptyListsOfClients() {

		Either<ErrorMsg, List<ClientDto>> clients = adminCapabilitiesService.getClients();

		assertNotNull(clients);
		checkEitherLeft(clients.isLeft(), AdminErrorMessageType.NO_CLIENTS_FOUND, clients.getLeft());
	}

	@Test
	void shouldGetClient() {
		when(feignClientService.getClient(anyString())).thenReturn(mockedClientDto);

		Either<ErrorMsg, ClientDto> client = adminCapabilitiesService.getClient(CLIENT_EMAIL);

		verify(feignClientService, times(1)).getClient(CLIENT_EMAIL);
		verify(feignClientService).getClient(stringArgumentCaptor.capture());

		assertNotNull(client);
		assertAll(() -> {
			assertTrue(client.isRight());
			assertEquals(FIRST_NAME, client.get().getFirstName());
			assertEquals(LAST_NAME, client.get().getLastName());
			assertEquals(CLIENT_DATE_OF_BIRTH, client.get().getDateOfBirth());
			assertEquals(CLIENT_EMAIL, client.get().getEmail());
			assertEquals(CLIENT_PHONE_NUMBER, client.get().getPhone());
		});
	}

	@Test
	void shouldFailBecauseNoClientFound() {

		Either<ErrorMsg, ClientDto> client = adminCapabilitiesService.getClient(CLIENT_EMAIL);

		assertNotNull(client);
		checkEitherLeft(client.isLeft(), AdminErrorMessageType.NO_CLIENT_FOUND, client.getLeft());
	}

	@Test
	void shouldDeleteClient() {
		when(feignClientService.deleteClient(anyString())).thenReturn(mockedClientDto);

		Either<ErrorMsg, ClientDto> client = adminCapabilitiesService.deleteClient(CLIENT_EMAIL);

		verify(feignClientService, times(1)).deleteClient(CLIENT_EMAIL);
		verify(feignClientService).deleteClient(stringArgumentCaptor.capture());

		assertNotNull(client);
		assertAll(() -> {
			assertTrue(client.isRight());
			assertEquals(FIRST_NAME, client.get().getFirstName());
			assertEquals(LAST_NAME, client.get().getLastName());
			assertEquals(CLIENT_DATE_OF_BIRTH, client.get().getDateOfBirth());
			assertEquals(CLIENT_EMAIL, client.get().getEmail());
			assertEquals(CLIENT_PHONE_NUMBER, client.get().getPhone());
		});
	}

	@Test
	void shouldFailBecauseNoClientFoundToDelete() {

		Either<ErrorMsg, ClientDto> client = adminCapabilitiesService.deleteClient(CLIENT_EMAIL);

		assertNotNull(client);
		checkEitherLeft(client.isLeft(), AdminErrorMessageType.NO_CLIENT_DELETED, client.getLeft());
	}

	@Test
	void shouldUpdateClient() {
		when(feignClientService.updateClient(any(), anyString())).thenReturn(mockedClientDto);

		Either<ErrorMsg, ClientDto> client = adminCapabilitiesService.updateClient(mockedClientDto, CLIENT_EMAIL);

		verify(feignClientService, times(1)).updateClient(mockedClientDto, CLIENT_EMAIL);
		verify(feignClientService).updateClient(clientDtoArgumentCaptor.capture(), stringArgumentCaptor.capture());

		assertNotNull(client);
		assertAll(() -> {
			assertTrue(client.isRight());
			assertEquals(FIRST_NAME, client.get().getFirstName());
			assertEquals(LAST_NAME, client.get().getLastName());
			assertEquals(CLIENT_DATE_OF_BIRTH, client.get().getDateOfBirth());
			assertEquals(CLIENT_EMAIL, client.get().getEmail());
			assertEquals(CLIENT_PHONE_NUMBER, client.get().getPhone());
		});
	}

	@Test
	void shouldFailBecauseNoClientFoundToUpdate() {

		Either<ErrorMsg, ClientDto> client = adminCapabilitiesService.updateClient(mockedClientDto, CLIENT_EMAIL);

		assertNotNull(client);
		checkEitherLeft(client.isLeft(), AdminErrorMessageType.NO_CLIENT_UPDATED, client.getLeft());
	}

	@Test
	void shouldGetTrainers() {

		when(feignTrainerService.getAllTrainers()).thenReturn(mockedTrainerDtos);

		Either<ErrorMsg, List<TrainerDto>> trainers = adminCapabilitiesService.getTrainers();

		verify(feignTrainerService, times(1)).getAllTrainers();
		assertNotNull(trainers);
		assertAll(() -> {
			assertTrue(trainers.isRight());
			assertEquals(3, trainers.get().size());
		});
	}

	@Test
	void shouldFailBecauseOfEmptyListsOfTrainers() {

		Either<ErrorMsg, List<TrainerDto>> trainers = adminCapabilitiesService.getTrainers();

		assertNotNull(trainers);
		checkEitherLeft(trainers.isLeft(), AdminErrorMessageType.NO_TRAINERS_FOUND, trainers.getLeft());
	}

	@Test
	void shouldGetTrainer() {
		when(feignTrainerService.getTrainer(anyString())).thenReturn(mockedTrainerDto);

		Either<ErrorMsg, TrainerDto> trainer = adminCapabilitiesService.getTrainer(TRAINER_EMAIL);

		verify(feignTrainerService, times(1)).getTrainer(TRAINER_EMAIL);
		verify(feignTrainerService).getTrainer(stringArgumentCaptor.capture());

		assertNotNull(trainer);
		assertAll(() -> {
			assertTrue(trainer.isRight());
			assertEquals(FIRST_NAME, trainer.get().getFirstName());
			assertEquals(LAST_NAME, trainer.get().getLastName());
			assertEquals(TRAINER_EMAIL, trainer.get().getEmail());
			assertEquals(TRAINER_PHONE_NUMBER, trainer.get().getPhone());
			assertEquals(TRAINER_RANKING_PLACE, trainer.get().getPlaceInTheRanking());
			assertEquals(TRAINING_TYPE, trainer.get().getTypesOfTraining());
			assertEquals(TRAINER_DETAILS, trainer.get().getSomethingAboutYourself());
		});
	}

	@Test
	void shouldFailBecauseNoTrainerFound() {

		Either<ErrorMsg, TrainerDto> trainer = adminCapabilitiesService.getTrainer(TRAINER_EMAIL);

		assertNotNull(trainer);
		checkEitherLeft(trainer.isLeft(), AdminErrorMessageType.NO_TRAINER_FOUND, trainer.getLeft());
	}

	@Test
	void shouldDeleteTrainer() {
		when(feignTrainerService.deleteTrainer(anyString())).thenReturn(mockedTrainerDto);

		Either<ErrorMsg, TrainerDto> trainer = adminCapabilitiesService.deleteTrainer(TRAINER_EMAIL);

		verify(feignTrainerService, times(1)).deleteTrainer(TRAINER_EMAIL);
		verify(feignTrainerService).deleteTrainer(stringArgumentCaptor.capture());

		assertNotNull(trainer);
		assertAll(() -> {
			assertTrue(trainer.isRight());
			assertEquals(FIRST_NAME, trainer.get().getFirstName());
			assertEquals(LAST_NAME, trainer.get().getLastName());
			assertEquals(TRAINER_EMAIL, trainer.get().getEmail());
			assertEquals(TRAINER_PHONE_NUMBER, trainer.get().getPhone());
			assertEquals(TRAINER_RANKING_PLACE, trainer.get().getPlaceInTheRanking());
			assertEquals(TRAINING_TYPE, trainer.get().getTypesOfTraining());
			assertEquals(TRAINER_DETAILS, trainer.get().getSomethingAboutYourself());
		});
	}

	@Test
	void shouldFailBecauseNoTrainerFoundToDelete() {

		Either<ErrorMsg, TrainerDto> trainer = adminCapabilitiesService.deleteTrainer(TRAINER_EMAIL);

		assertNotNull(trainer);
		checkEitherLeft(trainer.isLeft(), AdminErrorMessageType.NO_TRAINER_DELETED, trainer.getLeft());
	}

	@Test
	void shouldUpdateTrainer() {

		when(feignTrainerService.updateTrainer(any(), anyString())).thenReturn(mockedTrainerDto);

		Either<ErrorMsg, TrainerDto> trainer = adminCapabilitiesService.updateTrainer(mockedTrainerDto, TRAINER_EMAIL);

		verify(feignTrainerService, times(1)).updateTrainer(mockedTrainerDto, TRAINER_EMAIL);
		verify(feignTrainerService).updateTrainer(trainerDtoArgumentCaptor.capture(), stringArgumentCaptor.capture());

		assertNotNull(trainer);
		assertAll(() -> {
			assertTrue(trainer.isRight());
			assertEquals(FIRST_NAME, trainer.get().getFirstName());
			assertEquals(LAST_NAME, trainer.get().getLastName());
			assertEquals(TRAINER_EMAIL, trainer.get().getEmail());
			assertEquals(TRAINER_PHONE_NUMBER, trainer.get().getPhone());
			assertEquals(TRAINER_RANKING_PLACE, trainer.get().getPlaceInTheRanking());
			assertEquals(TRAINING_TYPE, trainer.get().getTypesOfTraining());
			assertEquals(TRAINER_DETAILS, trainer.get().getSomethingAboutYourself());
		});
	}

	@Test
	void shouldFailBecauseNoTrainerFoundToUpdate() {

		Either<ErrorMsg, TrainerDto> trainer = adminCapabilitiesService.updateTrainer(mockedTrainerDto, TRAINER_EMAIL);

		assertNotNull(trainer);
		checkEitherLeft(trainer.isLeft(), AdminErrorMessageType.NO_TRAINER_UPADTED, trainer.getLeft());
	}

	private void checkEitherLeft(boolean ifLeft,
								 AdminErrorMessageType errorMessageType,
								 ErrorMsg errorMsgEitherLeft) {
		assertTrue(ifLeft);
		assertEquals(errorMessageType.toString(), errorMsgEitherLeft.getMsg());
	}
}