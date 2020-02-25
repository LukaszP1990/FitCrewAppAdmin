package com.fitcrew.FitCrewAppAdmin.domains;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@Builder
@AllArgsConstructor(onConstructor = @__(@Builder))
@NoArgsConstructor
@ToString
public class AdminEntity implements Serializable {

    private static final long serialVersionUID = 1421658171867127534L;

    @Id
    private long id;

    @Field(value = "FIRST_NAME")
    @NotNull
    @Length(max = 20)
    private String firstName;

    @Field(value = "LAST_NAME")
    @NotNull
    @Length(max = 20)
    private String lastName;

    @Field(value = "EMAIL")
    @Indexed(unique = true)
    @NotNull
    @Length(max = 20)
    @Email
    private String email;

    @Field(value = "ADMIN_ID")
    @Indexed(unique = true)
    @NotNull
    private String adminId;

    @Field(value = "ENCRYPTED_PASSWORD")
    @Indexed(unique = true)
    @NotNull
    private String encryptedPassword;
}
