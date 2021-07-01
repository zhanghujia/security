package com.example.securitycore.properties;

import com.example.securitycore.social.qq.properties.QqProperties;
import org.springframework.stereotype.Component;

/**
 * @author jia
 */
@Component
public class SocialProperties {

    private String filterProcessUrl = "/social";

    private QqProperties qq = new QqProperties();

    public QqProperties getQq() {
        return qq;
    }

    public void setQq(QqProperties qq) {
        this.qq = qq;
    }

    public String getFilterProcessUrl() {
        return filterProcessUrl;
    }

    public void setFilterProcessUrl(String filterProcessUrl) {
        this.filterProcessUrl = filterProcessUrl;
    }
}
