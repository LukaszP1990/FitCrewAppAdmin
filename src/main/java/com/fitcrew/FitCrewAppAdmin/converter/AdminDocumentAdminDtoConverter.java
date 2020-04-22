package com.fitcrew.FitCrewAppAdmin.converter;

import com.fitcrew.FitCrewAppAdmin.domains.AdminDocument;
import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;
import org.mapstruct.Mapper;

@Mapper
public interface AdminDocumentAdminDtoConverter {
    AdminDocument adminDtoToAdminDocument(AdminDto adminDto);
}
