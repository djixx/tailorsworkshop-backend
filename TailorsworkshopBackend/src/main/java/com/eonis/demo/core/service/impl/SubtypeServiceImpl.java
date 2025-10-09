package com.eonis.demo.core.service.impl;

import com.eonis.demo.core.mapper.SubtypeMapper;
import com.eonis.demo.core.model.Subtype;
import com.eonis.demo.core.service.SubtypeService;
import com.eonis.demo.persistence.entity.SubtypeEntity;
import com.eonis.demo.persistence.jpa_repository.SubtypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class SubtypeServiceImpl implements SubtypeService {

    private final SubtypeMapper mapper;
    private final SubtypeRepository subtypeRepository;

    @Override
    public List<Subtype> get(Long categoryId) {
        List<SubtypeEntity> subtypeEntities = subtypeRepository.findAllByCategory(categoryId);
        return mapper.map(subtypeEntities);
    }
}
