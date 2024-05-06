package org.passwordrecovery.ap002.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordUpdateWithTokenInput {

    @NotBlank
    private String password;

    @NotBlank
    private String token;
}
