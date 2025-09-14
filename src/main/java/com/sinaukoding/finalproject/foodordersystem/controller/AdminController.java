package com.sinaukoding.finalproject.foodordersystem.controller;

import com.sinaukoding.finalproject.foodordersystem.model.filter.AdminFilterRecord;
import com.sinaukoding.finalproject.foodordersystem.model.request.AdminRequestRecord;
import com.sinaukoding.finalproject.foodordersystem.model.response.BaseResponse;
import com.sinaukoding.finalproject.foodordersystem.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("save")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> save(@RequestBody AdminRequestRecord request) {
        adminService.add(request);
        return BaseResponse.ok("Data berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> edit(@RequestBody AdminRequestRecord request) {
        adminService.edit(request);
        return BaseResponse.ok("Data berhasil diubah", null);
    }

    @PostMapping("find-all")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody AdminFilterRecord filterRequest) {
        return BaseResponse.ok(null, adminService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, adminService.findById(id));
    }

}
