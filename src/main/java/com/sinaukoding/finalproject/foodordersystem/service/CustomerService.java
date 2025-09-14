package com.sinaukoding.finalproject.foodordersystem.service;

import com.sinaukoding.finalproject.foodordersystem.model.app.SimpleMap;
import com.sinaukoding.finalproject.foodordersystem.model.filter.CustomerFilterRecord;
import com.sinaukoding.finalproject.foodordersystem.model.request.CustomerRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    void add(CustomerRequestRecord request);

    void edit(CustomerRequestRecord request);

    Page<SimpleMap> findAll(CustomerFilterRecord filterRequest, Pageable pageable);

    SimpleMap findById(String id);

}
