package com.example.securitycore.validate.processor.impl;

import com.example.securitycore.validate.code.ImageCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @author jia
 */
@Component("imageCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    /**
     * 发送图形验证码
     *
     * @param request   请求
     * @param imageCode 图像实体
     * @throws Exception 异常
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        BufferedImage image = imageCode.getImage();
        HttpServletResponse response = request.getResponse();
        assert response != null;
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

}
