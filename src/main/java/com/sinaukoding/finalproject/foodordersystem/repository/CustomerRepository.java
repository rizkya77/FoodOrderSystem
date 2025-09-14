package com.sinaukoding.finalproject.foodordersystem.repository;

import com.sinaukoding.finalproject.foodordersystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {
    boolean existsByEmail(String lowerCase);

    boolean existsByEmailAndIdNot(String lowerCase, String id);
}
