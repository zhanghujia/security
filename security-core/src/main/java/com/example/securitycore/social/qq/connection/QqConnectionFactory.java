package com.example.securitycore.social.qq.connection;

import com.example.securitycore.social.qq.Qq;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author jia
 */
public class QqConnectionFactory extends OAuth2ConnectionFactory<Qq> {

    public QqConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QqServiceProvider(appId, appSecret), new QqAdapter());
    }

}
