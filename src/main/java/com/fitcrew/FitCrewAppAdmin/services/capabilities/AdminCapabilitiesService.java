package com.fitcrew.FitCrewAppAdmin.services.capabilities;

import com.fitcrew.FitCrewAppAdmin.dto.ClientDto;
import com.fitcrew.FitCrewAppAdmin.dto.TrainerDto;
import com.fitcrew.FitCrewAppAdmin.enums.AdminErrorMessageType;
import com.fitcrew.FitCrewAppAdmin.feignclient.FeignClientService;
import com.fitcrew.FitCrewAppAdmin.feignclient.FeignTrainerService;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import com.fitcrew.FitCrewAppModel.domain.model.ClientModel;
import com.fitcrew.FitCrewAppModel.domain.model.TrainerModel;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Either<ErrorMsg, List<ClientModel>> getClients() {
        return Optional.ofNullable(feignClientService.getClients())
                .map(Either::<ErrorMsg, List<ClientModel>>right)
                .orElseGet(() -> Either.left(new ErrorMsg(AdminErrorMessageType.NO_CLIENTS_FOUND.toString())));
    }

    public Either<ErrorMsg, ClientModel> getClient(String clientEmail) {
        return Optional.ofNullable(feignClientService.getClient(clientEmail))
                .map(Either::<ErrorMsg, ClientModel>right)
                .orElseGet(() -> Either.left(new ErrorMsg(AdminErrorMessageType.NO_CLIENT_FOUND.toString())));
    }

    public Either<ErrorMsg, ClientModel> deleteClient(String clientEmail) {
        return Optional.ofNullable(feignClientService.deleteClient(clientEmail))
                .map(Either::<ErrorMsg, ClientModel>right)
                .orElseGet(() -> Either.left(new ErrorMsg(AdminErrorMessageType.NO_CLIENT_DELETED.toString())));
    }

    public Either<ErrorMsg, ClientModel> updateClient(ClientDto clientDto,
                                                      String clientEmail) {
        return Optional.ofNullable(feignClientService.updateClient(clientDto, clientEmail))
                .map(Either::<ErrorMsg, ClientModel>right)
                .orElseGet(() -> Either.left(new ErrorMsg(AdminErrorMessageType.NO_CLIENT_UPDATED.toString())));
    }

    public Either<ErrorMsg, List<TrainerModel>> getTrainers() {
        return Optional.ofNullable(feignTrainerService.getAllTrainers())
                .map(Either::<ErrorMsg, List<TrainerModel>>right)
                .orElseGet(() -> Either.left(new ErrorMsg(AdminErrorMessageType.NO_TRAINERS_FOUND.toString())));
    }

    public Either<ErrorMsg, TrainerModel> getTrainer(String trainerEmail) {
        return Optional.ofNullable(feignTrainerService.getTrainer(trainerEmail))
                .map(Either::<ErrorMsg, TrainerModel>right)
                .orElseGet(() -> Either.left(new ErrorMsg(AdminErrorMessageType.NO_TRAINER_FOUND.toString())));
    }

    public Either<ErrorMsg, TrainerModel> deleteTrainer(String trainerEmail) {
        return Optional.ofNullable(feignTrainerService.deleteTrainer(trainerEmail))
                .map(Either::<ErrorMsg, TrainerModel>right)
                .orElseGet(() -> Either.left(new ErrorMsg(AdminErrorMessageType.NO_TRAINER_DELETED.toString())));
    }

    public Either<ErrorMsg, TrainerModel> updateTrainer(TrainerDto trainerDto,
                                                        String trainerEmail) {
        return Optional.ofNullable(feignTrainerService.updateTrainer(trainerDto, trainerEmail))
                .map(Either::<ErrorMsg, TrainerModel>right)
                .orElseGet(() -> Either.left(new ErrorMsg(AdminErrorMessageType.NO_TRAINER_UPDATED.toString())));
    }
}
