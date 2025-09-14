package com.sinaukoding.finalproject.foodordersystem.repository;

import com.sinaukoding.finalproject.foodordersystem.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {
    boolean existsByEmail(String lowerCase);

    boolean existsByUsername(String lowerCase);

    boolean existsByEmailAndIdNot(String lowerCase, String id);

    boolean existsByUsernameAndIdNot(String lowerCase, String id);

    Optional<Admin> findByUsername(String username);
}
