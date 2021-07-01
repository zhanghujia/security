package com.example.securitycore.validate.service.impl.sms;

import com.example.securitycore.properties.SecurityProperties;
import com.example.securitycore.validate.code.SmsCode;
import com.example.securitycore.validate.code.ValidateCode;
import com.example.securitycore.validate.service.ValidateCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author jia
 */
@Component("smsCodeGenerator")
public class SmsCodeGeneratorImpl implements ValidateCodeGenerator {

    private SecurityProperties securityProperties
            = new SecurityProperties();

    @Override
    public ValidateCode generateCode(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new SmsCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }


}
