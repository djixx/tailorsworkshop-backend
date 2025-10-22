package com.eonis.demo.core.service.impl;

import com.eonis.demo.core.service.ImageService;
import com.eonis.demo.persistence.entity.ImageEntity;
import com.eonis.demo.persistence.jpa_repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public ImageEntity save(MultipartFile file) throws IOException {
        ImageEntity image = new ImageEntity();
        image.setName(file.getOriginalFilename());
        image.setContent(file.getBytes());

        imageRepository.save(image);
        return image;
    }
}
