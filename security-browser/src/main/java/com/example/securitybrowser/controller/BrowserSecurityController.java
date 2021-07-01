package com.example.securitybrowser.controller;

import com.example.securitycore.constants.SecurityConstants;
import com.example.securitycore.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author jia
 */
@RestController
public class BrowserSecurityController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecurityProperties securityProperties;

    private final RequestCache requestCache =
            new HttpSessionRequestCache();

    private final RedirectStrategy redirectStrategy =
            new DefaultRedirectStrategy();

    /**
     * 需要身份认证时跳转到这里
     *
     * @param request  请求
     * @param response 返回
     * @return 实例对象数组
     */
    @RequestMapping(SecurityConstants.DEFAULT_AUTO_REQUEST_REDIRECT)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String requireAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (!Objects.isNull(savedRequest)) {
            String redirectUrl = savedRequest.getRedirectUrl();
            logger.info("引发的跳转链接为: {}", redirectUrl);
            String html = ".html";
            if (StringUtils.endsWithIgnoreCase(redirectUrl, html)) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        return "访问的服务需要身份认证，请引导用户到登录页";
    }

}
