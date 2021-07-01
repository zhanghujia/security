package com.example.securitydemo.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;


/**
 * @author jia
 */

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {


    String path = "C:\\Users\\jia\\IdeaProjects\\security\\security-parent\\";

    @PostMapping("/create")
    @ApiOperation(value = "上传文件服务")
    public String uploadFile(MultipartFile file) throws IOException {

        File file2 = new File(path + Instant.now().toEpochMilli() + ".jpg");

        file.transferTo(file2);

        String absolutePath = file2.getAbsolutePath();

//        dispose(file);

        log.info(absolutePath);

        return absolutePath;

    }

    @GetMapping("/down")
    @ApiOperation(value = "下载文件服务")
    public void downloadFile(@RequestParam("fileName") String fileName,
                             HttpServletResponse response)
            throws IOException {

        try (InputStream inputStream =
                     new FileInputStream(path
                             + fileName + ".jpg");
             OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ".jpg");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }

    }

    public void dispose(MultipartFile file) throws IOException {

        try (InputStream inputStream = file.getInputStream();
             BufferedInputStream inputStream1 = new BufferedInputStream(inputStream);
             FileOutputStream outputStream = new FileOutputStream(Instant.now().toEpochMilli() + ".jpg");
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {

            byte[] bytes = new byte[1024];

            int len;

            while ((len = inputStream1.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, len);
            }

            //        Files.delete(Paths.get("1.jpg"));
        }
    }

}
