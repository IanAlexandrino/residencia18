package org.passwordrecovery.ap002.services.exceptions;

import org.springframework.security.core.AuthenticationException;

public class EmailNotFoundException extends AuthenticationException{
    public EmailNotFoundException(String msg) {
        super(msg);
    }

    public EmailNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
