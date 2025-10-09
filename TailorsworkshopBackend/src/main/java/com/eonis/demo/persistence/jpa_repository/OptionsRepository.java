package com.eonis.demo.persistence.jpa_repository;

import com.eonis.demo.persistence.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionsRepository extends JpaRepository<OptionEntity, Long> {
}
