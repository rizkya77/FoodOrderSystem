package com.sinaukoding.finalproject.foodordersystem.service;

import com.sinaukoding.finalproject.foodordersystem.model.app.SimpleMap;
import com.sinaukoding.finalproject.foodordersystem.model.filter.AdminFilterRecord;
import com.sinaukoding.finalproject.foodordersystem.model.request.AdminRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {

    void add(AdminRequestRecord request);

    void edit(AdminRequestRecord request);

    Page<SimpleMap> findAll(AdminFilterRecord filterRequest, Pageable pageable);

    SimpleMap findById(String id);

}
