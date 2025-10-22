package com.eonis.demo.core.service.impl;

import com.eonis.demo.core.mapper.OptionTypeMapper;
import com.eonis.demo.core.model.OptionType;
import com.eonis.demo.core.service.OptionTypeService;
import com.eonis.demo.persistence.entity.OptionTypeEntity;
import com.eonis.demo.persistence.jpa_repository.OptionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionTypeServiceImpl implements OptionTypeService {

    private final OptionTypeMapper mapper;
    private final OptionsRepository optionsRepository;

    @Override
    public List<OptionType> getAll() {
        List<OptionTypeEntity> all = optionsRepository.findAll();
        return mapper.map(all);
    }

    @Override
    public List<OptionTypeEntity> findAllById(List<Long> optionTypes) {
        return optionsRepository.findAllById(optionTypes);
    }
}
