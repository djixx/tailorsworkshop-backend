package com.eonis.demo.persistence.repository;

import com.eonis.demo.core.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAll();
}
