package com.eonis.demo.core.service.impl;

import com.eonis.demo.core.mapper.ProductMapper;
import com.eonis.demo.core.model.NewProduct;
import com.eonis.demo.core.model.Product;
import com.eonis.demo.core.model.ProductDetails;
import com.eonis.demo.core.service.OptionTypeService;
import com.eonis.demo.core.service.ProductService;
import com.eonis.demo.persistence.entity.OptionChoiceEntity;
import com.eonis.demo.persistence.entity.OptionTypeEntity;
import com.eonis.demo.persistence.entity.ProductEntity;
import com.eonis.demo.persistence.jpa_repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper mapper;
    private final ProductRepository repository;
    private final OptionTypeService optionTypeService;

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
    public Product save(NewProduct newProduct) {
        ProductEntity product = new ProductEntity();
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setDescription(newProduct.getDescription());

        Set<OptionTypeEntity> types = new HashSet<>(optionTypeService.findAllById(newProduct.getOptionTypes()));
        product.setOptionTypes(types);

        return mapper.map(product);
    }
}
