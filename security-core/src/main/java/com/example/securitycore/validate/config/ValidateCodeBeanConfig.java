package com.example.securitycore.validate.config;

import com.example.securitycore.properties.SecurityProperties;
import com.example.securitycore.validate.service.ValidateCodeGenerator;
import com.example.securitycore.validate.service.impl.image.ImageCodeGeneratorImpl;
import com.example.securitycore.validate.service.impl.sms.DefaultSmsCodeSender;
import com.example.securitycore.validate.service.impl.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jia
 */

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    // 如果有另外的imageCodeGenerator 条件判断使用另外的

    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGeneratorImpl imageCodeGenerator = new ImageCodeGeneratorImpl();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }

}
