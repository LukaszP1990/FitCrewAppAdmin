package com.fitcrew.FitCrewAppAdmin.services.capabilities;

import com.fitcrew.FitCrewAppAdmin.dto.ClientDto;
import com.fitcrew.FitCrewAppAdmin.dto.TrainerDto;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import com.fitcrew.FitCrewAppModel.domain.model.ClientModel;
import com.fitcrew.FitCrewAppModel.domain.model.TrainerModel;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminCapabilitiesServiceFacadeImpl implements AdminCapabilitiesServiceFacade {

    private final AdminCapabilitiesService adminCapabilitiesService;

    public AdminCapabilitiesServiceFacadeImpl(AdminCapabilitiesService adminCapabilitiesService) {
        this.adminCapabilitiesService = adminCapabilitiesService;
    }

    @Override
    public Either<ErrorMsg, List<ClientModel>> getClients() {
        return adminCapabilitiesService.getClients();
    }

    @Override
    public Either<ErrorMsg, ClientModel> getClient(String clientEmail) {
        return adminCapabilitiesService.getClient(clientEmail);
    }

    @Override
    public Either<ErrorMsg, ClientModel> deleteClient(String clientEmail) {
        return adminCapabilitiesService.deleteClient(clientEmail);
    }

    @Override
    public Either<ErrorMsg, ClientModel> updateClient(ClientDto clientDto, String clientEmail) {
        return adminCapabilitiesService.updateClient(clientDto, clientEmail);
    }

    @Override
    public Either<ErrorMsg, List<TrainerModel>> getTrainers() {
        return adminCapabilitiesService.getTrainers();
    }

    @Override
    public Either<ErrorMsg, TrainerModel> getTrainer(String trainerEmail) {
        return adminCapabilitiesService.getTrainer(trainerEmail);
    }

    @Override
    public Either<ErrorMsg, TrainerModel> deleteTrainer(String trainerEmail) {
        return adminCapabilitiesService.deleteTrainer(trainerEmail);
    }

    @Override
    public Either<ErrorMsg, TrainerModel> updateTrainer(TrainerDto trainerDto, String trainerEmail) {
        return adminCapabilitiesService.updateTrainer(trainerDto, trainerEmail);
    }
}
