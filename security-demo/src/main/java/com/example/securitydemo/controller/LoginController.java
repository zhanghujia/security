package com.example.securitydemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jia
 */
@RestController
public class LoginController {

//
//    @GetMapping("/me")
//    public Object getCurrentUser() {
//        return SecurityContextHolder.getContext().getAuthentication();
//    }

//    @GetMapping("/me")
//    public Object getCurrentUser(Authentication authentication) {
//        return authentication;
//    }

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

}
