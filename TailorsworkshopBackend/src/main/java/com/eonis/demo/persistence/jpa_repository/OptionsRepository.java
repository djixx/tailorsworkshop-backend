package com.eonis.demo.persistence.jpa_repository;

import com.eonis.demo.persistence.entity.OptionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionsRepository extends JpaRepository<OptionTypeEntity, Long> {
}
