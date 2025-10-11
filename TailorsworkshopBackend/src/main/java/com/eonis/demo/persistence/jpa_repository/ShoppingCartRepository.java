package com.eonis.demo.persistence.jpa_repository;

import com.eonis.demo.persistence.entity.ShoppingCartEntity;
import com.eonis.demo.persistence.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

    @Query(
            """
                    SELECT c FROM ShoppingCartEntity c
                    LEFT JOIN FETCH c.items i
                    LEFT JOIN FETCH i.product p
                    LEFT JOIN FETCH c.createdBy u  
                    WHERE u.email = :email AND c.status = :status
                    """)
    Optional<ShoppingCartEntity> findByCreatedBy_EmailAndStatusFetchAll(
            @Param("email") String email,
            @Param("status") CartStatus status
    );


    Optional<ShoppingCartEntity> findByCreatedBy_EmailAndStatus(String email, CartStatus status);
}
