package com.example.securitybrowser.config;

import com.example.securitycore.config.FormAuthenticationConfig;
import com.example.securitycore.config.SmsCodeAuthenticationSecurityConfig;
import com.example.securitycore.config.ValidCodeFilterSecurityConfig;
import com.example.securitycore.constants.SecurityConstants;
import com.example.securitycore.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author jia
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ValidCodeFilterSecurityConfig validCodeFilterSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(false);
        return tokenRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.configure(http);

        http
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                // 拦截所有请求
                .authorizeRequests()
                // 拦截放过
                .antMatchers(
                        SecurityConstants.DEFAULT_AUTO_REQUEST_REDIRECT,
                        SecurityConstants.SOCIAL_AUTO_QQ_LOGIN,
                        securityProperties.getSocial().getFilterProcessUrl() + "/" +
                                securityProperties.getSocial().getQq().getProviderId(),
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_FINAL_CODE_PREFIX_ + "/*").permitAll()
                .anyRequest()
                .authenticated()
                // 跨站请求伪造拦截: 关闭
                .and().csrf().disable()
                //  最前边的拦截器 SecurityContextPersistenceFilter
                //  最终拦截器 FilterSecurityInterceptor
                //  将结果，异常，引导在 ExceptionTranslationFilter
                .apply(validCodeFilterSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(springSocialConfigurer)
        ;
    }

}
