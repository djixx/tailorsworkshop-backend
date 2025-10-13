package com.eonis.demo.persistence.jpa_repository;

import com.eonis.demo.core.model.Product;
import com.eonis.demo.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(
            """
                    SELECT p
                    FROM ProductEntity p
                    LEFT JOIN FETCH p.optionTypes ot
                    LEFT JOIN FETCH ot.optionChoices
                    WHERE p.id = :id
                    """)
    ProductEntity findWithOptions(Long id);

    List<ProductEntity> findAllByProductCategory_id(Long id);
}
