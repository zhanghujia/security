package com.example.securitycore.social.qq.connection;

import com.example.securitycore.social.qq.Qq;
import com.example.securitycore.social.qq.QqUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author jia
 */
public class QqAdapter implements ApiAdapter<Qq> {

    @Override
    public boolean test(Qq qq) {
        // QQ 服务是否正常通信
        return true;
    }

    @Override
    public void setConnectionValues(Qq qq, ConnectionValues connectionValues) {
        QqUserInfo userInfo = qq.getQqUserInfo();
        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setImageUrl(userInfo.getFigureurlQq1());
        connectionValues.setProfileUrl(null);
        connectionValues.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(Qq qq) {
        return null;
    }

    @Override
    public void updateStatus(Qq qq, String s) {
     // 某些网站有这个 QQ 暂无
    }
}
