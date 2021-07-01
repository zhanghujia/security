package com.example.securitydemo.exception;

/**
 * @author jia
 */
public class RequestException extends RuntimeException {

    private static final long serialVersionUID = -5157148221076743548L;

    private final Integer code;

    public RequestException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
