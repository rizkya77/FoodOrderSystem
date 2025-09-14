package com.sinaukoding.finalproject.foodordersystem.model.filter;

import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;

public record TransactionFilterRecord(Integer amount,
                                     Double price,
                                     Integer discount,
                                     Double totalPrice,
                                     Status status) {
}
