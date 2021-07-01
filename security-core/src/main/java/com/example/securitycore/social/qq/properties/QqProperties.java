package com.example.securitycore.social.qq.properties;


import com.example.securitycore.social.properties.AutoSocialProperties;
import org.springframework.stereotype.Component;

/**
 * @author jia
 */

@Component
public class QqProperties extends AutoSocialProperties {

    private String providerId = "qq";


    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
