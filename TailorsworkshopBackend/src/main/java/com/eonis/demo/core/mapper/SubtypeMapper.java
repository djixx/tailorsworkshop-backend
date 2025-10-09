package com.eonis.demo.core.mapper;

import com.eonis.demo.core.model.Subtype;
import com.eonis.demo.persistence.entity.SubtypeEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubtypeMapper implements Mapper<SubtypeEntity, Subtype> {
    @Override
    public Subtype map(SubtypeEntity input) {
        Subtype subtype = new Subtype();
        subtype.setId(input.getId());
        subtype.setName(input.getName());
        return subtype;
    }

    @Override
    public List<Subtype> map(List<SubtypeEntity> input) {
        return Mapper.super.map(input);
    }
}
