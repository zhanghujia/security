package com.example.securitydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jia
 */

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello() {

//        throw new RequestException(-1,"requestException");

        return "Hello Spring Security";
    }
}
