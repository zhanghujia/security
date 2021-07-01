package com.example.securitycore.social.properties;

import org.springframework.stereotype.Component;

/**
 * @author jia
 */
@Component
public abstract class AutoSocialProperties {

    private String appId;

    private String appSecret;

    public AutoSocialProperties() {
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
