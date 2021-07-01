package com.example.securitycore.validate.service.impl.sms;

/**
 * @author jia
 */
public interface SmsCodeSender {

    /**
     * 发送手机短信验证码
     *
     * @param mobile 手机号
     * @param code   验证码
     */
    void send(String mobile, String code);

}
