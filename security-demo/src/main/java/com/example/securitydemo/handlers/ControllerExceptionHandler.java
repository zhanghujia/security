package com.example.securitydemo.handlers;

import com.example.securitydemo.exception.RequestException;
import com.example.securitydemo.exception.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jia
 */

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResponseException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handlerException(ResponseException e) {
        Map<String, Object> result = new HashMap<>(3);
        result.put("code",e.getCode());
        result.put("message", e.getMessage());
        result.put("data", null);
        return result;
    }


    @ExceptionHandler(RequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handlerException(RequestException e) {
        Map<String, Object> result = new HashMap<>(3);
        result.put("code",e.getCode());
        result.put("message", e.getMessage());
        result.put("data", null);
        return result;
    }

}
