package com.fitcrew.FitCrewAppAdmin.services.create;

import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import com.fitcrew.FitCrewAppModel.domain.model.AdminModel;
import io.vavr.control.Either;

public interface AdminCreateServiceFacade {
    Either<ErrorMsg, AdminModel> createAdmin(AdminDto adminDto);
}
