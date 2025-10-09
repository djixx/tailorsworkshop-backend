package com.eonis.demo.persistence.jpa_repository;

import com.eonis.demo.persistence.entity.SubtypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtypeRepository extends JpaRepository<SubtypeEntity, Long> {
    @Query(
            """
                    select subtype from SubtypeEntity subtype
                    join subtype.categorySubtypes categorySubtypes
                    join categorySubtypes.category category
                    where category.id = :categoryId
                    """)
    List<SubtypeEntity> findAllByCategory(Long categoryId);
}
