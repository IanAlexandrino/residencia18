package org.passwordrecovery.ap002.dto;

import org.passwordrecovery.ap002.models.user.UserRole;

public record RegisterDTO(String username, String password, String email, UserRole role) {
}
