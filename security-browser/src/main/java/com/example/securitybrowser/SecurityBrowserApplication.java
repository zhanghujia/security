package com.example.securitybrowser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author jia
 */
@SpringBootApplication
@ComponentScan("com.example.securitycore")
public class SecurityBrowserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityBrowserApplication.class, args);
    }

}
