package com.example.securitycore.social.qq.config;

import com.example.securitycore.properties.SecurityProperties;
import com.example.securitycore.social.config.SocialAutoConfigurerAdapter;
import com.example.securitycore.social.qq.properties.QqProperties;
import com.example.securitycore.social.qq.connection.QqConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author jia
 */
@Configuration
@ConditionalOnProperty(prefix = "com.example.security.social.qq", name = "appId")
public class QqAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QqProperties qqProperties = securityProperties.getSocial().getQq();
        return new QqConnectionFactory(qqProperties.getProviderId(), qqProperties.getAppId(), qqProperties.getAppSecret());
    }


}
