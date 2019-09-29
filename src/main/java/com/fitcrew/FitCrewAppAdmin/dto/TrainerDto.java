package com.fitcrew.FitCrewAppAdmin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ApiModel(value = "Trainer")
@Getter
@Setter
@Builder
@AllArgsConstructor(onConstructor = @__(@Builder))
@NoArgsConstructor
@ToString
public class TrainerDto implements Serializable {

    private static final long serialVersionUID = -3255126230685615683L;

    @NotNull(message = "First name of trainer cannot be null")
    @Size(min = 2, max = 20, message = "First name of trainer must be equal or grater than 2 characters and less than 20 character")
    @ApiModelProperty(value = "First name of trainer")
    private String firstName;

    @NotNull(message = "Last name of trainer cannot be null")
    @Size(min = 2, max = 20, message = "Last name of trainer must be equal or grater than 2 characters and less than 20 character")
    @ApiModelProperty(value = "Last name of trainer")
    private String lastName;

    @NotNull(message = "Place in the ranking cannot be null")
    @ApiModelProperty(value = "Place in the ranking")
    private String placeInTheRanking;

    @NotNull(message = "Type of training cannot be null")
    @Size(min = 2, max = 20, message = "Type of training must be equal or grater than 2 characters and less than 20 character")
    @ApiModelProperty(value = "Type of training offered by trainer")
    private String typesOfTraining;

    @NotNull(message = "Short description about yourself cannot be null")
    @Size(min = 2, max = 200, message = "Short description about yourself must be equal or grater than 2 characters and less than 20 character")
    @ApiModelProperty(value = "Short description about yourself")
    private String somethingAboutYourself;

    @NotNull(message = "Trainer email address cannot be null")
    @ApiModelProperty(value = "Trainer email address")
    private String email;

    @NotNull(message = "Trainer phone number cannot be null")
    @ApiModelProperty(value = "Trainer phone number")
    private String phone;
}
