package com.example.securitycore.validate.processor.impl;

import com.example.securitycore.validate.code.ValidateCode;
import com.example.securitycore.validate.processor.ValidateCodeProcessor;
import com.example.securitycore.validate.service.ValidateCodeGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author jia
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode>
        implements ValidateCodeProcessor {

    private final SessionStrategy sessionStrategy
            = new HttpSessionSessionStrategy();

    @Autowired
    private Map<String, ValidateCodeGenerator>
            validateCodeGenerators;

    @Override
    public void create(ServletWebRequest request)
            throws Exception {
        C validateCode = generateCode(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    /**
     * 抽象方法
     *
     * @param request      请求
     * @param validateCode code
     * @throws Exception 异常
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 保存校验码
     *
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, C validateCode) {
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
    }

    @SuppressWarnings("unchecked")
    private C generateCode(ServletWebRequest request) {
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + "CodeGenerator");
        return (C) validateCodeGenerator.generateCode(request);
    }

    /**
     * 根据请求的URL 获取验证码的类型
     *
     * @param request
     * @return
     */
    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }


}
