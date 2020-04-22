package com.fitcrew.FitCrewAppAdmin.services.capabilities;

import com.fitcrew.FitCrewAppAdmin.dto.ClientDto;
import com.fitcrew.FitCrewAppAdmin.dto.TrainerDto;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import com.fitcrew.FitCrewAppModel.domain.model.ClientModel;
import com.fitcrew.FitCrewAppModel.domain.model.TrainerModel;
import io.vavr.control.Either;

import java.util.List;

public interface AdminCapabilitiesServiceFacade {
    Either<ErrorMsg, List<ClientModel>> getClients();

    Either<ErrorMsg, ClientModel> getClient(String clientEmail);

    Either<ErrorMsg, ClientModel> deleteClient(String clientEmail);

    Either<ErrorMsg, ClientModel> updateClient(ClientDto clientDto, String clientEmail);

    Either<ErrorMsg, List<TrainerModel>> getTrainers();

    Either<ErrorMsg, TrainerModel> getTrainer(String trainerEmail);

    Either<ErrorMsg, TrainerModel> deleteTrainer(String trainerEmail);

    Either<ErrorMsg, TrainerModel> updateTrainer(TrainerDto trainerDto, String trainerEmail);
}
