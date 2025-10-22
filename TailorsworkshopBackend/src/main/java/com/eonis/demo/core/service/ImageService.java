package com.eonis.demo.core.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void save(MultipartFile file) throws IOException;
}
