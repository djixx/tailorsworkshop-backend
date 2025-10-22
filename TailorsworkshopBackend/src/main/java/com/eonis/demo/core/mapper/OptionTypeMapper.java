package com.eonis.demo.core.mapper;

import com.eonis.demo.core.model.OptionType;
import com.eonis.demo.persistence.entity.OptionTypeEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OptionTypeMapper implements Mapper<OptionTypeEntity, OptionType> {
    @Override
    public OptionType map(OptionTypeEntity input) {
        OptionType optionType = new OptionType();
        optionType.setId(input.getId());
        optionType.setName(input.getName());
        return optionType;
    }

    @Override
    public List<OptionType> map(List<OptionTypeEntity> input) {
        return Mapper.super.map(input);
    }
}
