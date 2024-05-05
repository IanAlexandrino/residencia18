package org.passwordrecovery.ap002.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank(message = "UserName não pode ser nulo nem vazio!")
    @Size(min = 3, max = 20, message = "UserName não pode ter menos de 3 caracteres nem mais de 20!")
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email deve ser válido!")
    @NotBlank(message = "Email não pode ser nulo nem vazio!")
    @Size(min = 14, max = 50, message = "Email não pode ter menos de 14 caracteres nem mais de 50!")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    @NotBlank(message = "PassWord não pode ser nulo nem vazio!")
    @Size(min = 8, max = 40, message = "PassWord não pode ter menos de 8 caracteres nem mais de 40!")
    private String password;

}
