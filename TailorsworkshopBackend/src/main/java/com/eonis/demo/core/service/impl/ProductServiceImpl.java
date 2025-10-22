package com.eonis.demo.core.service.impl;

import com.eonis.demo.core.mapper.ProductMapper;
import com.eonis.demo.core.model.NewProduct;
import com.eonis.demo.core.model.Product;
import com.eonis.demo.core.model.ProductDetails;
import com.eonis.demo.core.service.CategoryService;
import com.eonis.demo.core.service.ImageService;
import com.eonis.demo.core.service.OptionTypeService;
import com.eonis.demo.core.service.ProductService;
import com.eonis.demo.persistence.entity.*;
import com.eonis.demo.persistence.jpa_repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper mapper;
    private final ImageService imageService;
    private final ProductRepository repository;
    private final OptionTypeService optionTypeService;
    private final CategoryService categoryService;

    public List<Product> getAll() {
        List<ProductEntity> entities = repository.findAll();
        return mapper.map(entities);
    }

    public ProductDetails getWithDetail(Long id) {
        ProductEntity product = repository.findWithOptions(id);

        ProductDetails details = new ProductDetails();
        details.setId(product.getId());
        details.setName(product.getName());
        details.setPrice(product.getPrice());
        details.setDescription(product.getDescription());
        details.setCategoryName(product.getProductCategory().getName());

        Set<OptionTypeEntity> optionTypes = product.getOptionTypes();
        Map<String, Map<Long, String>> optionsByTypeWithId = optionTypes.stream()
                .collect(Collectors.toMap(
                        OptionTypeEntity::getName,
                        ot -> ot.getOptionChoices().stream()
                                .collect(Collectors.toMap(
                                        OptionChoiceEntity::getId,
                                        OptionChoiceEntity::getName
                                ))
                ));


        details.setOptionChoiceMap(optionsByTypeWithId);
        return details;
    }

    @Override
    public ProductEntity findWithOptions(Long productId) {
        return repository.findWithOptions(productId);
    }

    @Override
    public List<Product> getForCategory(Long categoryId) {
        List<ProductEntity> products = repository.findAllByProductCategory_id(categoryId);
        return mapper.map(products);
    }

    @Override
    public Product save(NewProduct newProduct, MultipartFile imageFile) throws IOException {
        ImageEntity image = null;
        if (imageFile != null && !imageFile.isEmpty()) {
            image = imageService.save(imageFile);
        }

        Long categoryId = newProduct.getCategoryId();
        ProductCategoryEntity categoryEntity = categoryService.get(categoryId);

        if (categoryEntity == null) {
            throw new IllegalArgumentException("Invalid category ID ");
        }

        ProductEntity product = new ProductEntity();
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setDescription(newProduct.getDescription());
        product.setProductCategory(categoryEntity);

        List<OptionTypeEntity> optionTypes = optionTypeService.findAllById(newProduct.getOptionTypes());
        Set<OptionTypeEntity> types = optionTypes != null ? new HashSet<>(optionTypes) : new HashSet<>();
        product.setOptionTypes(types);

        if (image != null) {
            product.setImage(image);
        }

        ProductEntity savedProduct = repository.save(product);
        return mapper.map(savedProduct);
    }
}
