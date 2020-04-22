package com.fitcrew.FitCrewAppAdmin.dto;

import com.fitcrew.FitCrewAppAdmin.common.ValidationErrorMessage;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor(onConstructor = @__(@Builder))
@NoArgsConstructor
@ToString
public class LoginDto {

	@NotNull(message = ValidationErrorMessage.LOGIN_ERROR_MESSAGE)
    private String email;

	@NotNull(message = ValidationErrorMessage.PASSWORD_ERROR_MESSAGE)
    private String password;
}
