package com.example.securitycore.social.qq.connection;

import com.example.securitycore.social.qq.Qq;
import com.example.securitycore.social.qq.impl.QqImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author jia
 */

// 不能component 会导致变成单例 全局变量会出现线程安全问题

public class QqServiceProvider extends AbstractOAuth2ServiceProvider<Qq> {

    private String appId;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QqServiceProvider(String appId, String appSecret) {
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public Qq getApi(String accessToken) {
        return new QqImpl(accessToken, appId);
    }


}
