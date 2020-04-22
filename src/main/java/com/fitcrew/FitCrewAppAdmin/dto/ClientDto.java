package com.fitcrew.FitCrewAppAdmin.dto;

import com.fitcrew.FitCrewAppAdmin.common.ValidationErrorMessage;
import com.fitcrew.FitCrewAppAdmin.dto.validation.NotAllRequiredValueSetInClientDto;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor(onConstructor = @__(@Builder))
@NoArgsConstructor
@ToString
@NotAllRequiredValueSetInClientDto
public class ClientDto implements Serializable {

    private static final long serialVersionUID = 2118331620631970477L;

    @Size(min = 2, max = 20, message = ValidationErrorMessage.FIRST_NAME_ERROR_MESSAGE)
    private String firstName;

    @Size(min = 2, max = 20, message = ValidationErrorMessage.LAST_NAME_ERROR_MESSAGE)
    private String lastName;

    @Email
    private String email;

    @Pattern(regexp = "[0-9]{9}")
    private String phone;

    @Size(min = 2, max = 20, message = ValidationErrorMessage.DATE_OF_BIRTH_ERROR_MESSAGE)
    private String dateOfBirth;

    private String password;
    private String clientId;
    private String encryptedPassword;
}
