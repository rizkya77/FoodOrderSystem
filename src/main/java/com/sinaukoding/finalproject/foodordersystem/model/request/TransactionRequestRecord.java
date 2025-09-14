package com.sinaukoding.finalproject.foodordersystem.model.request;

import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;
import jakarta.validation.constraints.NotNull;

public record TransactionRequestRecord(String id,
                                       @NotNull(message = "Jumlah tidak boleh kosong") Integer amount,
                                       Double price,
                                       Integer discount,
                                       Double totalPrice,
                                       Status status) {
}
