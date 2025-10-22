package com.eonis.demo.core.service;

import com.eonis.demo.core.model.NewProduct;
import com.eonis.demo.core.model.Product;
import com.eonis.demo.core.model.ProductDetails;
import com.eonis.demo.persistence.entity.ProductEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> getAll();

    ProductDetails getWithDetail(Long id);

    ProductEntity findWithOptions(Long productId);

    List<Product> getForCategory(Long categoryId);

    Product save(NewProduct newProduct, MultipartFile imageFile) throws IOException;
}


