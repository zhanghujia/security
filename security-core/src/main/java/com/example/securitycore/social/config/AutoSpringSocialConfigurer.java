package com.example.securitycore.social.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author jia
 */

public class AutoSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessUrl;

    public AutoSpringSocialConfigurer(String filterProcessUrl) {
        this.filterProcessUrl = filterProcessUrl;
    }

    // 覆盖默认过滤器地址
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter socialFilter
                = (SocialAuthenticationFilter) super.postProcess(object);
        socialFilter.setFilterProcessesUrl(filterProcessUrl);
        return (T) socialFilter;
    }
}
