package com.eonis.demo.core.service;

import com.eonis.demo.core.model.OptionType;
import com.eonis.demo.persistence.entity.OptionTypeEntity;

import java.util.List;

public interface OptionTypeService {
    List<OptionType> getAll();

    List<OptionTypeEntity> findAllById(List<Long> optionTypes);
}
