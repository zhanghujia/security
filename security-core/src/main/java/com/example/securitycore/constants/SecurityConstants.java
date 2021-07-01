package com.example.securitycore.constants;

import org.springframework.stereotype.Component;

/**
 * @author jia
 */
@Component
public interface SecurityConstants {

    String DEFAULT_AUTO_FORM_LOGIN = "/auth/login";

    String MOBILE_AUTO_LOGIN = "/auth/mobile";

    String SOCIAL_AUTO_QQ_LOGIN = "/social/qq";

    String DEFAULT_AUTO_REQUEST_REDIRECT = "/auth/require";

    String DEFAULT_FINAL_CODE_PREFIX_ = "/code";
}
