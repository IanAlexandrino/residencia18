package org.passwordrecovery.ap002.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordResetInput {

    @Email
    @NotBlank
    private String email;
}
