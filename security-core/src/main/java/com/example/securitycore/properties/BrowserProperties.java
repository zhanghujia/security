package com.example.securitycore.properties;

import com.example.securitycore.enums.LoginType;
import org.springframework.stereotype.Component;


/**
 * @author jia
 */
@Component
public class BrowserProperties {

    private String loginPage = "/login-demo.html";

    private LoginType loginType = LoginType.REDIRECT;

    private int rememberMeSeconds = 3600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }


    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
