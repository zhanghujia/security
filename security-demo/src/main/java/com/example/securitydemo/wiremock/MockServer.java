package com.example.securitydemo.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author jia
 */
public class MockServer {

    public static void main(String[] args) throws IOException {
        WireMock.configureFor(8062);
        WireMock.removeAllMappings();
        ClassPathResource classPathResource = new ClassPathResource("mock/01.txt");
        String content = FileUtils.readFileToString(
                classPathResource.getFile(),
                StandardCharsets.UTF_8);
        WireMock.stubFor(
                WireMock.get("/order/1")
                        .willReturn(
                                WireMock.aResponse()
                                        .withBody(content)
                                        .withStatus(200)));
    }
}
