package com.sinaukoding.finalproject.foodordersystem.controller;

import com.sinaukoding.finalproject.foodordersystem.model.filter.FoodFilterRecord;
import com.sinaukoding.finalproject.foodordersystem.model.request.FoodRequestRecord;
import com.sinaukoding.finalproject.foodordersystem.model.response.BaseResponse;
import com.sinaukoding.finalproject.foodordersystem.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping("save")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> save(@RequestBody FoodRequestRecord request) {
        foodService.add(request);
        return BaseResponse.ok("Data berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> edit(@RequestBody FoodRequestRecord request) {
        foodService.edit(request);
        return BaseResponse.ok("Data berhasil diubah", null);
    }

    @PostMapping("find-all")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody FoodFilterRecord filterRequest) {
        return BaseResponse.ok(null, foodService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, foodService.findById(id));
    }
}
