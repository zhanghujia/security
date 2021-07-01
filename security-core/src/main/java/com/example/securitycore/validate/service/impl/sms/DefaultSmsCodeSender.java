package com.example.securitycore.validate.service.impl.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jia
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        log.info("向手机号:{} 发送验证码: {}", mobile, code);
    }
}
