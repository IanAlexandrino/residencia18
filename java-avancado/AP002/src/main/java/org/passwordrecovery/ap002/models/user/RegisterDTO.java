package org.passwordrecovery.ap002.models.user;

public record RegisterDTO(String username, String password, String email, UserRole role) {
}