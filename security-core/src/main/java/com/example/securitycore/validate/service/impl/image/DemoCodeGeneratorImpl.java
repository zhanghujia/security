package com.example.securitycore.validate.service.impl.image;

import com.example.securitycore.validate.code.ImageCode;
import com.example.securitycore.validate.service.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author jia
 */
//@Component("imageCodeGenerator")
@Slf4j
public class DemoCodeGeneratorImpl implements ValidateCodeGenerator {

    @Override
    public ImageCode generateCode(ServletWebRequest request) {
        log.info("可重写的图形验证码生成逻辑");
        return null;
    }

}
