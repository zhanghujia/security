package com.example.securitycore.validate.service;

import com.example.securitycore.validate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author jia
 */

public interface ValidateCodeGenerator {

    /**
     * 验证码生成
     *
     * @param request 请求
     * @return 实例对象
     */
    ValidateCode generateCode(ServletWebRequest request);

}
