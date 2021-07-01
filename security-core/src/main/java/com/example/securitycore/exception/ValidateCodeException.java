package com.example.securitycore.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author jia
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -5340249119801686883L;

    public ValidateCodeException(String msg) {
        super(msg);
    }


}
