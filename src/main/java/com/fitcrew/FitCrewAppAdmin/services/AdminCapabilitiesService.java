package com.fitcrew.FitCrewAppAdmin.services;

import com.fitcrew.FitCrewAppAdmin.dto.ClientDto;
import com.fitcrew.FitCrewAppAdmin.dto.TrainerDto;
import com.fitcrew.FitCrewAppAdmin.feignclient.FeignClientService;
import com.fitcrew.FitCrewAppAdmin.feignclient.FeignTrainerService;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdminCapabilitiesService {

    private final FeignClientService feignClientService;
    private final FeignTrainerService feignTrainerService;

    public AdminCapabilitiesService(FeignClientService feignClientService,
                                    FeignTrainerService feignTrainerService) {
        this.feignClientService = feignClientService;
        this.feignTrainerService = feignTrainerService;
    }

    public Either<ErrorMsg, List<ClientDto>> getClients() {

        List<ClientDto> clients = feignClientService.getClients();

        return checkEitherResponseForClients(clients);
    }

    public Either<ErrorMsg, ClientDto> getClient(String clientEmail) {

        ClientDto client = feignClientService.getClient(clientEmail);

        return checkEitherResponseForClient(client);
    }

    public Either<ErrorMsg, ClientDto> deleteClient(String clientEmail) {

        ClientDto client = feignClientService.deleteClient(clientEmail);

        return checkEitherResponseForClient(client);
    }

    public Either<ErrorMsg, ClientDto> updateClient(ClientDto clientDto,
                                                    String clientEmail) {

        ClientDto client = feignClientService.updateClient(clientDto, clientEmail);

        return checkEitherResponseForClient(client);
    }

    public Either<ErrorMsg, List<TrainerDto>> getTrainers() {

        return checkEitherResponseForTrainers(feignTrainerService.getAllTrainers());
    }

    public Either<ErrorMsg, TrainerDto> getTrainer(String trainerEmail) {

        TrainerDto trainer = feignTrainerService.getTrainer(trainerEmail);

        return checkEitherResponseForTrainer(trainer);
    }

    public Either<ErrorMsg, TrainerDto> deleteTrainer(String trainerEmail) {

        TrainerDto trainer = feignTrainerService.deleteTrainer(trainerEmail);

        return checkEitherResponseForTrainer(trainer);
    }

    public Either<ErrorMsg, TrainerDto> updateTrainer(TrainerDto trainerDto,
                                                      String trainerEmail) {

        TrainerDto trainer = feignTrainerService.updateTrainer(trainerDto, trainerEmail);

        return checkEitherResponseForTrainer(trainer);
    }

    private Either<ErrorMsg, List<ClientDto>> checkEitherResponseForClients(List<ClientDto> clients) {
        if (!clients.isEmpty()) {
            log.debug("Clients to return: {}", clients);
            return Either.right(clients);
        } else {
            log.debug("No client found");
            return Either.left(new ErrorMsg("No client found"));
        }
    }

    private Either<ErrorMsg, ClientDto> checkEitherResponseForClient(ClientDto client) {
        if (client != null) {
            log.debug("Client to return: {}", client);
            return Either.right(client);
        } else {
            log.debug("No client found");
            return Either.left(new ErrorMsg("No client found"));
        }
    }

    private Either<ErrorMsg, List<TrainerDto>> checkEitherResponseForTrainers(List<TrainerDto> listsOfTrainers) {
        if (!listsOfTrainers.isEmpty()) {
            log.debug("List of trainers {}", listsOfTrainers);
            return Either.right(listsOfTrainers);
        } else {
            log.debug("No trainer found");
            return Either.left(new ErrorMsg("No trainers to return"));
        }
    }

    private Either<ErrorMsg, TrainerDto> checkEitherResponseForTrainer(TrainerDto trainer) {
        if (trainer != null) {
            log.debug("Trainer to return: {}", trainer);
            return Either.right(trainer);
        } else {
            log.debug("No trainer found");
            return Either.left(new ErrorMsg("No trainer found"));
        }
    }
}
