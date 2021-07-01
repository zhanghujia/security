package com.example.securitycore.controller;

import com.example.securitycore.validate.processor.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author jia
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY
            = "SESSION_KEY_IMAGE_CODE";

    private final SessionStrategy sessionStrategy
            = new HttpSessionSessionStrategy();

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeGenerators;

//    @Autowired
//    private ValidateCodeGenerator imageCodeGenerator;
//
//    @Autowired
//    private ValidateCodeGenerator smsCodeGenerator;

//    @Autowired
//    private SmsCodeSender smsCodeSender;

//    @GetMapping("/code/image")
//    public void createImageCode(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        ImageCode imageCode = (ImageCode) imageCodeGenerator.generateCode(new ServletWebRequest(request));
//        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
//        BufferedImage image = imageCode.getImage();
//        ImageIO.write(image, "JPEG", response.getOutputStream());
//    }
//
//    @GetMapping("/code/sms")
//    public void createSmsCode(HttpServletRequest request, HttpServletResponse response)
//            throws ServletRequestBindingException {
//        SmsCode smsCode = (SmsCode) smsCodeGenerator.generateCode(new ServletWebRequest(request));
//        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
//        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
//        smsCodeSender.send(mobile, smsCode.getCode());
//    }

    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable String type) throws Exception {
        validateCodeGenerators.get(type + "CodeProcessor").create(new ServletWebRequest(request, response));
    }

}
