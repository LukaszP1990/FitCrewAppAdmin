package com.fitcrew.FitCrewAppAdmin.converter;

import com.fitcrew.FitCrewAppAdmin.domains.AdminDocument;
import com.fitcrew.FitCrewAppModel.domain.model.AdminModel;
import org.mapstruct.Mapper;

@Mapper
public interface AdminDocumentAdminModelConverter {
    AdminModel adminDocumentToAdminModel(AdminDocument adminDocument);
}
