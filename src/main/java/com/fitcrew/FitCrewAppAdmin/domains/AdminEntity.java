package com.fitcrew.FitCrewAppAdmin.domains;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "Admin")
@Getter
@Setter
@Builder
@AllArgsConstructor(onConstructor = @__(@Builder))
@NoArgsConstructor
@ToString
public class AdminEntity implements Serializable {

    private static final long serialVersionUID = 1421658171867127534L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(name = "Email", nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false, unique = true)
    private String adminId;

    @Column(nullable = false, unique = true)
    private String encryptedPassword;
}
