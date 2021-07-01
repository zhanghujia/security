package com.example.securitycore.social.qq.impl;

import com.example.securitycore.social.qq.Qq;
import com.example.securitycore.social.qq.QqUserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @author jia
 */
public class QqImpl extends AbstractOAuth2ApiBinding implements Qq {

    private final String getOpenIdUrl = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private final String userInfoUrl = "https://graph.qq.com/user/get_user_info?\n" +
            "oauth_consumer_key=%s&\n" +
            "openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QqImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String openUrl = String.format(getOpenIdUrl, accessToken);
        String result = getRestTemplate().getForObject(openUrl, String.class);
        System.out.println(result);
        this.openId = StringUtils.substringBetween(result, "\"openid\":", "}");
    }


    @Override
    public QqUserInfo getQqUserInfo() {
        String url = String.format(userInfoUrl, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        QqUserInfo qqUserInfo = null;
        try {
            qqUserInfo = objectMapper.readValue(result, QqUserInfo.class);
            qqUserInfo.setOpenId(this.openId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("无法获取Qq用户信息");
        }
        System.out.println(qqUserInfo);
        return qqUserInfo;
    }


}
