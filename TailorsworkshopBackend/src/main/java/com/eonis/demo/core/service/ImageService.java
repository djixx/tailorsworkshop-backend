package com.eonis.demo.core.service;

import com.eonis.demo.persistence.entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ImageEntity save(MultipartFile file) throws IOException;
}
