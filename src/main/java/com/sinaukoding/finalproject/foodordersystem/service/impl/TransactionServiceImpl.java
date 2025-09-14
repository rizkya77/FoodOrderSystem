package com.sinaukoding.finalproject.foodordersystem.service.impl;

import com.sinaukoding.finalproject.foodordersystem.builder.CustomBuilder;
import com.sinaukoding.finalproject.foodordersystem.entity.Food;
import com.sinaukoding.finalproject.foodordersystem.entity.FoodImage;
import com.sinaukoding.finalproject.foodordersystem.entity.Transaction;
import com.sinaukoding.finalproject.foodordersystem.mapper.FoodMapper;
import com.sinaukoding.finalproject.foodordersystem.mapper.TransactionMapper;
import com.sinaukoding.finalproject.foodordersystem.model.app.AppPage;
import com.sinaukoding.finalproject.foodordersystem.model.app.SimpleMap;
import com.sinaukoding.finalproject.foodordersystem.model.filter.TransactionFilterRecord;
import com.sinaukoding.finalproject.foodordersystem.model.request.TransactionRequestRecord;
import com.sinaukoding.finalproject.foodordersystem.repository.FoodRepository;
import com.sinaukoding.finalproject.foodordersystem.repository.TransactionRepository;
import com.sinaukoding.finalproject.foodordersystem.service.TransactionService;
import com.sinaukoding.finalproject.foodordersystem.service.app.ValidatorService;
import com.sinaukoding.finalproject.foodordersystem.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final ValidatorService validatorService;

    @Override
    public void add(TransactionRequestRecord request) {

        validatorService.validator(request);

        var transaction = transactionMapper.requestToEntity(request);
        transactionRepository.save(transaction);

    }

    @Override
    public void edit(TransactionRequestRecord request) {

        validatorService.validator(request);

        var userExisting = transactionRepository.findById(request.id()).orElseThrow(() ->  new RuntimeException("Data transaksi tidak ditemukan"));

        var transaction = transactionMapper.requestToEntity(request);
        transaction.setId(userExisting.getId());
        transactionRepository.save(transaction);

    }

    @Override
    public Page<SimpleMap> findAll(TransactionFilterRecord filterRequest, Pageable pageable) {
        return null;
        /*
        CustomBuilder<Transaction> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("amount", filterRequest.amount(), builder);
        FilterUtil.builderConditionNotBlankLike("price", filterRequest.price(), builder);
        FilterUtil.builderConditionNotBlankLike("discount", filterRequest.discount(), builder);
        FilterUtil.builderConditionNotBlankLike("totalPrice", filterRequest.totalPrice(), builder);
        FilterUtil.builderConditionNotNullEqual("status", filterRequest.status(), builder);

        Page<Transaction> listTransaction = transactionRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listTransaction.stream().map(transaction -> {
            SimpleMap data = new SimpleMap();
            data.put("id", transaction.getId());
            data.put("amount", transaction.getAmount());
            data.put("price", transaction.getPrice());
            data.put("discount", transaction.getDiscount());
            data.put("totalPrice", transaction.getTotalPrice());
            data.put("status", transaction.getStatus().getLabel());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listTransaction.getTotalElements());

         */
    }

    @Override
    public SimpleMap findById(String id) {
        var transaction = transactionRepository.findById(id).orElseThrow(() ->  new RuntimeException("Data transaksi tidak ditemukan"));
        SimpleMap data = new SimpleMap();
        data.put("id", transaction.getId());
        data.put("amount", transaction.getAmount());
        data.put("price", transaction.getPrice());
        data.put("discount", transaction.getDiscount());
        data.put("totalPrice", transaction.getTotalPrice());
        data.put("status", transaction.getStatus().getLabel());
        data.put("listFood", transaction.getListFood().stream().map(Food::getPrice).collect(Collectors.toSet()));
        data.put("admin", transaction.getAdmin());
        data.put("customer", transaction.getCustomer());
        return data;
    }
}
