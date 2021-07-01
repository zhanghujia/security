package com.example.securitycore.validate.processor;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author jia
 */
public interface ValidateCodeProcessor {

    String SESSION_KEY_PREFIX
            = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建验证码
     *
     * @param request 请求
     * @throws Exception 系统异常
     */
    void create(ServletWebRequest request) throws Exception;

}
