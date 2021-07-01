package com.example.securitycore.config;

import com.example.securitycore.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author jia
 */

@Component
public class FormAuthenticationConfig {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    public void configure(HttpSecurity http) throws Exception {

        //  UsernamePasswordAuthenticationFilter 表单过滤器
        http.formLogin()
                //  BasicAuthenticationFilter 基础过滤器
                //  http.httpBasic()
                // 登录跳转地址
                .loginPage(SecurityConstants.DEFAULT_AUTO_REQUEST_REDIRECT)
                // 指明登录接口地址 虽然源码里有默认的 但是还是要指明(可以更改)
                .loginProcessingUrl(SecurityConstants.DEFAULT_AUTO_FORM_LOGIN)
                // 成功执行
                .successHandler(authenticationSuccessHandler)
                // 失败执行
                .failureHandler(authenticationFailureHandler);

    }

}
