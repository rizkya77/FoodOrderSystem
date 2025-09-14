package com.sinaukoding.finalproject.foodordersystem.mapper;

import com.sinaukoding.finalproject.foodordersystem.entity.Transaction;
import com.sinaukoding.finalproject.foodordersystem.model.request.TransactionRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public Transaction requestToEntity(TransactionRequestRecord request){
        return Transaction.builder()
                .amount(request.amount())
                .price(request.price())
                .discount(request.discount())
                .totalPrice(request.totalPrice())
                .status(request.status())
                .build();
    }
}
