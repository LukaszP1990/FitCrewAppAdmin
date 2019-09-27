package com.fitcrew.FitCrewAppAdmin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@ApiModel(value = "Login")
@Getter
@Setter
@Builder
@AllArgsConstructor(onConstructor = @__(@Builder))
@NoArgsConstructor
@ToString
public class LoginDto {

	@NotNull(message = "Login cannot be null")
	@ApiModelProperty(value = "Email address. It's a login name for admin")
    private String email;

	@NotNull(message = "Password cannot be null")
    @ApiModelProperty(value = "Password to authorize admin")
    private String password;
}
