package com.sinaukoding.finalproject.foodordersystem.service;

import com.sinaukoding.finalproject.foodordersystem.model.app.SimpleMap;
import com.sinaukoding.finalproject.foodordersystem.model.filter.FoodFilterRecord;
import com.sinaukoding.finalproject.foodordersystem.model.request.FoodRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodService {

    void add(FoodRequestRecord request);

    void edit(FoodRequestRecord request);

    Page<SimpleMap> findAll(FoodFilterRecord filterRequest, Pageable pageable);

    SimpleMap findById(String id);

}
