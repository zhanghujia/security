package com.example.securitycore.filter;

import com.example.securitycore.constants.SecurityConstants;
import com.example.securitycore.exception.ValidateCodeException;
import com.example.securitycore.handler.AuthenticationFailureHandler;
import com.example.securitycore.properties.SecurityProperties;
import com.example.securitycore.validate.code.ImageCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.example.securitycore.validate.processor.ValidateCodeProcessor.SESSION_KEY_PREFIX;

/**
 * @author jia
 */
@Component
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler
            = new AuthenticationFailureHandler();

    private SessionStrategy sessionStrategy
            = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties = new SecurityProperties();

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() {
        String url = securityProperties.getCode().getImage().getUrl();
        if (!Objects.isNull(url)) {
            String[] configUrls =
            StringUtils.splitByWholeSeparatorPreserveAllTokens
                     (url, ",");
            urls.addAll(Arrays.asList(configUrls));
        }
        urls.add(SecurityConstants.DEFAULT_AUTO_FORM_LOGIN);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        boolean action = false;

        for (String url : urls) {
            if (antPathMatcher.match(url, httpServletRequest.getRequestURI())) {
                action = true;
            }
        }

//        String loginUrl = "/login";
//        if (StringUtils.equals(loginUrl, httpServletRequest.getRequestURI())
//                && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")) {

        if (action) {
            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,
                        httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    public void validate(ServletWebRequest request) throws ServletRequestBindingException {

        ImageCode codeInSession =
                (ImageCode) sessionStrategy.getAttribute(request, SESSION_KEY_PREFIX + "IMAGE");

        String codeInRequest
                = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("???????????????????????????");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("??????????????????");
        }

        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request,SESSION_KEY_PREFIX + "IMAGE");
            throw new ValidateCodeException("??????????????????");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("??????????????????");
        }

        sessionStrategy.removeAttribute(request,SESSION_KEY_PREFIX + "IMAGE");
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }


}
