package com.fitcrew.FitCrewAppAdmin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ApiModel(value = "Admin")
@Getter
@Setter
@Builder
@AllArgsConstructor(onConstructor = @__(@Builder))
@NoArgsConstructor
@ToString
public class AdminDto implements Serializable {

    private static final long serialVersionUID = 2118331620631970477L;

    @NotNull(message = "First name of admin cannot be null")
    @Size(min = 2, max = 20, message = "First name of admin must be equal or grater than 2 characters and less than 20 character")
    @ApiModelProperty(value = "First name of admin")
    private String firstName;

    @NotNull(message = "Last name of admin cannot be null")
    @Size(min = 2, max = 20, message = "Last name of admin must be equal or grater than 2 characters and less than 20 character")
    @ApiModelProperty(value = "First name of admin")
    private String lastName;

    @NotNull(message = "Admin email address cannot be null")
    @ApiModelProperty(value = "Admin email address")
    private String email;

    @NotNull(message = "Password cannot be null")
    @ApiModelProperty(value = "Admin password")
    private String password;

    @ApiModelProperty(value = "Admin number identification")
    private String adminId;

    @ApiModelProperty(value = "Admin encrypted password")
    private String encryptedPassword;
}
