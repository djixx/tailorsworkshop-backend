package com.eonis.demo.persistence.jpa_repository;


import com.eonis.demo.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {
}
