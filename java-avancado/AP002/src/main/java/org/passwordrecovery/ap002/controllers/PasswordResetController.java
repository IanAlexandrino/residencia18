package org.passwordrecovery.ap002.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.passwordrecovery.ap002.dto.PasswordResetInput;
import org.passwordrecovery.ap002.dto.PasswordUpdateWithTokenInput;
import org.passwordrecovery.ap002.models.user.User;
import org.passwordrecovery.ap002.repositories.UserRepository;
import org.passwordrecovery.ap002.services.EmailService;
import org.passwordrecovery.ap002.services.UserPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class PasswordResetController {
    private final UserPasswordService userPasswordService;
    private final UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/forgot-password")
    public void forgotPassword(@Valid @RequestBody PasswordResetInput input){
        Optional<User> optionalUser = this.userRepository.findByEmail(input.getEmail());
        optionalUser.ifPresent(user -> {
            String token = userPasswordService.generateToken(user);

            log.info(emailService.enviarEmailTexto(
                    input.getEmail(),
                    "Resetar Senha!",
                    token
            ));

            log.info(token);
        });
    }

    @PostMapping("/change-password")
    public void changePassword(@Valid @RequestBody PasswordUpdateWithTokenInput input){
        try{

            userPasswordService.changePassword(input.getPassword(), input.getToken());

        } catch (Exception e){

            log.error("Erro ao alterar a senha usando token", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }
    }
}
