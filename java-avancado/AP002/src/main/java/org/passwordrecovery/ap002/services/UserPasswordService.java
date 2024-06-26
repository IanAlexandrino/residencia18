package org.passwordrecovery.ap002.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.passwordrecovery.ap002.dto.PasswordTokenPublicData;
import org.passwordrecovery.ap002.models.user.User;
import org.passwordrecovery.ap002.repositories.UserRepository;
import org.passwordrecovery.ap002.services.exceptions.EmailNotFoundException;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.token.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserPasswordService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public String generateToken(User user){
        KeyBasedPersistenceTokenService tokenService = getInstanceFor(user);

        tokenService.setServerSecret(user.getPassword());
        tokenService.setServerInteger(16);
        tokenService.setSecureRandom(new SecureRandomFactoryBean().getObject());

        Token token = tokenService.allocateToken(user.getEmail());

        return token.getKey();
    }

    @SneakyThrows
    public void changePassword(String newPassword, String rawToken){
        PasswordTokenPublicData publicData = readPublicData(rawToken);

        if (isExpired(publicData)){
            throw new RuntimeException("Token expirado!");
        }

        User user = userRepository.findByEmail(publicData.getEmail())
                .orElseThrow(EmailNotFoundException::new);

        KeyBasedPersistenceTokenService tokenService = this.getInstanceFor(user);
        tokenService.verifyToken(rawToken);

        user.setPassword(this.passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    private boolean isExpired(PasswordTokenPublicData publicData) {
        Instant createdAt = new Date(publicData.getCeateAtTimestamp()).toInstant();
        Instant now = new Date().toInstant();
        return createdAt.plus(Duration.ofMinutes(5)).isBefore(now);
    }

    private KeyBasedPersistenceTokenService getInstanceFor(User user) throws Exception{
        KeyBasedPersistenceTokenService tokenService = new KeyBasedPersistenceTokenService();

        tokenService.setServerSecret(user.getPassword());
        tokenService.setServerInteger(16);
        tokenService.setSecureRandom(new SecureRandomFactoryBean().getObject());
        return tokenService;
    }

    private PasswordTokenPublicData readPublicData(String rawToken){
        String rawTokenDecoded = new String(Base64.getDecoder().decode(rawToken));
        String[] tokenParts = rawTokenDecoded.split(":");
        Long timestamp = Long.parseLong(tokenParts[0]);
        String email = tokenParts[2];

        return new PasswordTokenPublicData(email, timestamp);
    }
}
