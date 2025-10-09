package com.eonis.demo.core.service;

import com.eonis.demo.core.model.Subtype;

import java.util.List;

public interface SubtypeService {
    List<Subtype> get(Long categoryId);
}
