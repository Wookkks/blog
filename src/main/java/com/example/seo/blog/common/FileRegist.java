package com.example.seo.blog.common;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Getter
@Setter
public class FileRegist {

    private MultipartFile file;
    private static final String UPLOAD_DIR = "C:/Users/User/Desktop/images/";
    private String fileName;
    private String filePath;
    public FileRegist(MultipartFile file) throws IOException {
        this.file = file;
        UUID uuid = UUID.randomUUID();
        this.fileName = uuid + "_" + file.getOriginalFilename();
        this.filePath = UPLOAD_DIR + fileName;

        File destination = new File(filePath);
        file.transferTo(destination);
    }
}
