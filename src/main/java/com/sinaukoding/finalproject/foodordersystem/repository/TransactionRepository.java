package com.sinaukoding.finalproject.foodordersystem.repository;

import com.sinaukoding.finalproject.foodordersystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionRepository extends JpaRepository<Transaction, String>, JpaSpecificationExecutor<Transaction> {
}
