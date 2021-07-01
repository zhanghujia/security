package com.example.securitycore.validate.processor.impl;

import com.example.securitycore.validate.code.SmsCode;
import com.example.securitycore.validate.service.impl.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author jia
 */
@Component("smsCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<SmsCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    /**
     * 发送短信验证码
     *
     * @param request 请求
     * @param smsCode 短信实体
     * @throws Exception 异常
     */
    @Override
    protected void send(ServletWebRequest request, SmsCode smsCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        smsCodeSender.send(mobile, smsCode.getCode());
    }
}
