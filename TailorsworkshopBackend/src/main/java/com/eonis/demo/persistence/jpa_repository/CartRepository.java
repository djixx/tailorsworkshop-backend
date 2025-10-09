package com.eonis.demo.persistence.jpa_repository;

import com.eonis.demo.persistence.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<ShoppingCartEntity, Long> {
}
