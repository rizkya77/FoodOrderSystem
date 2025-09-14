package com.sinaukoding.finalproject.foodordersystem;

import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;
import com.sinaukoding.finalproject.foodordersystem.model.request.TransactionRequestRecord;
import com.sinaukoding.finalproject.foodordersystem.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    void addTransactionTest() {
        TransactionRequestRecord request = new TransactionRequestRecord(null,
                10,
                1000.00,
                10,
                900.00,
                Status.AKTIF
        );
        transactionService.add(request);
    }
}
