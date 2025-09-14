package com.sinaukoding.finalproject.foodordersystem.service;

import com.sinaukoding.finalproject.foodordersystem.model.app.SimpleMap;
import com.sinaukoding.finalproject.foodordersystem.model.filter.TransactionFilterRecord;
import com.sinaukoding.finalproject.foodordersystem.model.request.TransactionRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    void add(TransactionRequestRecord request);

    void edit(TransactionRequestRecord request);

    Page<SimpleMap> findAll(TransactionFilterRecord filterRequest, Pageable pageable);

    SimpleMap findById(String id);

}
