package com.sinaukoding.finalproject.foodordersystem.controller;

import com.sinaukoding.finalproject.foodordersystem.model.filter.TransactionFilterRecord;
import com.sinaukoding.finalproject.foodordersystem.model.request.TransactionRequestRecord;
import com.sinaukoding.finalproject.foodordersystem.model.response.BaseResponse;
import com.sinaukoding.finalproject.foodordersystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("save")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> save(@RequestBody TransactionRequestRecord request) {
        transactionService.add(request);
        return BaseResponse.ok("Data berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> edit(@RequestBody TransactionRequestRecord request) {
        transactionService.edit(request);
        return BaseResponse.ok("Data berhasil diubah", null);
    }

    @PostMapping("find-all")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody TransactionFilterRecord filterRequest) {
        return BaseResponse.ok(null, transactionService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, transactionService.findById(id));
    }
}
