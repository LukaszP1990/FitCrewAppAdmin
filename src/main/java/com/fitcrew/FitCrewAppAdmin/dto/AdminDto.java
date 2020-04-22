package com.fitcrew.FitCrewAppAdmin.dto;

import com.fitcrew.FitCrewAppAdmin.common.ValidationErrorMessage;
import com.fitcrew.FitCrewAppAdmin.dto.validation.NotAllRequiredValueSetInAdminDto;
import lombok.*;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor(onConstructor = @__(@Builder))
@NoArgsConstructor
@ToString
@NotAllRequiredValueSetInAdminDto
public class AdminDto implements Serializable {

    private static final long serialVersionUID = 2118331620631970477L;

    @Size(min = 2, max = 20, message = ValidationErrorMessage.FIRST_NAME_ERROR_MESSAGE)
    private String firstName;

    @Size(min = 2, max = 20, message = ValidationErrorMessage.LAST_NAME_ERROR_MESSAGE)
    private String lastName;

    private String email;

    private String password;

    private String adminId;

    private String encryptedPassword;
}
