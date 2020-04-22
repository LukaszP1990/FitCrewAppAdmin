package com.fitcrew.FitCrewAppAdmin.services.create;

import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import com.fitcrew.FitCrewAppModel.domain.model.AdminModel;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

@Component
public class AdminCreateServiceFacadeImpl implements AdminCreateServiceFacade {

    private final AdminCreateService adminCreateService;

    public AdminCreateServiceFacadeImpl(AdminCreateService adminCreateService) {
        this.adminCreateService = adminCreateService;
    }

    @Override
    public Either<ErrorMsg, AdminModel> createAdmin(AdminDto adminDto) {
        return adminCreateService.createAdmin(adminDto);
    }
}
