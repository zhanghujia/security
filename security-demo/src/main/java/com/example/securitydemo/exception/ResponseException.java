package com.example.securitydemo.exception;

/**
 * @author jia
 */
public class ResponseException extends RuntimeException {

    private static final long serialVersionUID = -7798906547160010730L;

    private final Integer code;

    public ResponseException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
