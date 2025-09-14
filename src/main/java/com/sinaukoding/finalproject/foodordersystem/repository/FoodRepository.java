package com.sinaukoding.finalproject.foodordersystem.repository;

import com.sinaukoding.finalproject.foodordersystem.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FoodRepository extends JpaRepository<Food, String>, JpaSpecificationExecutor<Food> {
    boolean existsByName(String lowerCase);

    boolean existsByNameAndIdNot(String lowerCase, String id);
}
